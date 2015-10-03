# Giới thiệu #

Môi trường sử dụng: Thunderbid


# Chi tiết #

**Thunderbird trên HĐH Fedora 15,16**

Khi bạn dùng mail server là IMAP, khi gởi email có thể bị báo lỗi "There was an error copying the message to the Sent folder. Retry?".

Có thể email đã gởi thành công nhưng bản sao không thể lưu lại trong thư mục "Sent".

**Giải quyết:** Tạo một thư mục trên "Local" để lưu các email đã gởi.

**Hướng dẫn:**


Bước 1) Tạo một thư mục "Sent Item" trong "Local Folders"

Bước 2) Vào menu Edit > Account Settings, vào mục "Copies & Folders" của tài khoản email tương ứng, trong phần "When sending messages, automatically", mục "Place a copy in" mặc định được chọn là "Sent" folder on: <tài khoản email>, kích chọn Other với thư mục đã tạo.