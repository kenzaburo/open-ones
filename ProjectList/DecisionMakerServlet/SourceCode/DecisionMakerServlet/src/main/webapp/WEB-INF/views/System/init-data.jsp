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
<script type="text/javascript" src="resources/js/data-table.js"></script>

<jsp:include page="../_common/confirmDeleteRequest.jsp"/>

<div class="box-body table-responsive">
<table id="searchResult" class="table table-bordered table-hover">
  <thead>
    <tr>
      <th width="20px"><s:message code="No"/></th>
      <th width="80px"><s:message code="Code"/></th>
      <th width="100px"><s:message code="Name"/></th>
      <th width="200px"><s:message code="Description"/></th>
      <th width="80px"><s:message code="Enable"/></th>
    </tr>
  </thead>
  <tbody>
    <c:choose>
        <c:when test="${result == true}">
            <H3><s:message code="Init_data_success"/></H3>
        </c:when>
        <c:otherwise>
            <label class="error">
            <s:message code="Init_data_no_success"/>
            </label>
        </c:otherwise>
    </c:choose>
    <c:forEach var="reqType" items="${lstRequestType}" varStatus="status">
      <tr>
        <td>${status.count}.</td>
        <td>${reqType.cd}</td>
        <td>${reqType.name}</td>
        <td>${reqType.description}</td>
        <td>${reqType.enabled}</td>
        
      </tr>
    </c:forEach>
  </tbody>
</table>
</div>