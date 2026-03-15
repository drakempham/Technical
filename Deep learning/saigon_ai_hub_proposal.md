# Proposal Dự Án Tham Gia Saigon AI Hub

---

## I. Thông Tin Tổng Quan Dự Án

**Tên dự án:**
_Rebuilding cuBLAS: Nghiên cứu Tối ưu Hóa GEMM Kernel trên GPU Thế Hệ Mới (A100/H100)_

**Mô tả dự án:**
Dự án nghiên cứu quá trình tối ưu hóa từng bước của CUDA GEMM kernel (General Matrix Multiplication) — phép nhân ma trận tổng quát, nền tảng của mọi mô hình deep learning. Xuất phát từ một implementation naive đến các kiến trúc tiên tiến như Tensor Core pipeline với `cp.async` (A100/SM80) và TMA + WGMMA (H100/SM90), dự án nhằm trả lời câu hỏi: _điều gì thực sự giới hạn hiệu năng GPU và làm thế nào để tiếp cận giới hạn lý thuyết của phần cứng?_

Kết quả hiện tại (trên T4 GPU): 10 kernel hoàn chỉnh, đạt từ **465 GFLOP/s** (Naive) lên **3,304 GFLOP/s** (Vectorized Register Tiling). Giai đoạn tiếp theo cần A100 để triển khai `cp.async` hardware-async pipeline — bước đột phá quyết định để vượt qua "memory wall" và tiếp cận **>50 TFLOP/s** throughput thực tế.

**Thời gian thực hiện:**

- Bắt đầu: Tháng 04/2025
- Kết thúc dự kiến: Tháng 09/2025
- Tổng thời lượng: **6 tháng**

**Tổ chức và nhân sự:**

| Họ tên   | Chức vụ                | Tổ chức   | Email   | SĐT   |
| -------- | ---------------------- | --------- | ------- | ----- |
| [Họ tên] | Chủ nhiệm / Researcher | [Tổ chức] | [email] | [sđt] |

---

## II. Xuất Xứ & Sự Cần Thiết Của Dự Án

### Xuất xứ dự án

Dự án phát triển từ nhu cầu tự nghiên cứu GPU performance engineering, bắt đầu bằng việc tái xây dựng từng lớp tối ưu hóa của `cuBLAS` từ đầu. Repo GitHub [`rebuilding-cublas`](https://github.com/MinhDrake/Technical) đã hoàn thành 10 kernel trên T4 GPU (SM75), có documentation đầy đủ về bottleneck và phân tích hiệu năng từng bước.

Dự án hiện **chưa có tài trợ** từ cơ quan/tổ chức nào.

---

### Tính cần thiết của dự án

**Tổng quan nghiên cứu:**

GEMM là primitive nền tảng của toàn bộ AI/ML hiện đại: Transformer attention, convolution, embedding lookup đều quy về matrix multiplication. Các thư viện như `cuBLAS`, `cuDNN`, và `FlashAttention` đều được xây dựng trên GEMM kernel tối ưu. Tuy nhiên, phần lớn tài liệu chỉ trình bày _kết quả cuối_ mà không giải thích _tại sao_ từng bước tối ưu lại có hiệu quả.

Đây là khoảng trống tồn tại trong cộng đồng AI Việt Nam: **thiếu tài liệu kỹ thuật sâu về GPU performance engineering bằng tiếng Việt.**

**Vấn đề kỹ thuật cốt lõi — Memory Wall:**

Tensor Core của A100 có thể thực hiện `16×16×16` matrix multiply trong **1–2 clock cycles**. Nhưng với T4, kết quả thực tế chỉ đạt **~4.2% throughput lý thuyết** (2.7 TFLOP/s / 65 TFLOP/s). Nguyên nhân: global memory bus (320 GB/s) không feed data đủ nhanh cho Tensor Core. Giải pháp — `cp.async` của Ampere (A100) — cho phép phần cứng tự động overlap memory transfer với compute, không cần warp stall. **Đây là tính năng chỉ có từ SM80 trở lên, không khả thi trên T4.**

**Cơ hội:**

| Tầng                      | Hiện tại (T4)   | Mục tiêu (A100) |
| ------------------------- | --------------- | --------------- |
| Vectorized TC Pipeline    | ~2.5 TFLOP/s    | -               |
| `cp.async` async prefetch | ❌ Không hỗ trợ | **>20 TFLOP/s** |
| TMA + WGMMA (H100)        | ❌              | **>60 TFLOP/s** |

---

### Tác động dự án

- **Giáo dục:** Tạo tài liệu kỹ thuật sâu về GPU kernel engineering bằng tiếng Việt — tài nguyên đang thiếu nghiêm trọng trong cộng đồng AI Việt Nam.
- **Kinh tế:** Kiến thức về GPU optimization trực tiếp ứng dụng vào tối ưu inference cost trong production AI systems (giảm compute cost ~10–30x là feasible).
- **Xã hội:** Nâng cao năng lực GPU programming cho engineers Việt Nam, giảm phụ thuộc vào black-box libraries.

---

## III. Mục Tiêu Dự Án

### Mục tiêu khoa học

1. Triển khai và benchmark **Kernel 11**: 128×128 block tile với `int4` vectorized loads trên A100, đóng vai trò bridge giữa T4 và Ampere architecture.
2. Triển khai **Kernel 12**: `cp.async` hardware-async prefetch pipeline (SM80+) — giải quyết memory wall bằng hardware-level pipelining.
3. Triển khai **Kernel 13**: TMA + WGMMA (SM90/H100) nếu infrastructure cho phép.
4. Phân tích định lượng từng bottleneck bằng **Nsight Compute** (memory throughput, SM utilization, stall reasons, roofline model).

### Mục tiêu ứng dụng

- **Throughput mục tiêu:** >20 TFLOP/s sustained trên A100 (>30% peak theoretical cho FP16).
- **Tài liệu hóa:** Xuất bản open-source documentation giải thích từng optimization cho engineers cấp intermediate.

### Tiêu chí thành công

| Chỉ số             | Baseline (T4) | Mục tiêu (A100) |
| ------------------ | ------------- | --------------- |
| Peak GFLOP/s       | 3,304         | >20,000         |
| TC Utilization     | ~4%           | >30%            |
| Memory Efficiency  | ~15% peak BW  | >60% peak BW    |
| Kernels hoàn chỉnh | 10            | 13              |

---

## IV. Sản Phẩm Dự Kiến

### 1. Sản phẩm khoa học

- **Technical report / preprint** documenting the full optimization sequence từ Naive → TMA+WGMMA, với Nsight Compute profiling data, roofline analysis, và hardware-level explanation cho mỗi kernel.
- Mục tiêu nộp: arXiv và/hoặc workshop tại hội nghị uy tín (SC, PPoPP, hoặc NeurIPS workshop về efficient ML).

### 2. Sản phẩm thử nghiệm (MVP)

**Kiến trúc kỹ thuật:**

```
rebuilding-cublas/
├── Kernel 01–10: T4 SGEMM series (đã hoàn thành)
├── Kernel 11: 128×128 tile + int4 (A100, FP16)
├── Kernel 12: cp.async async pipeline (A100, FP16)
├── Kernel 13: TMA + WGMMA (H100, FP16) [nếu khả thi]
├── benchmarks/: Automated benchmark runner
└── profiling/: Nsight Compute reports + roofline plots
```

**Bộ chỉ số đánh giá:**

| Metric                       | Công cụ đo         |
| ---------------------------- | ------------------ |
| GFLOP/s                      | CUDA event timing  |
| Memory bandwidth utilization | Nsight Compute     |
| SM occupancy                 | Nsight Compute     |
| Max absolute error           | So sánh với cuBLAS |
| Roofline efficiency          | Manual + Nsight    |

---

## V. Nội Dung và Phương Án Triển Khai

### Nội dung 1: Kernel 11 — 128×128 Block Tile + Vectorized Loads

**Vấn đề cần giải quyết:** Vectorized TC Pipeline (Kernel 10) chậm hơn Kernel 09 vì tile quá nhỏ (32×64), khiến 75% thread idle trong load phase. Cần tile lớn hơn để justify int4 loads.

**Phương pháp:**

- Tăng block tile lên 128×128, 16 warps/block.
- Dùng `int4` (128-bit) loads cho A và B tiles.
- Warp tile 64×32 để đủ memory concurrency saturate bandwidth.

**Các bước:**

1. Port Kernel 09 lên A100, validate correctness.
2. Implement 128×128 tile với `int4` loads.
3. Profile với Nsight Compute, tune occupancy.

---

### Nội dung 2: Kernel 12 — cp.async Hardware-Async Pipeline

**Vấn đề cần giải quyết:** Trên T4, memory loads block warp scheduler (warp stall). `cp.async` của Ampere cho phép issue một load và **tiếp tục compute ngay lập tức** — warp không bao giờ stall chờ memory.

**Phương pháp:**

- Thay `__syncthreads()` + global load bằng `cp.async` + `commit_group` + `wait_group`.
- Implement double buffer: 1 buffer đang compute, 1 buffer đang load tile tiếp theo.
- Dùng `__pipeline_memcpy_async` API (CUDA 11+).

**Các bước:**

1. Nghiên cứu PTX semantics của `cp.async`.
2. Implement và validate correctness.
3. Benchmark và so sánh stall profile trước/sau với Nsight Compute.

---

### Rủi ro và giải pháp

| Rủi ro                                   | Khả năng   | Giải pháp                                          |
| ---------------------------------------- | ---------- | -------------------------------------------------- |
| A100 không available đủ giờ              | Trung bình | Prioritize critical kernels trước, batch benchmark |
| `cp.async` complexity gây bugs khó debug | Cao        | Unit test từng async stage riêng biệt              |
| Occupancy thấp do register pressure      | Trung bình | Tune `maxrregcount`, chia kernel                   |

---

## VI. Kế Hoạch Thực Hiện

| Nội dung                                   | Thời gian | Kết quả mong đợi            | Nhân sự |
| ------------------------------------------ | --------- | --------------------------- | ------- |
| Port T4 kernels lên A100, validate         | Tháng 4   | 10 kernels pass trên A100   | [Tên]   |
| Kernel 11: 128×128 + int4 tiles            | Tháng 4–5 | >10 TFLOP/s, Nsight report  | [Tên]   |
| Kernel 12: `cp.async` double buffer        | Tháng 5–6 | >20 TFLOP/s, stall analysis | [Tên]   |
| Kernel 13: TMA + WGMMA (nếu H100 khả dụng) | Tháng 6–7 | >50 TFLOP/s                 | [Tên]   |
| Profiling, documentation, technical report | Tháng 7–9 | Bài viết + open-source repo | [Tên]   |

---

## VII. Nguồn Lực Cần Hỗ Trợ Từ Saigon AI Hub

### Cơ sở vật chất & thiết bị _(Ưu tiên cao nhất)_

> **GPU access: NVIDIA A100 (SM80) — tối thiểu 50–100 giờ GPU compute time.**

Đây là yêu cầu cốt lõi. `cp.async` và `cp.async.bulk` (TMA) là tính năng phần cứng chỉ có trên SM80+. Không có A100, Kernel 12–13 về bản chất là **không thể triển khai** trên T4 (SM75).

- CUDA toolkit 12.x, Nsight Compute license.
- Nếu possible: H100 access cho Kernel 13 (TMA + WGMMA).

### Nhân lực & mentoring

- Kết nối với engineers có kinh nghiệm GPU performance engineering hoặc compiler/CUDA backend.
- Feedback về research direction và write-up quality nếu target publication.

### Hỗ trợ từ đối tác

- Kết nối cộng đồng AI engineers Việt Nam để lan tỏa kết quả nghiên cứu.
- Hỗ trợ PR/trình bày kết quả tại meetup/hội thảo của Saigon AI Hub.
