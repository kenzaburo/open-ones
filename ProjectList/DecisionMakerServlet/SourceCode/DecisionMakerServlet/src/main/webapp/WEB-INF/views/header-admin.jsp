<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Menu Horizontal -->
<ul class="menu">
  <li class="current"><a href=""><i class="icon-home"></i>Trang chủ</a></li>
  <li><a href="listAnnouncement"><i class="icon-bullhorn"></i>Thông báo</a></li>
  <li><a href="listRule"><i class="icon-legal"></i>Quy định</a></li>
  <li><a href="listTask"><i class="icon-eye-open"></i>Công việc</a>
    <ul>
      <li><a href="createTask"><i class="icon-magic"></i>Tạo việc mới</a></li>
      <li><a href="searchTask"><i class="icon-search"></i>Tìm công việc</a></li>
      <li class="divider"><a href="myOpenTask"><i class="icon-beer"></i>Việc đang làm của tôi</a></li>
    </ul></li>
  <li><a href="createRequest"><i class="icon-magic"></i>Tạo yêu cầu</a></li>

  <li class="right" style="display: inline-block;"><a href=""><i class="icon-user"></i>${pageContext.request.userPrincipal.name}</a>
    <ul>
      <li class="left"><a href="j_spring_security_logout"><i class="icon-coffee"></i>Thoát</a></li>
    </ul>
  </li>

  <li class="right" style="display: inline-block;"><a href=""><i class="icon-cog"></i>Cấu hình</a>
    <ul>
      <li class="left"><a href="master.department"><i class="icon-sitemap"></i>Phòng ban</a></li>
      <li class="left"><a href="master.template"><i class="icon-bookmark-empty"></i>Biểu mẫu</a></li>
    </ul>
  </li>
  

</ul>

<!-- End #header -->