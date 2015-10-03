# Giới thiệu #
Tài liệu này hướng dẫn bạn triển khai gói phần mềm quản trị dự án FMS - FPT Software Management Suite phiên bản 1.0 mà FPT đã cung cấp cho cộng đồng CNTT Việt Nam trong năm 2009 với giấy phép mã nguồn mở BSD.

(Gói phần mềm FMS và bộ tài liệu CMMi có thể tải tại trang web của [Bộ Thông tin và truyền thông](http://ict-industry.gov.vn/Clients/Document/DownloadDocument.aspx?Me=Document&ID=1).)

Trong quá trình cài đặt để dùng thử công cụ FMS này, nhóm Open-Ones phát hiện phần hướng dẫn triển khai cơ sở dữ liệu (Oracle 9i) chưa được chi tiết, bộ script để tạo CSDL chưa được hoàn chỉnh. Vì vậy nhóm Open-Ones đã cập nhật và chia sẻ dưới đây.

Nội dụng:
  * Cài đặt Oracle 10g Express
  * Triển khai jBoss và bộ công cụ FMS

# Chi tiết #
### Bước 1) Cài đặt Oracle 10g Express ###

Để cài đặt CSDL cho FMS, bạn cần chuẩn bị sẵn một CSDL Oracle, một account có quyền DBA (thật ra chỉ cần một số quyền. Tuy nhiên để đơn giản thì cứ tạo một tài khoản có quyền DBA).

Sau đó bạn lấy bộ script đã được chỉnh sửa tại: http://open-ones.googlecode.com/files/FMS_sql_updated_20110818.zip

(Phiên bản gốc FMS 1.0 có 2 scripts trong thư mục sql: FMS\_All.sql, FMS\_Fix.sql. Việc thực thi toàn bộ nội dung 2 scripts này tương đối vất vả và mất thời gian)

Bạn xem file "README.txt" trong bộ sql chình sửa ở trên để biết cách tạo CSDL.

Đối với các dự án công ty vừa và nhỏ, đặc biệt các trung tâm dự án đào tạo thì bản Oracle Express là phù hợp (Xem các giới hạn của phiên bản Express tại http://download.oracle.com/docs/cd/B25329_01/doc/install.102/b25143/toc.htm#BABIECJA).

**Xem tài liệu hướng dẫn tài Oracle 10g Express** tại [đây](http://code.google.com/p/open-ones/wiki/CaiDatOracleExpress).

### Bước 2) Triển khai jBoss và bộ công cụ FMS ###
Phần hướng dẫn dưới đây sẽ giúp bạn cài đặt các gói (packages) của FMS vào jBoss. Bạn có thể download file này để dùng ngay mà không cần mất thời gian: http://open-ones.googlecode.com/files/jboss-5.0.1.GA-fms.zip.
Trong đó đã chuẩn bị đầy đủ các gói và cấu hình CSDL với Oracle:

Database: XE

Username: fmsuser

Password: fmsuser

Nếu bước 1 bạn đã cài đặt CSDL, tạo user theo đúng cấu hình trên và thực hiện các script để tạo dữ liệu thành công thì có thể bung gói zip này ra và khởi động jBoss ngay. Chờ jBoss khởi động xong thì dùng trình duyệt vào địa chỉ http://localhost:8080/FsoftInsight, đăng nhập bằng tài khoản sysadmin/sysadmin để khám phá FMS.


### Tự triển khai bộ công cụ FMS vào jBoss ###
Nếu máy bạn chưa cài Java thì hãy tham khảo bài 1 (Exercise 1) trong tài liệu [Hands-on Lab: Install JDK and Tomcat](http://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B4vjYnf6j5_4ZDMzY2NhM2MtMDBlMy00OWE1LWE4MDUtNGNkMWJlYzczMjAy&hl=en_US).


Tải bản jBoss 5.1.0 tại http://www.jboss.org/jbossas/downloads

Các nội dung khác trong tài liệu "Install.pdf" tương đối rõ ràng. Các bạn có thể xem và làm theo.
Ghi chú: Bạn nên đổi tên file DMS.war thành chữ thường dms.war.

Để gởi động và dùng jBoss, bạn nên tạo 2 batch script trong thư mục C:\jPackages\jboss-5.1.0.GA

---Script để khởi động jBoss---

cd bin

run.bat -b 0.0.0.0


---Script để tắt jBoss---

cd bin

shutdown.bat -S

### Tham khảo ###
Bạn nào mong muốn dùng thử ngay tool FMS thì có thể liên hệ nhóm để nhận CD chứa máy ảo VMWare đã cài đặt sẵn CentOS/RedHat + Oracle 10g Express, jBoss + FMS.

Theo dõi các phiên bản nâng cấp, sửa lỗi tại http://project360.googlecode.com.

Dịch vụ hỗ trợ triển khai, đào tạo xem tại http://open-ones.com.

Liên hệ: openonesadm@gmail.com

ĐT: 0908 550 642