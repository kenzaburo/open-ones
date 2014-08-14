<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--    Task -->
    <div id="make-task">

		<div>
		  <label for="title" class="col_2">Tiêu đề</label>
		  <input id="title" type="text" class="col_8" />
		</div>
		<div>
			<label for="content" class="col_2 left">Nội dung</label>
            
			<textarea style="display:inline; position: relative; top:6px; left:10px;" cols="120" id="taskContent" name="taskContent" rows="15" placeholder="Mô tả chi tiết công việc"></textarea>
		</div>

		<div>
			
		 	<label for="scopes" class="col_2">Người thực hiện</label>
		 	<input id="text2" type="text"  class="col_8" />
		</div>
		<div>
		 	<label for="scopes" class="col_2">Người nhận</label>
		 	<select name="receiveUser" class="col_3">
         		<option value="0">-- Người nhận --</option>
         		<c:forEach var="receiveUser" items="${listUsers}">
            		<option value="${receiveUser.cd}">${receiveUser.username}</option>
         		</c:forEach>
	  		</select>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Chia sẻ</label>
		 	<input id="text2" type="text"  class="col_8" />
		</div>
		<div>
		 	<label for="scopes" class="col_2">Ngày bắt đầu</label>
		 	<input id="text2" type="date" class="col_2"/>

		 	<label for="scopes" class="col_2">Ngày kết thúc</label>
		 	<input id="text2" type="date" class="col_2"/>
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

</div>