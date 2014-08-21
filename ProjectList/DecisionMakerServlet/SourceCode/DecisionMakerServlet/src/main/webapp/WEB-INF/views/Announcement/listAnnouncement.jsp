<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">


<table class="sortable">
  <thead>
    <tr>
      <th>STT</th>
      <th>Thông báo</th>
      <th>Đính kèm</th>
      <th>Hành động</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="request" items="${lstAnnouncement}">
      <tr>
        <td></td>
        <td><a href="">${request.title}</a></td>
        <td></td>
        <td>
          <a href="#" title="Bạn thích thông báo này"><i class="icon-thumbs-up"></i></a>
          <s:authorize access="hasRole('ROLE_ADMIN')">
            <a href="#" title="Sửa"><i class="icon-edit"></i></a>
            <a href="#" title="Xóa"><i class="icon-remove"></i></a>
           </s:authorize>
         </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
