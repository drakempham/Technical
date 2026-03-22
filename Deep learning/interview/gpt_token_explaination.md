Fused QKV projection
Trong Transformer, từ input ta thường tạo ra 3 tensor:

Q = Query
K = Key
V = Value
Cách thường:

q = Wq(x)
k = Wk(x)
v = Wv(x)
Tức là 3 phép chiếu tuyến tính riêng.

Fused QKV projection nghĩa là gộp 3 phép này thành 1 phép linear lớn:

qkv = W(x)
rồi tách ra thành q, k, v.

Lợi ích:

Ít kernel launch hơn trên GPU
Nhanh hơn
Tận dụng bộ nhớ/cache tốt hơn
Ý chính: 3 bước riêng -> 1 bước gộp.

Flash Attention (F.scaled_dot_product_attention)
Attention bình thường cần tạo ma trận attention khá lớn, rất tốn:

VRAM
băng thông bộ nhớ
thời gian
Flash Attention là cách tính attention tối ưu hơn, tránh lưu quá nhiều tensor trung gian.

Trong PyTorch, F.scaled_dot_product_attention(...) có thể tự dùng kernel tối ưu kiểu Flash Attention nếu phần cứng/phần mềm hỗ trợ.

Lợi ích:

Nhanh hơn
Ít tốn bộ nhớ hơn
Rất hữu ích khi sequence dài
Ý chính: vẫn là attention, nhưng được tính theo cách thông minh và tiết kiệm hơn.

GELU activation
Đây là hàm kích hoạt, thường dùng trong phần feed-forward của Transformer.

So với ReLU:

ReLU: cắt cứng mọi giá trị âm về 0
GELU: làm mềm hơn, không cắt gắt
Trực giác:

ReLU quyết định kiểu "bật/tắt"
GELU quyết định kiểu "mượt hơn"
Lợi ích:

Phù hợp với Transformer/GPT
Thường cho chất lượng tốt hơn ReLU
Ý chính: thay activation cho "chuẩn Transformer" hơn.

Gradient Clipping
Khi backprop, gradient đôi lúc quá lớn, gây:

train không ổn định
loss nhảy mạnh
dễ explode gradient
Gradient Clipping là giới hạn độ lớn của gradient trước khi update weight.

Ví dụ:

nếu norm gradient quá lớn, ta cắt nó về một ngưỡng như 1.0
Lợi ích:

train ổn định hơn
giảm nguy cơ gradient explosion
Ý chính: như một "van an toàn" cho quá trình học.

Mixed Precision (float16)
Thông thường model train với float32.

Mixed Precision nghĩa là dùng kết hợp:

float16 cho nhiều phép tính để tăng tốc
float32 cho một số phần quan trọng để giữ ổn định số học
Lợi ích:

train nhanh hơn trên GPU hiện đại
giảm dùng VRAM
có thể tăng batch size
Tên "mixed" là vì không phải mọi thứ đều dùng float16, mà trộn nhiều precision với nhau.

Ý chính: giảm độ chính xác số ở những chỗ an toàn để đổi lấy tốc độ và bộ nhớ.

Cosine LR Scheduler
LR là learning rate, tức tốc độ cập nhật tham số.

Thay vì giữ LR cố định, ta thay đổi LR theo thời gian train.

Cosine LR Scheduler giảm learning rate theo đường cong cosine:

lúc đầu LR tương đối cao
sau đó giảm dần mượt
cuối training LR rất nhỏ
Hình dung:

không giảm thẳng
mà giảm cong, mượt hơn
Lợi ích:

train ổn định hơn
thường hội tụ tốt hơn
hay dùng trong deep learning hiện đại
Ý chính: điều chỉnh learning rate theo lịch trình mượt để tối ưu quá trình học.

Tóm tắt cực ngắn
Fused QKV projection: gộp 3 phép chiếu Q,K,V thành 1 để nhanh hơn
Flash Attention: attention tối ưu, ít tốn VRAM hơn
GELU: activation mượt hơn ReLU, hợp Transformer
Gradient Clipping: chặn gradient quá lớn để train ổn định
Mixed Precision (float16): tăng tốc và giảm memory
Cosine LR Scheduler: giảm learning rate theo đường cong cosine
