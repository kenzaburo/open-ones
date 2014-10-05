<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!--    Rule -->
<div id="make-rule">
  <form:form name="createRule" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
    <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Rule"/>
    <form:hidden path="request.id"/>
        <div>
          <label for="title" class="col_2"><s:message code="Rule"/><span class="required">*</span></label>
          <form:input path="request.title" type="text" required="required" class="col_8"/>
          <form:errors path="request.title" class="error"/>
        </div>
        
        <div>
            <label for="content" class="col_2 left"><s:message code="Content"/></label>
            <form:textarea path="request.content" id="request.contentRule" class="ckeditor" rows="10"/>
        </div>
        
        <div>
          <label for="scopes" class="col_2"><s:message code="Scope"/></label>
          <form:select path="request.departmentCd" class="col_2">
              <option value="0">-- Tất cả --</option>
              <c:forEach var="dept" items="${listDepartment}">
                <c:choose>
                  <c:when test="${model.request.departmentCd == dept.cd}">
                    <option value="${dept.cd}" selected="selected">${dept.name}</option>
                  </c:when>
                  <c:otherwise>
                    <option value="${dept.cd}">${dept.name}</option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
          </form:select> 
        </div>
        
        <jsp:include page="_createRequest_Attach.jsp">
            <jsp:param name="requestType" value="Rule"/>
        </jsp:include>

      <div>
        <input type="submit" value='<s:message code="Save"/>' class="button"/>
        <input type="reset" value='<s:message code="Reset"/>' class="button"/>
      </div>
  </form:form>
</div>