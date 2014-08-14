<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--    Announcement -->
    <div id="make-announcement">
<form class="horizontal" enctype="multipart/form-data" action="createNewRequest">

       <jsp:include page="_commonPart.jsp"></jsp:include>

		<div>
		  <label for="title" class="col_2">Thông báo</label>
		  <input id="title" type="text" class="col_8" />
		</div>
		
		<div>
			<label for="content" class="col_2">Nội dung</label>
			<textarea class="ckeditor col_2" id="annContent" name="editor1" rows="10">
			</textarea>
		</div>
		
		<div>
		  <label for="scopes" class="col_2">Phạm vi áp dụng</label>
		  <select id="scopes" multiple="multiple" class="col_3 fancy">
		    <option value="0">Tất cả</option>
		    <option value="1">Phòng kế toán</option>
		    <option value="2">Phòng kinh doanh</option>
		    <option value="3">Kho</option>
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