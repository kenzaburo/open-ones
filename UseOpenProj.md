# Introduction #

Tham khảo:

http://sourceforge.net/projects/openproj

http://en.wikipedia.org/wiki/OpenProj


# Details #

**Cài đặt OpenProj trên Fedora 14**

Tải gói .RPM 1.4.2 tại http://sourceforge.net/projects/openproj/files/OpenProj%20Binaries/1.4/openproj-1.4-2.noarch.rpm/download?_test=goal

Cài đặt bằng cách thực thi lệnh rpm với quyền root:

|rpm -iv /đường dẫn/openproj-1.4-2.noarch.rpm|
|:-------------------------------------------|

Xem lại các files của gói openproj bằng lệnh:

|rpm -ql openproj|
|:---------------|

Tìm file có đuôi .desktop (vd: /usr/share/applications/openproj.desktop).
Bạn chép file này vào thư mục Desktop của user. Vd dùng lệnh sau trong cửa sồ console của user:

|cp /usr/share/applications/openproj.desktop ~/Desktop|
|:----------------------------------------------------|