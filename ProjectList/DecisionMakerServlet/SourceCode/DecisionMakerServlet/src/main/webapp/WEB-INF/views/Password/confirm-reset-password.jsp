<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- Display the result/error --%>
  <c:if test='${(not empty result) && (result == true)}'>
    <div id="" class="notice success">
        <i class="icon-ok icon-large"></i><s:message code="Message_reset_password_success"/>!
        <a href="#close" class="icon-remove"></a>
    </div>
  </c:if>
  
  <%-- Display error when user send request hyperlink from email --%>
  <c:if test='${(not empty result) && (result == false)}'>
    <div id="" class="notice error">
        <i class="icon-remove-sign icon-large"></i><s:message code="Message_invalid_request"/>!
        <br/>
        <c:if test="${not empty desc}">
            <s:message code="${desc}"/>
        </c:if>
        <a href="#close" class="icon-remove"></a>
    </div>
  
  </c:if>
  
  <%-- Display form for input new password --%>
    <form:form class="horizontal" enctype="multipart/form-data" action="confirmResetPassword" modelAttribute="model" method="POST">
      <div class="form-group">
        <label for="newPassword"><s:message code="Username"/>:</label>
        <form:input path="newPassword" type="text" class="form-control"/>
      </div>
      <div class="form-group">
        <label for="confirmNewPassword"><s:message code="confirmNewPassword"/>:</label>
        <form:password path="confirmNewPassword" class="form-control"/>
      </div>
    </form:form>
