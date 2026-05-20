# II. Geospatial Queries

> **Ví dụ ứng dụng:** Tìm tài xế Grab gần đây, tìm nhà hàng trong bán kính 5km.

Bài toán này khó ở chỗ vị trí của vật thể **thay đổi liên tục** (tài xế di chuyển) và tọa độ là **không gian 2 chiều** (Kinh độ - Vĩ độ), trong khi các index thông thường chỉ xử lý tốt trên không gian **1 chiều**.

---

## 1. Database khuyên dùng trong Interview

| Use Case | Database | Chi tiết |
|---|---|---|
| 🔴 Real-time, tần suất thay đổi cao (Uber, Tinder) | **Redis** | Lệnh `GEOADD`, `GEORADIUS` dựa trên **Geohash** + **Sorted Set** |
| 🟢 Dữ liệu tĩnh hơn, cần độ chính xác cao (nhà hàng, ranh giới quận/huyện) | **PostgreSQL + PostGIS** | Index **R-Tree** hoặc **Quad-Tree** |

---

## 2. Quy trình xử lý trong kiến trúc hệ thống (Step-by-Step)

Giả sử bạn đang thiết kế một ứng dụng giống **Uber / Grab**, cách handle sẽ như sau:

### Bước 1 — Mã hóa vị trí 2D thành 1D (Geohash)

Hệ thống **không thể** so sánh trực tiếp cặp `(Latitude, Longitude)` một cách hiệu quả. Thay vào đó:

- Chia bản đồ thế giới thành các **lưới ô vuông**.
- Mỗi ô vuông được đại diện bởi một chuỗi ký tự hoặc số nguyên → gọi là **Geohash**.
- Các vị trí **ở gần nhau** sẽ có chuỗi Geohash có **phần đầu (Prefix) giống nhau**.

```
(Lat, Long)  →  Geohash  →  "w3gv2f..."
                             ^^^^^^^^
                             Prefix giống nhau = ở gần nhau
```

### Bước 2 — Lưu trữ vào Redis

- Cứ mỗi **3–5 giây**, app của tài xế gửi tọa độ mới về server.
- Hệ thống tính ra **Geohash** và cập nhật vào **Redis Sorted Set (ZSET)**.
- **Score** của ZSET chính là giá trị số hóa của Geohash.

```
GEOADD drivers <longitude> <latitude> <driver_id>
```

### Bước 3 — Truy vấn phạm vi (Range Query)

- Khi khách hàng tìm xe, hệ thống tính **Geohash của khách hàng**.
- Quét một **dải các Geohash lân cận** trong Redis Sorted Set.
- Bài toán lúc này quy về **Range Query trên 1 chiều** → cực kỳ nhanh.

```
Khách hàng (Lat, Long)
        ↓
    Geohash  →  GEORADIUS / ZRANGEBYSCORE trên Redis ZSET
        ↓
  Danh sách Driver ID gần nhất
```

### Bước 4 — Xử lý Sharding (Quy mô lớn / Global)

Nếu hệ thống chạy toàn cầu, **không thể** nhét toàn bộ vị trí vào 1 node Redis. Cần **Sharding**:

- ✅ **Shard theo `City_ID` hoặc `Region_ID`** ← **Đúng**
- ❌ **Không shard theo `User_ID`** ← Sai, vì truy vấn định vị luôn mang tính **cục bộ** (người ở Hà Nội chỉ tìm tài xế ở Hà Nội).


## Thực tế thì dùng cả 2: 

- redis cho bài toán real-time matching khi 3-> 5 s tài xế gửi định vị về thì phải update toạ độ liên tục và cũng phải query liên tục

- Tính giá cuốc cxe cần các query phức tạp, các vị trí tĩnh, polygon để tính minimum shortest path: postgresql lúc này vẫn là lựa chọn tối ưu.