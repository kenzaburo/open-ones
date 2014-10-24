<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link type="text/css" href="resources/css/app-validation.css" rel="stylesheet">

<%-- Display the result/error --%>
  <c:if test='${(not empty result) && (result == true)}'>
    <div id="" class="notice success">
        <i class="icon-ok icon-large"></i><s:message code="Message_set_password_success"/>!
        <a href="#close" class="icon-remove"></a>
    </div>
  </c:if>
  
  <%-- Display error when user send request hyperlink from email --%>
  <c:if test='${(not empty result) && (result == false)}'>
    <div id="" class="notice error">
        <i class="icon-remove-sign icon-large"></i><s:message code="Message_set_password_fail"/>!
        <br/>
        <a href="#close" class="icon-remove"></a>
    </div>
  
  </c:if>
<c:choose> 
	<c:when test="${(not empty model.validKey) && (model.validKey == true)}"> 
	  <div class="row">
	    <div class="col-lg-3">
		  <%-- Display form for input new password --%>
		    <form:form class="horizontal" enctype="multipart/form-data" action="confirmResetPassword" modelAttribute="model" method="POST">
		      <form:hidden path="email"/>
		      <form:hidden path="validKey"/>
		      <div class="form-group">
		        <label for="newPassword"><s:message code="New_password"/>:</label>
		        <form:password path="newPassword" class="form-control"/>
		        <form:errors path="newPassword" class="error"/>
		      </div>
		      <div class="form-group">
		        <label for="confirmNewPassword"><s:message code="Confirm_new_password"/>:</label>
		        <form:password path="confirmNewPassword" class="form-control"/>
		        <form:errors path="confirmNewPassword" class="error"/>
		        <form:errors path="matchedPassword" class="error"/>
		        
		      </div>
		      
		      <input type="submit" value='<s:message code="Set_new_password"/>' class="button"/>
		    </form:form>
	    </div>
	  </div>
	</c:when>
	<c:otherwise>
	  <i class="icon-remove-sign icon-large"></i><s:message code="Message_invalid_request"/>!
	</c:otherwise>
</c:choose>