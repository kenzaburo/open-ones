<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">
<script>
  function viewRequestContent(viewUrl) {
    // alert("Open windows url=" + viewUrl);
    if (window.showModelessDialog) {        // Internet Explorer
        showModelessDialog (viewUrl, window, "dialogWidth:980px; dialogHeight:620px");
    } 
    else {
        window.open (viewUrl, "Nội dung qui định","width=980, height=620, alwaysRaised=yes");
    }
  }

  function confirmDelete(requestId, requestTitle) {
    var confirmVal = confirm("Bạn chắc chắn muốn xóa qui định này?<br/>" + 
          "[" + requestId + "]" + requestTitle);
    if (confirmVal) {
    } else {
    }
  }
</script>
<table class="sortable">
  <thead>
    <tr>
      <th width="20px">STT</th>
      <th width="500px">Qui định</th>
      <th width="200px">Đính kèm</th>
      <th>Hành động</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="request" items="${lstRule}" varStatus="status">
      <tr>
        <td>${status.count}.</td>
        <td><a href="#" onclick='viewRequestContent("viewRule?id=${request.id}")'>${request.title}</a></td>
        <td></td>
        <td>
          <a href="#" title="Bạn thích qui định này"><i class="icon-thumbs-up"></i></a>
          <s:authorize access="hasRole('ROLE_ADMIN')">
            <a href="editRequest?id=${request.id}" title="Sửa"><i class="icon-edit"></i></a>
            <a href="#" onclick='confirmDelete("${request.id}", "${request.title}")' title="Xóa"><i class="icon-remove"></i></a>
           </s:authorize>
         </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
