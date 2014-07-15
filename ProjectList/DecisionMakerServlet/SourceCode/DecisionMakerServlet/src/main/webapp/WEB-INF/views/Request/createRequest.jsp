<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<script type="text/javascript" src="resources/ckeditor-3.6.6.1/ckeditor.js"></script>
<script type="text/javascript" src="resources/js/createTask.js"></script>
<form class="horizontal" enctype="application/x-www-form-urlencoded">
	<div>
	  <label for="type" class="col_2">Loại yêu cầu</label>
	   <select id="reqType" class="col_3">
	    <option value="0">-- Lựa chọn --</option>
	    <option value="1">Thông báo</option>
	    <option value="2">Quy định</option>
	    <option value="3" selected="selected">Công việc</option>
      <option value="4" >Nghỉ phép</option>
	  </select>
	</div>
<!-- 	Announcement -->
	<div id="make-announcement">
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
	</div>
  
<!--    Rule -->
    <div id="make-rule">
        <div>
          <label for="title" class="col_2">Quy định</label>
          <input id="title" type="text" class="col_8" />
        </div>
        
        <div>
            <label for="content" class="col_2">Nội dung</label>
            <textarea class="ckeditor" cols="80" id="ruleContent" name="editor1" rows="15">
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
    </div>
    
<!-- 	Task -->
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
		 	<label for="scopes" class="col_2">Người duyệt</label>
		 	<input id="text2" type="text"  class="col_8" />
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
  
<!--    Leave -->
    <div id="make-leave">
        <div>
          <label for="title" class="col_2">Tiêu đề</label>
          <input id="title" type="text" class="col_8" />
        </div>
        <div>
            <label for="content" class="col_2">Lý do</label>
            <textarea style="display:inline; position: relative; top:6px; left:10px;" cols="100" id="taskContent" name="taskContent" rows="15" placeholder="Mô tả chi tiết lý do và sắp xếp công việc đảm bảo không ảnh hưởng"></textarea>
        </div>

        <div>
            <label for="scopes" class="col_2">Người duyệt</label>
            <input id="text2" type="text"  class="col_8" />
        </div>
        <div>
            <label for="scopes" class="col_2">Chia sẻ</label>
            <input id="text2" type="text"  class="col_8" />
        </div>
        <div>
            <label for="scopes" class="col_2">Ngày bắt đầu</label>
            <input id="text2" type="date" class="col_2"/>
        </div>
        <div>
            <label for="scopes" class="col_2">Ngày kết thúc</label>
            <input id="text2" type="date" class="col_2"/>
        </div>
        <div>
            <label for="scopes" class="col_2">Nhãn</label>
            <input id="text2" type="text" class="col_8"/>
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
</form>