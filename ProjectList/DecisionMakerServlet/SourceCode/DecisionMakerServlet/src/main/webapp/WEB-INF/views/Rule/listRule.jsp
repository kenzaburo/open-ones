<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../_common/_includeListScreen.jsp" %>
<%@ include file="../_common/confirmDeleteRequest.jsp" %>

<div class="box-body table-responsive">
<table id="searchResult" class="table table-bordered table-hover">
  <thead>
    <tr>
      <th width="20px"><s:message code="No"/></th>
      <th width="500px"><s:message code="Rule"/></th>
      <th width="200px"><s:message code="Attach"/></th>
      <th><s:message code="Action"/></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="request" items="${lstRule}" varStatus="status">
      <tr>
        <td>${status.count}.</td>
        <td><a href="#" onclick='viewRequestContent("viewRule?id=${request.id}", "<s:message code="Rule_content"/>")'>${request.title}</a></td>
        <td>
          <c:if test="${not empty request.filename1}">
            <a href="downloadFile?id=${request.id}" target="_blank" title='<s:message code="Attachment"/>'>${request.filename1}</a>
          </c:if>
        </td>
        <td>
<!--           <a href="#" title="Bạn thích qui định này"><i class="icon-thumbs-up"></i></a> -->
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