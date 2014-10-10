<%--
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--Search result --%>
<%@ include file="_searchScript.jsp" %>

<div>
  <H4><s:message code="Search"/></H4>
</div>
<%-- Search condition --%>
<div>
  <form:form name="searchRequest" class="horizontal" enctype="multipart/form-data" action="searchRequest" modelAttribute="model" method="GET">
    <div class="visible" style="background: #eee">
        <label for="request.requesttypeCd"><s:message code="Request_type"/></label>
        <form:select path="request.requesttypeCd" multiple="multiple" class="multiselect">
<%--            <option value="All"><s:message code="All"/></option> --%>
           <c:forEach var="reqType" items="${listRequestType}">
             <c:choose>
               <c:when test='${fn:contains(model.request.requesttypeCd, reqType.cd)}'>
                 <option value="${reqType.cd}" selected="selected">${reqType.name}</option>
               </c:when>
               <c:otherwise>
                 <option value="${reqType.cd}">${reqType.name}</option>
               </c:otherwise>
             </c:choose>
           </c:forEach>
        </form:select>
        
        <label><s:message code="Assignee"/></label>
        <form:select path="request.assigneeUsername" multiple="multiple" class="multiselect">
<%--             <option value="All"><s:message code="All"/></option> --%>
            <c:forEach var="user" items="${listUser}">
              <c:choose>
                <c:when test="${fn:contains(model.request.assigneeUsername, user.username)}">
                  <option value="${user.username}" selected="selected">${user.username}</option>
                </c:when>
                <c:otherwise>
                  <option value="${user.username}">${user.username}</option>
                </c:otherwise>
              </c:choose>
                
            </c:forEach>
        </form:select>
        
        <label><s:message code="Status"/></label>
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
<%@ include file="_searchResult.jsp" %>