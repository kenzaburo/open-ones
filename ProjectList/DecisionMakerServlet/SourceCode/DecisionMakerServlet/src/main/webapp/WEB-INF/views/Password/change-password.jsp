<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<link type="text/css" href="resources/css/app-validation.css" rel="stylesheet">
<%-- Display the result/error --%>
  <c:if test='${(not empty result) && (result == true)}'>
    <div id="" class="notice success">
        <i class="icon-ok icon-large"></i><s:message code="Change_password_success"/>!
        <a href="#close" class="icon-remove"></a>
    </div>
  </c:if>
  <c:if test='${(not empty result) && (result == false)}'>
    <div id="" class="notice error">
        <i class="icon-remove-sign icon-large"></i><s:message code="Change_password_no_success"/>!
        <br/>
        <c:if test="${not empty errorCode}">
            <s:message code="${errorCode}"/>
        </c:if>
        <a href="#close" class="icon-remove"></a>
    </div>
  
  </c:if>


  <div>
    <H4><s:message code="Change_password"/></H4>
    <form:form class="horizontal" enctype="multipart/form-data" action="changePassword" modelAttribute="model" method="POST">
      <div>
        <label for="oldPassword" class="col_2"><s:message code="Old_password" /></label>
        <form:password path="oldPassword" required="required" class="col_3"/>
        <form:errors path="oldPassword" class="error"/>
      </div>
      <div>
        <label for="newPassword" class="col_2"><s:message code="New_password" /></label>
        <form:password path="newPassword" required="required" class="col_3"/>
        <form:errors path="newPassword" class="error"/>
      </div>
      <div>
        <label for=confirmNewPassword class="col_2"><s:message code="Confirm_new_password" /></label>
        <form:password path="confirmNewPassword" required="required" class="col_3"/>
        <form:errors path="confirmNewPassword" class="error"/>
        <form:errors path="matchedPassword" class="error"/>
      </div>
      <div>
        <label class="col_2"></label>
        <input type="submit" value='<s:message code="Change"/>' class="button"/>
      </div>
    </form:form>
  </div>
