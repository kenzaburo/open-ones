<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--    Announcement -->
<div id="make-announcement">
  <form:form name="createAnnouncement" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
    <input id="requestTypeCd" name="requestTypeCd" type="hidden" value="Announcement"/>
    <form:hidden path="requestId"/>

		<div>
		  <label for="title" class="col_2">Thông báo</label>
		  <form:input path="title" id="title" type="text" required="required" class="col_8"/>
		</div>
		
		<div>
			<label for="request.contentAnnouncement" class="col_2">Nội dung</label>
			<form:textarea path="content" id="request.contentAnnouncement" class="ckeditor" rows="10"/>
			
		</div>
		
		<div>
          <label for="scopes" class="col_2">Phạm vi áp dụng</label>
          <select path="departmentCd" class="col_2 column">
				<option value="0">-- Tất cả --</option>
		        <c:forEach var="dept" items="${listDepartment}">
		            <option value="${dept.cd}">${dept.name}</option>
		        </c:forEach>
			</select>
        </div>
		
        <div>
          <label for="attachment1" class="col_2">Đính kèm</label>
          <input name="attachments[0]" type="file" class="col_8"/>
        </div>

      <div>
         <input type="submit" value="Save" class="button"/>
         <input type="reset" value="Reset" class="button" />
      </div>
  </form:form>
</div>