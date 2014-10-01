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
          <label for="title" class="col_2">Quy định (*)</label>
          <form:input path="request.title" type="text" required="required" class="col_8"/>
          <form:errors path="request.title" class="error"/>
        </div>
        
        <div>
            <label for="content" class="col_2">Nội dung</label>
            <form:textarea path="request.content" id="request.contentRule" class="ckeditor" rows="10"/>
        </div>
        
        <div>
          	<label for="scopes" class="col_2">Phạm vi áp dụng</label>
          	<select id="departmentCd" class="col_2 column">
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
        <input type="submit" value='<s:message code="Save"/>' class="button"/>
        <input type="reset" value='<s:message code="Reset"/>' class="button"/>
      </div>
  </form:form>
</div>