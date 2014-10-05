<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- bootstrap 3.0.2 -->
<link href="resources/AdminLTE/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="resources/AdminLTE/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="resources/AdminLTE/css/ionicons.min.css" rel="stylesheet" type="text/css" />

<!-- DATA TABLES -->
<link href="resources/AdminLTE/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        
<!-- jQuery -->
<script src="resources/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<!-- Bootstrap -->
<script src="resources/AdminLTE/js/bootstrap.min.js" type="text/javascript"></script>
<!-- DATA TABES SCRIPT -->
<script src="resources/AdminLTE/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
<script src="resources/AdminLTE/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>

<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">

<%-- Process confirmation delete request --%>
<script type="text/javascript" src="resources/js/confirmFunction.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>

<!-- page script -->
<script type="text/javascript">
    $(function() {
        $('#searchResult').dataTable({
            "bPaginate": true,
            "bLengthChange": false,
            "bFilter": false,
            "bSort": true,
            "bInfo": true,
            "bAutoWidth": false
        });
    });
</script>
<jsp:include page="../_common/confirmDeleteRequest.jsp"/>

<div class="box-body table-responsive">
<table id="searchResult" class="table table-bordered table-hover">
  <thead>
    <tr>
      <th width="20px"><s:message code="No"/></th>
      <th width="500px"><s:message code="Announcement"/></th>
      <th width="200px"><s:message code="Attach"/></th>
      <th><s:message code="Action"/></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="request" items="${lstAnnouncement}" varStatus="status">
      <tr>
        <td>${status.count}.</td>
        <td><a href="#" onclick='viewRequestContent("viewAnnouncement?id=${request.id}", "<s:message code="Announcement_content"/>")'>${request.title}</a></td>
        <td>
          <c:if test="${not empty request.filename1}">
            <a href="downloadFile?id=${request.id}" target="_blank" title='<s:message code="Attachment"/>'>${request.filename1}</a>
          </c:if>
        </td>
        <td>
<!--           <a href="#" title="Bạn thích thông báo này"><i class="icon-thumbs-up"></i></a> -->
          <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="editRequest?id=${request.id}" title="Sửa"><i class="icon-edit"></i></a>
            <a href="#" onclick='showConfirmDialog("${request.id}", "${request.title}")' title="Xóa"><i class="icon-remove"></i></a>
           </sec:authorize>
         </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
</div>