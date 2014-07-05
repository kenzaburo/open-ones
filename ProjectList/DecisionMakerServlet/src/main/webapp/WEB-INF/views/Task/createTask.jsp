<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<script type="text/javascript" src="resources/ckeditor-3.6.6.1/ckeditor.js"></script>
<script type="text/javascript" src="resources/js/createTask.js"></script>
<form class="horizontal" enctype="application/x-www-form-urlencoded">
	<div>
	  <label for="type" class="col_2">Loại</label>
	   <select id="select1" class="col_3">
	    <option value="0">-- Lựa chọn --</option>
	    <option value="1">Thông báo</option>
	    <option value="2">Quy định</option>
	    <option value="3">Công việc</option>
	  </select>
	</div>
<!-- 	tạo báo cáo -->
	<div id="make-report">
		<div>
		  <label for="title" class="col_2">Tạo báo cáo</label>
		  <input id="title" type="text" class="col_8" />
		</div>
		
		<div>
			<label for="content" class="col_2">Nội dung</label>
			<textarea class="ckeditor" cols="80" id="editor1" name="editor1" rows="15">
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
			<a class="button" href=""></i>Lưu</a>
			<a class="button" href=""></i>Hủy</a>
		</div>
	</div>
<!-- 	tạo công việc -->
	<div id="make-task">
		<div>
		  <label for="title" class="col_2">Tạo báo cáo</label>
		  <input id="title" type="text" class="col_8" />
		</div>
		<div>
			<label for="content" class="col_2">Nội dung</label>
			<textarea class="ckeditor" cols="80" id="editor2" name="editor2" rows="15">
			</textarea>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Người làm</label>
		 	<input id="text2" type="text"  class="col_8" />
		</div>
		<div>
		 	<label for="scopes" class="col_2">Người nhận báo cáo</label>
		 	<input id="text2" type="text"  class="col_8" />
		</div>
		<div>
		 	<label for="scopes" class="col_2">Người theo dõi</label>
		 	<input id="text2" type="text"  class="col_8" />
		</div>
		<div>
		 	<label for="scopes" class="col_2">Ngày bắt đầu</label>
		 	<input id="text2" type="date" class="col_8"/>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Ngày kết thúc</label>
		 	<input id="text2" type="date" class="col_8"/>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Nhãn</label>
		 	<input id="text2" type="text" class="col_8"/>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Thời lượng</label>
		 	<input id="text2" type="text" class="col_8" style="display:inline;"/>
		 	<p style="display:inline; position: relative; top:4px;">(Ví dụ: 1 tuần, 1 ngày, 1 giờ)</p>
		</div>
		<div>
		  <label for="attachment" class="col_2">Đính kèm</label>
		  <input id="attachment" type="file" class="col_8"/>
		</div>
	</div>
</form>