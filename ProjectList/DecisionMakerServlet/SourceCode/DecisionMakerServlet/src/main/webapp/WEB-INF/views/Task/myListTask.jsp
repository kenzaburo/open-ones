<%--
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--Search result --%>
<%@ include file="../Request/_searchScript.jsp" %>

<div>
  <H4><s:message code="My_task"/></H4>
</div>
<%-- Search condition --%>
<div>
  <form:form name="myListTask" class="horizontal" enctype="multipart/form-data" action="myListTask" modelAttribute="model" method="GET">
    <div class="visible" style="background: #eee">
        <label style="font-weight: normal"><s:message code="Task_I_do"/></label>
        <form:checkbox path="request.assigneeUsername" value="${pageContext.request.userPrincipal.name}" style="margin-top: 0px"/>
        
        <label style="font-weight: normal"><s:message code="Task_I_manage"/></label>
        <form:checkbox path="request.managerUsername" value="${pageContext.request.userPrincipal.name}" style="margin-top: 0px"/>
        <label style="font-weight: normal">&nbsp;&nbsp;<s:message code="Status"/></label>

        <form:select path="request.status" multiple="multiple" class="multiselect">
<%--             <option value="All"><s:message code="All"/></option> --%>
            <c:forEach var="status" items="${listStatus}">
              <c:choose>
                <c:when test="${fn:contains(model.request.status, status)}">
                  <option value="${status}" selected="selected"><s:message code="${status}"/></option>
                </c:when>
                <c:otherwise>
                  <option value="${status}"><s:message code="${status}"/></option>
                </c:otherwise>
              </c:choose>
                
            </c:forEach>
        </form:select>
        
        <button class="pill" type="submit"><i class="button icon-search"></i></button>
        
    </div>
  </form:form>
</div>

<%--Search result --%>
<%@ include file="../Request/_searchResult.jsp" %>