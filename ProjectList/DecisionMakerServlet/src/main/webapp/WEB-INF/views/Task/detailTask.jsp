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
<div>
	<h3>Chi tiết</h3>
	<div>
		<label for="title" class="col_2">Tiêu đề: </label>
		<p style="display:inline; position: relative; top:6px; left: -30px;"> Dự án 1</p>
	</div>
	<div>
		<label for="title" class="col_2">Nhãn: </label>
		<p style="display:inline; position: relative; top:6px; left: -30px;"> Dự án tài chính</p>
	</div>
	<div>
		<label for="title" class="col_2">Mô tả: </label>
		<textarea cols="80" id="editor2" name="editor2" rows="15" style="display:inline; position: relative; left: -30px;">
		</textarea>
	</div>
</div>
<div>
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
		<button>Submit</button>
	</div>
	<br>
	<br>
</div>