# Giới thiệu #

Tôi thường làm việc trên các hệ điều hành khác nhau: Windows, Linux (Fedora), dữ liệu nhạy cảm thường được lưu trên USB, Laptop. Làm thế nào để bảo vệ chúng?


# Chi tiết #

Đã có nhiều bài viết hay về TrueCrypt như:
  * http://zxc232.wordpress.com/2010/03/16/ma-hoa-b%E1%BA%A3o-v%E1%BB%87-d%E1%BB%AF-li%E1%BB%87u-b%E1%BA%B1ng-truecrypt/
  * http://blogphanmem.com/download-truecrypt-bao-ve-du-lieu-o-flash-mot-cach-toan-dien/
  * http://www.spacesoft.vn/Ph%E1%BA%A7nm%E1%BB%81m/TrueCrypt/tabid/75/language/en-US/Default.aspx

Bài này chỉ tóm tắt và ghi chú các kinh nghiệm trong quá trình sử dụng TrueCrypt trên Windows và Linux (tôi đang dùng Fedora Linux phiên bản 14 và 15).

### Download ###
http://www.truecrypt.org/downloads

### Dùng TrueCrypt trong Fedora 14/15/16 ###
Nếu bạn đăng nhập vào Fedora bằng tài khoản root để sử dụng thì không có vấn đề gì. Tuy nhiên, điều này hiếm khi xảy ra mà thường là chúng ta dùng một tài khoản thường để làm việc. Để thuận tiện trong quá trình sử dụng TrueCrypt thì có một số gợi ý sau:

**Bước 1**: Cấu hình sudo cho phép account thường có thể chạy truecrypt với quyền root.
> Mở file "/etc/sudoers" thêm dòng cấu hình như sau:

| thachln ALL= (root) NOPASSWD: /usr/bin/truecrypt, /usr/bin/dolphin |
|:-------------------------------------------------------------------|


> Ý nghĩa:

> thachln: là tài khoản người dùng bạn muốn có thể dùng lệnh truecrypt, dophin với quyền root mà không cần nhập mật khẩu

**Bước 2**: Nên tạo một shortcut để khởi không Truecrypt với quyền root với lệnh sudo như sau:

| sudo -u root truecrypt |
|:-----------------------|

sudo -u root truecrypt

Để truy cập dữ liệu trong ổ đĩa của TrueCrypt, bạn cần chạy chương trình với quyền root. Trong bước một ở trên tôi đã cấu hình sudo cho phép tài khoản thachln chạy chương trình quản lý file Dolphin (File Manager) trong Fedora) với quyền root.