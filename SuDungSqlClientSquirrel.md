# Introduction #
Tải và cài đặt chương trình tại http://www.squirrelsql.org. Phần chi tiết bên dưới giúp bạn làm quen SQL Squirrel bằng cách kết nối vào Oracle 10g Express ([Hướng dẫn cài đặt](http://code.google.com/p/open-ones/wiki/CaiDatOracleExpress)).

  * Cấu hình JDBC
  * Tạo alias để kết nối vào CSDL

# Chuẩn bị #
Nếu Oracle 10g Express chạy trên máy chủ khác với máy đang chạy SQuirrel SQL Client, bạn cần vào máy chủ chép file ojdbc14.jar (Mặc định tại C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib\ojdbc14.jar) lên máy chạy SQuirrel SQL Client.

# Chi tiết #
Sau khi khởi động SQuirrel SQL Client, bạn cần cấu hình JDBC cho loại CSDL. Ví dụ bạn đã cài đặt Oracle 10g Express trên cùng máy chạy SQuirrel SQL Client. File JDBC mặc định tại C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib\ojdbc14.jar

### Bước 1: Cấu hình JDBC ###
![http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/squirrel_conf.png](http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/squirrel_conf.png)

Bấm chọn thẻ Extra Class Path, bấm nút Add rồi chọn file ojdbc14.jar. Bấm OK để kết thúc.
![http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/squirrel_add_jdbc_ora.png](http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/squirrel_add_jdbc_ora.png)

Sau khi cấu hình thành công, biểu tượng Stick màu xanh hiển thị phía trước mục Oracle Thin Driver như hình bên dưới:

![http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/squirrel_add_jdbc_ora_ok.png](http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/squirrel_add_jdbc_ora_ok.png)

### Bước 2: Tạo Alias để kết nối vào CSDL ###