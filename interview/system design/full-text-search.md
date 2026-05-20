# I. Full-Text Search

> **Ví dụ ứng dụng:** Tìm kiếm sản phẩm trên E-commerce, tìm bài viết.

Khi người dùng gõ cụm từ không chính xác, gõ sai chính tả (**Fuzzy Search**), hoặc muốn tìm các từ có liên quan, các database truyền thống dùng **B-Tree** sẽ bất lực vì toán tử `LIKE '%keyword%'` bắt buộc hệ thống phải **scan toàn bộ bảng**.

---

## 1. Database khuyên dùng trong Interview

| Lựa chọn | Database | Ghi chú |
|---|---|---|
| 🥇 Gold Standard | **Elasticsearch** hoặc **OpenSearch** | Hệ thống lớn |
| 🥈 Thay thế | **PostgreSQL** (GIN Index + `tsvector`) | Hệ thống vừa/nhỏ |

---

## 2. Quy trình xử lý trong kiến trúc hệ thống (Step-by-Step)

Nếu phỏng vấn hỏi cách tích hợp Elasticsearch vào hệ thống, hãy trình bày theo **4 bước** sau:

### Bước 1 — Tách biệt Database

- **Không** dùng Elasticsearch làm database chính (**Source of Truth**).
- Dữ liệu gốc vẫn ghi vào **SQL (MySQL / Postgres)** để đảm bảo tính toàn vẹn (**ACID**).

### Bước 2 — Đồng bộ dữ liệu (Data Synchronization)

Khi có sản phẩm/bài viết mới ghi vào SQL, làm sao để Elasticsearch biết? Có **2 cách**:

- **Cách dễ:** Ứng dụng ghi vào SQL → bắn một **Event** vào **Message Queue** (Kafka / RabbitMQ) → một **Worker** đọc Event và ghi vào Elasticsearch.

- **Cách tối ưu** ⭐ *(Điểm cộng lớn)*: Dùng **CDC (Change Data Capture)** như **Debezium** để đọc trực tiếp **Binlog** của SQL và đồng bộ sang Elasticsearch một cách **bất đồng bộ (Asynchronously)**.

### Bước 3 — Cấu trúc dữ liệu bên dưới (Inverted Index)

Giải thích cách Elasticsearch lưu dữ liệu:

```
Truyền thống:   Document → [Từ khóa]
Inverted Index: Từ khóa  → [Document ID, Document ID, ...]
```

Elasticsearch **tách câu thành từng từ đơn lẻ (Tokenization)** và lưu theo dạng `Từ khóa → Danh sách Document ID` chứa từ đó.

### Bước 4 — Thực hiện truy vấn

```
User Search → Elasticsearch (lấy danh sách ID nhanh nhờ Inverted Index)
                    ↓
             SQL / Cache (lấy thông tin chi tiết nếu cần)
```

Ứng dụng gọi trực tiếp vào **Elasticsearch** → nhận danh sách ID rất nhanh → quay lại **SQL hoặc Cache** để lấy thông tin chi tiết.