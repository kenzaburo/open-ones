<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<link type="text/css" href="resources/css/app-validation.css" rel="stylesheet">

<!-- Leave -->
<div id="browse-leave">
		<div>
  		  <H5>${model.request.title}</H5>
		</div>
        <div>
          <label for="status" class="col_2"><s:message code="Status"/>:</label>
          <label for="status" class="col_2"><s:message code="${model.request.status}"/></label>
        </div>

		<div>
			<label for="content" class="col_2 left"><s:message code="Content"/>:</label>
			<label class="col_8">${model.request.content}</label>
		</div>

		<div>
		 	<label for="assigneeUsername" class="col_2"><s:message code="Assignee"/>:</label>
		 	<label class="col_8">${model.request.assigneeUsername}</label>
		</div>
		<div>
            <label for="managerUsername" class="col_2"><s:message code="Manager"/>:</label>
            <label class="col_8">${model.request.managerUsername}</label>
		</div>
		<div>
            <label for="startDate" class="col_2"><s:message code="Start_date"/>:</label>
            <label class="col_2"><fmt:formatDate value="${model.request.startdate}" pattern="${DATE_FORMAT}"/></label>
            
            <label for="endDate" class="col_2"><s:message code="End_date"/>:</label>
            <label class="col_2"><fmt:formatDate value="${model.request.enddate}" pattern="${DATE_FORMAT}"/></label>
		</div>
		<div>
		 	<label for="label" class="col_2"><s:message code="Label"/>:</label>
		 	<label class="col_8">${model.request.label1}</label>
		</div>

		<div id="attachment">
		  <label for="attachment0" class="col_2"><s:message code="Attach"/>:</label>
          <label class="col_8">
            <c:choose>
                <c:when test="${not empty model.request.filename1}">
                  <a href="downloadFile?id=${model.request.id}" target="_blank" title='<s:message code="Download_attachment"/>"'>${model.request.filename1}</a>
                </c:when>
                <c:otherwise>
                  <s:message code="NONE"/>
                </c:otherwise>
            </c:choose>
          </label>
        </div>
</div>