<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%-- Display the result/error --%>
  <c:if test='${(not empty model.result) && (model.result == true)}'>
    <div id="" class="notice success alert alert-success alert-dismissable">
        <i class="icon-ok icon-large"></i><s:message code="Message_reset_password_success"/>!
        <a href="#close" class="icon-remove"></a>
    </div>
  </c:if>
  <c:if test='${(not empty model.result) && (model.result == false)}'>
    <div id="" class="notice error alert alert-danger alert-dismissable">
        <i class="icon-remove-sign icon-large"></i>
            <c:choose>
                <c:when test="${not empty model.errorCode}">
                    <s:message code="${model.errorCode}"/>.
                </c:when>
                <c:otherwise>
                    <s:message code="Message_reset_password_fail"/>!
                </c:otherwise>
            </c:choose>
        
        <br/>
        <a href="#close" class="icon-remove"></a>
    </div>
  
  </c:if>


  <div>
  <div class="row" style="margin-top: 20px">
    <div class="container">
      <H3><s:message code="Reset_password"/></H3>
    </div>
  </div>
    <form:form class="horizontal" enctype="multipart/form-data" action="resetPassword" modelAttribute="model" method="POST">
      <div>
        <label for="email" class="col_2"><s:message code="Email"/></label>
        <form:input path="email" required="required" class="col_3"/>
        <form:errors path="email" class="error"/>
      </div>
      <div>
        <label class="col_2"></label>
        <input type="submit" value='<s:message code="Reset_password"/>' class="button"/>
      </div>
    </form:form>
  </div>
