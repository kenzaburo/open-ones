<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--    Task -->
<div id="make-task">
    <form name="createTask" class="horizontal" enctype="multipart/form-data" action="createNewRequest" modelAttribute="model">
		<div>
		  <label for="title" class="col_2">Tiêu đề</label>
		  <input id="request.title" type="text" class="col_8"/>
		</div>
		<div>
			<label for="content" class="col_2 left">Nội dung</label>
            
			<textarea style="display:inline; position: relative; top:6px; left:10px;" cols="100" id="request.content" name="taskContent" rows="15" placeholder="Mô tả chi tiết công việc"></textarea>
		</div>

		<div>
			
		 	<label for="request.createdbyId.id" class="col_2">Người thực hiện</label>
		 	<select id="request.createdbyId.id" name="request.createdbyId.id"
                class="col_8 chosen-select">
                <c:forEach var="user" items="${model.listUser}">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
		</div>
		<div>
            <label for="request.managerId.id" class="col_2">Người giám
                sát</label>
            <select id="request.managerId.id" name="request.managerId.id"
                class="chosen-select col_8">
                <c:forEach var="user" items="${model.listUser}">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
		</div>
		<div>
            <label for="listWatcher " class="col_2">Chia sẻ</label>
            <select name="listWatcher"
                class="col_8 chosen-select" multiple="true">
                <c:forEach items="${model.listUser}" var="user">
                    <option value="${user.id}">${user.username }</option>
                </c:forEach>
            </select>
		</div>
		<div>
            <label for="scopes" class="col_2">Ngày bắt đầu</label>
            <input id="request.startdate" name="request.startdate" type="date"
                class="col_2" required="true" />
            <label for="scopes" class="col_2">Ngày kết thúc</label>
            <input id="request.endate" name="request.endate" type="date"
                class="col_2" required="true" />
		</div>
		<div>
		 	<label for="scopes" class="col_2">Nhãn</label>
		 	<input id="text2" type="text" class="col_8"/>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Thời lượng</label>
		 	<input id="text2" type="text" class="col_2" style="display:inline;"/>
      
            <select id="unit" class="col_2">
              <option value="0">giờ</option>
              <option value="1">ngày</option>
              <option value="2">tuần</option>
            </select>
		</div>
		<div>
		  <label for="attachment" class="col_2">Đính kèm</label>
		  <input id="attachment" type="file" class="col_8"/>
		</div>

      <div>
          <a class="button" href="">Lưu</a>
          <a class="button" href="">Hủy</a>
      </div>
  </form>
</div>