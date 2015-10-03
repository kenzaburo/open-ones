# Giới thiệu #

Oracle 10g Express là phiên bản miễn phí. Rất thích hợp cho việc đào tạo, triển khai các dự án nhỏ.
Tài liệu này giúp các bạn cài đặt và thực hiện một số lệnh cơ bản của Oracle.
Phiên bản Oracle được minh họa: Oracle 10g Express 10.2.0. Tải tại http://www.oracle.com/technetwork/database/express-edition/downloads/index.html

# Chi tiết #

Sau khi tải file OracleXEUniv.exe về, thực thi để cài đặt theo hướng dẫn.

## Đổi port mặc định của CSDL XDB 8080 thành 6060 ##
Mặc định Oracle 10g Express có sẵn một máy chủ HTTP CSDL XML chạy ở cổng 8080.
Nếu máy của bạn chạy nhiều ứng dụng có cổng 8080 như Tomcat/JBoss thì nên thực hiện lệnh đổi cổng mặc định XDB của Oracle. Bên dưới sẽ hướng dẫn cách đổi port 8080 thành 6060.

Khởi động chương trình SQL Command line của Oracle. Kết nối vào database bằng lệnh

**connect sys/$password as sysdba;**

$password là mật khẩu của system của bạn khi cài Oracle Express

Sau đó thực thi 2 đoạn lệnh dưới đây bằng cách sao chép và dán vào cửa sổ lệnh sqlplus

begin
> dbms\_xdb.sethttpport('6060');
end;
/
select dbms\_xdb.gethttpport as "HTTP-Port", dbms\_xdb.getftpport as "FTP-Port" from dual;
/

Nếu muốn tắt (disable) máy chủ HTTP thì thực hiện đoạn lệnh sau:

begin
> dbms\_xdb.sethttpport(0);
end;
/

## Cách dán (paste) lệnh vào cửa sổ lệnh sqlplus ##
Bấm vào biểu tượng SQL> ở góc trái trên cửa sổ lệnh, chọn Edit > Paste như hình bên dưới:

![http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/oracle_cp.png](http://i1083.photobucket.com/albums/j387/thachln/Open-Ones/oracle_cp.png)