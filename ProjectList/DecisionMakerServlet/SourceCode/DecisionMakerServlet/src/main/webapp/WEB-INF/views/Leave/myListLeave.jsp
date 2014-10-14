<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<%@ include file="../Request/_searchScript.jsp" %>

<div>
  <H4><s:message code="Request_leave"/></H4>
</div>
<%-- Search condition --%>
<div>
  <form:form name="myListLeave" class="horizontal" enctype="multipart/form-data" action="myListLeave" modelAttribute="model" method="GET">
    <div class="visible" style="background: #eee">
        <label style="font-weight: normal"><s:message code="My_request_leave"/></label>
        <form:checkbox path="request.assigneeUsername" value="${pageContext.request.userPrincipal.name}" style="margin-top: 0px"/>
        
        <label style="font-weight: normal"><s:message code="My_request_leave_manage"/></label>
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



<div class="box-body table-responsive">
<table id="searchResult" class="table table-bordered table-hover">
  <thead>
    <tr>
      <th width="20px"><s:message code="No"/></th>
      <th width="90px"><s:message code="Leave_person"/></th>
      <th width="200px"><s:message code="Leave"/></th>
      <th width="300px"><s:message code="Reason"/></th>
      <th width="80px"><s:message code="From_date"/></th>
      <th width="80px"><s:message code="To_date"/></th>
      <th width="90px"><s:message code="Attach"/></th>
      <th width="80px"><s:message code="Status"/></th>      
      <th><s:message code="Action"/></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="request" items="${lstLeave}" varStatus="status">
      <tr>
        <td>${status.count}.</td>
        <td title="${request.assigneeName}">${request.assigneeUsername}</td>
        <td><a href="browseRequest?id=${request.id}">${request.title}</a></td>
        <td><label title="${request.content}">${request.content}</label></td>
        <td><fmt:formatDate value="${request.startdate}" pattern="${DATE_FORMAT}"/></td>
        <td><fmt:formatDate value="${request.enddate}" pattern="${DATE_FORMAT}"/></td>
        <td>
          <c:if test="${not empty request.filename1}">
            <a href="downloadFile?id=${request.id}" target="_blank" title="${request.filename1}"><span class="glyphicon glyphicon-paperclip"></span></a>
          </c:if>
        </td>
        <td><s:message code="${request.status}"/></td>
        <td>
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