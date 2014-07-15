<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">
<!-- Button Bar w/icons -->
<div>
	<ul class="button-bar" style="left:30%;">
	<li><a href=""><i class="icon-pencil"></i> Sửa</a></li>
	<li><a href=""><i class="icon-comments"></i> Bình luận</a></li>
	<li><a href=""><i class="icon-tags"></i> Ủy thác</a></li>
	<li><a href=""><i class="icon-plus"></i>Cập nhật trạng thái</a></li>
	</ul>
</div>
<div style="position: relative; width: 600px; top: 40px;">
	<h3>Chi tiết</h3>
	<div>
		<label for="title" class="col_2">Tiêu đề: </label>
		<p style="display:inline; position: relative; top:6px; left: -10px;"> Dự án 1</p>
	</div>
	<div>
		<label for="title" class="col_2">Độ ưu tiên: </label>
		<p style="display:inline; position: relative; top:6px; left: -10px;"> Độ ưu tiên 1</p>
	</div>
	<div>
		<label for="title" class="col_2">Nhãn: </label>
		<p style="display:inline; position: relative; top:6px; left: -10px;"> Dự án tài chính</p>
	</div>
	<div style="position: relative; top:-85px; left: 300px;">
		<label for="title" class="col_2">Trạng thái: </label>
		<p style="display:inline; position: relative; top:6px; left: -10px;"> Đang thực hiện</p>
	</div>
	<div style="position: relative; top:-85px; left: 300px;">
		<label for="title" class="col_2">Giải pháp: </label>
		<p style="display:inline; position: relative; top:6px; left: -10px;"> Chưa có giải pháp</p>
	</div>
</div>
<div style="position: relative; width: 600px; top: -180px; left: 750px;">
	<h3>Nhân viên</h3>
	<div>
		<label for="title" class="col_3">Giám sát: </label>
		<p style="display:inline; position: relative; top:6px; left: 50px;"> Lê Ngọc Thạch</p>
	</div>
	<div>
		<label for="title" class="col_3">Báo cáo: </label>
		<p style="display:inline; position: relative; top:6px; left: 50px;"> Lê Ngọc Thạch</p>
	</div>
	<div>
		<label for="title" class="col_3">Votes: </label>
		<p style="display:inline; position: relative; top:6px; left: 50px;"> Lê Ngọc Thạch</p>
	</div>
	<div>
		<label for="title" class="col_3">Wathcer: </label>
		<p style="display:inline; position: relative; top:6px; left: 50px;"> Lê Ngọc Thạch</p>
	</div>
</div>
<div style="position: relative; width: 600px; top: -180px; left: 750px;">
	<h3>Lịch trình</h3>
	<div>
		<label for="title" class="col_3">Due: </label>
		<p style="display:inline; position: relative; top:6px; left: 50px;"> 15/07/2014</p>
	</div>
	<div>
		<label for="title" class="col_3">Ngày tạo: </label>
		<p style="display:inline; position: relative; top:6px; left: 50px;"> 15/07/2014</p>
	</div>
	<div>
		<label for="title" class="col_3" >Ngày giờ update: </label>
		<p style="display:inline; position: relative; top:6px; left: 50px;"> 1:00 PM 15/07/2014</p>
	</div>
</div>
<div style="position: relative; width: 600px; top: -180px; left: 750px;">
	<h3>Đối tác</h3>
	<a href="" style="text-decoration: none;">Thêm đối tác</a>
</div>
<br>
<div style="position: relative; width: 600px; top: -450px;">
	<h3>Mô tả</h3>
	<a href="" style="text-decoration: none;">Thêm mô tả công việc</a>
</div>
<br>
<div style="position: relative; width: 600px; top: -450px;">
	<h3>Công việc nhỏ</h3>
	<table style="border-bottom:1pt solid black;">
		<tr>
			<td>1. Công việc 1</td>
			<td><i class="icon-cog"></i></td>
			<td>Nguyễn Lê Trường Thọ</td>
			<td>Đang xử lý</td>
		</tr>

	</table>
</div>
<br>
<div style="position: relative; width: 600px; top: -450px;">
	<h3>Hoạt động</h3>
	<ul class="button-bar">
		<li><a href=""><i class="icon-pencil"></i> Sửa</a></li>
		<li><a href=""><i class="icon-comments"></i> Bình luận</a></li>
	</ul>
	<br>
	<br>
	<div>
		<table>
			<thead><tr>
				<td><strong>Tên hoạt động</strong></td>
				<td><strong>Người làm</strong></td>
				<td><strong>Thời điểm bắt đầu</strong></td>
				<td><strong>Thời điểm kết thúc</strong></td>
			</tr></thead>
			<tr>
				<td>Hoạt động 1</td>
				<td>Người A</td>
				<td>01/01/2014</td>
				<td>02/02/2014</td>
			</tr>
		</table>
	</div>
	<div>
		<button><i class="icon-comments"></i> Bình luận</button>
		<br>
		<br>
		<textarea cols="80" id="editor2" name="editor2" rows="8">
		</textarea>
		<br>
		<br>
		<button>Cập nhật</button>
	</div>
	<br>
	<br>
</div>