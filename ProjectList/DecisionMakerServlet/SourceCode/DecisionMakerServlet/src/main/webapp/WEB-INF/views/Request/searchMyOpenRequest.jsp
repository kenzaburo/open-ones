<%--
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <th width="5px" title="Phân Loại">L</th>
        <th width="200px" nowrap="nowrap">Tiêu đề</th>
        <th width="50px" nowrap="nowrap">Người thực hiện</th>
        <th width="50px" nowrap="nowrap">Người quản lý</th>
        <th width="50px" nowrap="nowrap">Trạng thái</th>
        <th width="40px" nowrap="nowrap">Ngày tạo</th>
        <th width="40px" nowrap="nowrap">Ngày cập nhật</th>
        <th width="40px" nowrap="nowrap">Ngày kết thúc</th>
        <th width="5px" title="Thao tác">T</th>
      </tr>
    </thead>
    <c:forEach var="request" items="${requests}" varStatus="status">
      <tr>
        <td>
          <c:if test='${request.requesttypeCd == "Task"}'><i class="icon-magic"></i></c:if>
          <c:if test='${request.requesttypeCd == "Rule"}'><i class="icon-reorder"></i></c:if>
          <c:if test='${request.requesttypeCd == "Leave"}'><i class="icon-plane"></i></c:if>
          <c:if test='${request.requesttypeCd == "Announcement"}'><i class="icon-bullhorn"></i></c:if>
        </td>
        <td>
          <c:choose>
            <c:when test="${not empty request.filename1}">
                <a href="downloadFile?id=${request.id}" target="_blank" title="Tài liệu đính kèm: ${request.filename1}"><span class="glyphicon glyphicon-paperclip"></span></a>
            </c:when>
            <c:otherwise>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </c:otherwise>
          </c:choose>
          
          
          <a href="browseRequest?id=${request.id}" title='<s:message code="View_Details"/>'>${request.title}</a>
        </td>
        <td>${request.assigneeUsername}</td>
        <td>${request.managerUsername}</td>
        <td>
            <c:choose>
                <c:when test="${not empty request.status}"><s:message code="${request.status}"/></c:when>
                <c:otherwise>&nbsp;</c:otherwise>
            </c:choose>
            
          
        </td>
        <td><fmt:formatDate value="${request.created}" pattern="${DATE_FORMAT}"/></td>
        <td><fmt:formatDate value="${request.lastmodified}" pattern="${DATE_FORMAT}"/></td>
        <td><fmt:formatDate value="${request.enddate}" pattern="${DATE_FORMAT}"/></td>
        <td style="width: 10px; padding-top: 0px; padding-bottom: 0px; padding-left: 2px; padding-right: 0px;">
            <div class="input-group-btn">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="border: NONE" title="Thao tác"><i class="icon-cog"></i><span class="fa fa-caret-down"></span></button>
                <ul class="dropdown-menu">
                  <c:if test="${request.createdbyUsername == pageContext.request.userPrincipal.name}">
                    <li><a href="#" onclick='showConfirmDialog("${request.id}", "${request.title}")' title='<s:message code="Delete"/>'><s:message code="Delete"/></a></li>
                  </c:if>
                    
                  <li><a href="#"><s:message code="View_Details"/></a></li>
                  <li class="divider"></li>
                  <li><a href="#">Comment</a></li>
                </ul>
            </div><!-- /btn-group -->
        </td>
      </tr>
    </c:forEach>
  </table>
</div>


