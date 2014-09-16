<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--    Task -->
<div id="make-task">
    <form:form name="createTask" id="createTask" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
      <input id="requestTypeCd" name="requestTypeCd" type="hidden" value="Task"/>
      <form:hidden path="requestId"/>
		<div>
		  <label for="title" class="col_2">Tiêu đề</label>
		  <form:input path="title" id="title" type="text" required="required" class="col_8"/>
          <form:errors path="title"></form:errors>
		</div>
		<div>
			<label for="content" class="col_2 left">Nội dung</label>
            
			<form:textarea path="content" id="content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="taskContent" rows="15" placeholder="Mô tả chi tiết công việc"></form:textarea>
		</div>

		<div>
			
		 	<label for="assigneeAccount" class="col_2">Người thực hiện</label>
		 	<form:select path="assigneeAccount" class="col_8 chosen-select">
                <c:forEach var="user" items="${listUser}">
                  <c:choose>
                    <c:when test="${assigneeAccount == user.username}">
                      <option value="${user.username}" selected="selected">${user.username}</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${user.username}">${user.username}</option>
                    </c:otherwise>
                  </c:choose>
                    
                </c:forEach>
            </form:select>
		</div>
		<div>
            <label for="managerAccount" class="col_2">Người quản lý</label>
            <form:select path="managerAccount" class="chosen-select col_8">
                <c:forEach var="user" items="${listUser}">
                  <c:choose>
                    <c:when test="${managerAccount == user.username}">
                        <option value="${user.username}" selected="selected">${user.username}</option>
                    </c:when>
                    <c:otherwise>
                      <c:if test="${user.username != pageContext.request.userPrincipal.name}">
                        <option value="${user.username}">${user.username}</option>
                      </c:if>
                    </c:otherwise>
                  </c:choose>
         		</c:forEach>
            </form:select>
		</div>
		<div>
            <label for="listWatcher" class="col_2">Chia sẻ</label>
            <form:select path="listWatcher" name="listWatcher" class="col_8 chosen-select" multiple="true">
                <c:forEach var="user" items="${listUser}">
                    <option value="${user.username}">${user.username}</option>
                </c:forEach>
            </form:select>
		</div>
		<div>
            <label for="startDate" class="col_2">Ngày bắt đầu</label>
            <form:input path="startDate" id="request_startdate" size="10" class="col_2"/>
            
            <label for="endDate" class="col_2">Ngày kết thúc</label>
            <form:input path="endDate" id="request_enddate" size="10" class="col_2"/>
		</div>
		<div>
		 	<label for="listLabel" class="col_2">Nhãn</label>
		 	<form:input path="listLabel" type="text" class="col_8"/>
		</div>
		<div>
		 	<label for="duration" class="col_2">Thời lượng</label>
		 	<form:input path="duration" id="duration" type="text" class="col_2" style="display:inline;"/>
      
            <form:select path="durationUnit" id="durationUnit" class="col_2">
                <c:forEach var="duration" items="${listDurationUnit}">
                  <c:choose>
                    <c:when test="${durationUnit == duration.cd}">
                      <option value="${duration.cd}" selected="selected">${duration.name}</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${duration.cd}">${duration.name}</option>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
            </form:select>
		</div>
    <%-- Refer: http://crunchify.com/spring-mvc-tutorial-how-to-upload-multiple-files-to-specific-location/ --%>
		<div>
		  <label for="attachment0" class="col_2">Đính kèm</label>
		  <input name="attachments[0]" type="file" class="col_8"/>
		</div>

      <div>
        <input type="submit" value="Save" class="button"/>
         <input type="reset" value="Reset" class="button" />
      </div>
  </form:form>
</div>