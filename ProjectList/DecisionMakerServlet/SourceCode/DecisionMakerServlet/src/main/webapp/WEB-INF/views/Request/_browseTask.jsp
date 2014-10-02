<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<link type="text/css" href="resources/css/app-validation.css" rel="stylesheet">

<fmt:formatDate value="${model.request.startdate}" pattern="dd-MM-yyyy" var="startdate"/>
<fmt:formatDate value="${model.request.enddate}" pattern="dd-MM-yyyy" var="enddate"/>

<!-- Task -->
<div id="browse-task">
		<div>
  		  <H5>${model.request.title}</H5>
		</div>
        <div>
          <label for="status" class="col_2">Trạng thái:</label>
          <label for="status" class="col_2">${model.request.status}</label>
        </div>

		<div>
			<label for="content" class="col_2 left">Nội dung:</label>
			<label class="col_8">${model.request.content}</label>
		</div>

		<div>
		 	<label for="assigneeUsername" class="col_2">Người thực hiện:</label>
		 	<label class="col_8">${model.request.assigneeUsername}</label>
		</div>
		<div>
            <label for="managerUsername" class="col_2">Người quản lý:</label>
            <label class="col_8">${model.request.managerUsername}</label>
		</div>
		<div>
            <label for="startDate" class="col_2">Ngày bắt đầu:</label>
            <label class="col_2">${startdate}</label>
            
            <label for="endDate" class="col_2">Ngày kết thúc:</label>
            <label class="col_2">${enddate}</label>
		</div>
		<div>
		 	<label for="label" class="col_2">Nhãn:</label>
		 	<label class="col_8">${model.request.label1}</label>
		</div>
		<div>
		 	<label for="duration" class="col_2">Thời lượng:</label>
		 	<label class="col_2">${model.request.duration} <span>${model.durationUnitName}</span></label>
		</div>

		<div id="attachment">
		  <label for="attachment0" class="col_2">Đính kèm:</label>
          <label class="col_8">
            <c:choose>
                <c:when test="${not empty model.request.filename1}">
                  <a href="downloadFile?id=${model.request.id}" target="_blank" title="Download file đính kèm">${model.request.filename1}</a>
                </c:when>
                <c:otherwise>
                  <input name="attachments[0]" type="file" class="col_8"/>
                </c:otherwise>
            </c:choose>
          </label>
        </div>
</div>