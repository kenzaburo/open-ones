<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--    Task -->
<div id="make-task">
    <form:form name="createTask" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
    <!--  Select type Request -->
    <jsp:include page="_commonPart.jsp">
      <jsp:param name="formName" value="createTask"/>
      <jsp:param name="newReqType" value="Task"/>
    </jsp:include>
		<div>
		  <label for="request.title" class="col_2">Tiêu đề</label>
		  <form:input path="request.title" id="request.title" type="text" required="required" class="col_8"/>
		</div>
		<div>
			<label for="request.content" class="col_2 left">Nội dung</label>
            
			<form:textarea path="request.content" id="request.content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="taskContent" rows="15" placeholder="Mô tả chi tiết công việc"></form:textarea>
		</div>

		<div>
			
		 	<label for="request.assignedId.id" class="col_2">Người thực hiện</label>
		 	<form:select path="request.assignedId.id" id="request.assignedId.id" name="request.assignedId.id"
                class="col_8 chosen-select">
                <c:forEach var="user" items="${model.listUser}">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </form:select>
		</div>
		<div>
            <label for="request.managerId.id" class="col_2">Người quản lý</label>
            <form:select path="request.managerId.id" id="request.managerId.id" name="request.managerId.id" class="chosen-select col_8">
                <c:forEach var="user" items="${model.listUser}">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </form:select>
		</div>
		<div>
            <label for="listWatcher " class="col_2">Chia sẻ</label>
            <form:select path="listWatcher" name="listWatcher" class="col_8 chosen-select" multiple="true">
                <c:forEach items="${model.listUser}" var="user">
                    <option value="${user.id}">${user.username }</option>
                </c:forEach>
            </form:select>
		</div>
		<div>
            <label for="scopes" class="col_2">Ngày bắt đầu</label>
            <form:input path="request.startdate" id="request.startdate" name="request.startdate" type="date"
                class="col_2"/>
            <label for="scopes" class="col_2">Ngày kết thúc</label>
            <form:input path="request.endate" id="request.endate" name="request.endate" type="date"
                class="col_2"/>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Nhãn</label>
		 	<form:input path="labels" id="labels" type="text" class="col_8"/>
		</div>
		<div>
		 	<label for="request.duration" class="col_2">Thời lượng</label>
		 	<form:input path="request.duration" id="request.duration" type="text" class="col_2" style="display:inline;"/>
      
            <form:select path="request.durationunit" id="durationunit" class="col_2">
              <option value="0">giờ</option>
              <option value="1">ngày</option>
              <option value="2">tuần</option>
            </form:select>
		</div>
		<div>
		  <label for="attachment1" class="col_2">Đính kèm</label>
		  <form:input path="request.attachment1" id="attachment1" type="file" class="col_8"/>
		</div>

      <div>
        <input type="submit" value="Lưu" class="button" />
          <a class="button" href="">Hủy</a>
      </div>
  </form:form>
</div>