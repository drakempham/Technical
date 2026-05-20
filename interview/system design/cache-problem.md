## Cache avalance 

Hệ thống Redis bị sập hoàn toàn (hoặc một lượng lớn key cùng hết hạn một lúc). Giống như một con đập bị vỡ, toàn bộ lượng traffic khổng lồ vốn được Cache gánh hộ nay đột ngột đổ ập xuống Database gốc.


Tại sao Database lại dễ sập đến thế?

Bình thường, cấu hình của hệ thống được tính toán dựa trên việc có Cache đứng trước:

Cache (Redis) lưu trên RAM, có thể chịu được 100.000 requests/giây (QPS).

Database (Postgres/MySQL) lưu trên ổ cứng, chỉ chịu được khoảng 2.000 - 5.000 QPS.

Khi Cache biến mất, 100.000 request kia không giảm đi mà chạy thẳng xuống Database. Định luật I/O của ổ cứng sẽ khiến Database bị nghẽn cổ chai ngay lập tức, CPU nhảy lên 100%, các kết nối (Connection Pool) bị cạn kiệt, dẫn đến sập toàn hệ thống.


-> Dùng degraded performance / ciruit breaker/ L1 cache khi muốn giảm thiểu xuống db

## cache stampede/ thundering herd

Hiện tượng này xảy ra khi Redis vẫn sống nhăn răng, nhưng có một Key cực kỳ hot (ví dụ: thông tin của một Idol đang livestream) bị hết hạn (Expired). Ngay tại mili-giây key đó biến mất, hàng vạn request đồng thời đọc không thấy key (Cache Miss) và cùng lúc lao xuống Database để tính toán lại dữ liệu cho key đó.


