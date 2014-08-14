<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--    Leave -->
    <div id="make-leave">


        <div>
          <label for="title" class="col_2">Tiêu đề</label>
          <input name="leaveTitle" type="text" class="col_8" />
        </div>
        <div>
            <label for="content" class="col_2">Lý do</label>
            <textarea style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="leaveContent" rows="15" placeholder="Mô tả chi tiết lý do và sắp xếp công việc đảm bảo không ảnh hưởng"></textarea>
        </div>

        <div>
		 	<label for="scopes" class="col_2">Người nhận</label>
		 	<select name="leaveReceiveUser" class="col_3">
         		<option value="0">-- Người nhận --</option>
         		<c:forEach var="receiveUser" items="${listUsers}">
            		<option value="${receiveUser.cd}">${receiveUser.username}</option>
         		</c:forEach>
	  		</select>
		</div>
        <div>
            <label for="scopes" class="col_2">Ngày bắt đầu</label>
            <input name="leaveStartDay" type="date" class="col_2"/>
        </div>
        <div>
            <label for="scopes" class="col_2">Ngày kết thúc</label>
            <input name="leaveEndDay" type="date" class="col_2"/>
        </div>
        <div>
            <label for="scopes" class="col_2">Nhãn</label>
            <input name="leaveLabel" type="text" class="col_8"/>
        </div>
        <div>
          <label for="attachment" class="col_2">Đính kèm</label>
          <input name="leaveAttachment" type="file" class="col_8"/>
        </div>
      <div>
      		<input type="submit" value="Send" class="button" />
			<input type="reset" value="Reset" class="button"  />

      </div>
</div>