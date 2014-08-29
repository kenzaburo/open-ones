<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<fmt:formatDate value="${request.startdate}" pattern="yyyy-MM-dd" var="startDate"/>
<fmt:formatDate value="${request.enddate}" pattern="yyyy-MM-dd" var="endDate"/>
<script type="text/javascript" src="resources/ckeditor-3.6.6.1/ckeditor.js"></script>
<!-- <script type="text/javascript" src="resources/js/createRequest.js"></script> -->
<script type="text/javascript" src="resources/js/validateFunction.js"></script>
<script>

$(function(){
	
	var status = 0;
	var statusTask = 0;
	$("#reason").hide();
	function showHideForm() {
		if (status == 0) {
			$("#reason").show("slow");
			$("#approve").hide("slow");
			status = 1;
		}
		else {
			$("#reason").hide("slow");
			$("#approve").show("slow");
			status = 0;
		}
	}

	$("#reject").click(showHideForm);
 
	$("#reason").submit(function( event ) {
  		$( "#reason" ).hide("fast");
	});
	
	
	$("#comment").hide();
	function showHideForm1() {
		if (statusTask == 0) {
			$("#comment").show("slow");
			$("#approve").hide("slow");
			statusTask = 1;
		}
		else { 
			$("#comment").hide("slow");
			$("#approve").show("slow");
			statusTask = 0;
		}
	}
	
	$("#confirmIncomplete").click(showHideForm1);
	$("#commentTask").click(showHideForm1);
 
	$("#comment").submit(function( event ) {
  		$( "#comment" ).hide("fast");
	});
});
</script>
<c:if test="${request.requesttypeCd == 'Leave'}">
	<div>
		<div>
			<label for="title" class="col_2">Tiêu đề: </label>
			<input id="title" type="text" disabled="disabled" value="${request.title}" class="col_3 column"/>
			<c:if test="${request.status == 'Created'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small blue">Created</button>
			</c:if>
			<c:if test="${request.status == 'Updated'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small orange">Updated</button>
			</c:if>
			<c:if test="${request.status == 'Updated1'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small orange">Updated</button>
			</c:if>
			<c:if test="${request.status == 'Rejected'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small red">Rejected</button>
			</c:if>
			<c:if test="${request.status == 'Approved'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small green">Approved</button>
			</c:if>
		</div>
		<div>
			<label for="title" class="col_2">Người nhận: </label>
			<input id="title" type="text" disabled="disabled" value="${request.managerName}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Ngày bắt đầu nghỉ: </label>
			<input id="title" type="date" disabled="disabled" value="${startDate}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Ngày kết thúc: </label>
			<input id="title" type="date" disabled="disabled" value="${endDate}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Lý do: </label>
			<textarea disabled="disabled" style="display:inline; position: relative; top:5px; left:10px;" cols="120" id="taskContent" name="taskContent" rows="15">${request.content}</textarea>
		</div>
		<c:if test="${(not empty request.comment)}">
			<div>
				<label for="title" class="col_2">Comment: </label>
				<textarea disabled="disabled" style="display:inline; position: relative; top:10px; left:10px;" cols="120" id="taskContent" name="taskContent" rows="15">${request.comment}</textarea>
			</div>
		</c:if>
	</div>
	
<!-- 	Kiem tra tai khoan dang nhap co phai tai khoan tao yeu cau khong -->
	<c:if test="${not empty isCreator}">
		<div>
			<a class="button" href="editRequest?id=${request.id}">Sửa nội dung yêu cầu</a>
		</div>
	</c:if>
<!-- 	Kiem tra tai khoan dang nhap co phai tai khoan nhan yeu cau khong -->
	<c:if test="${not empty isManager}">
		<div>
			<a class="button" href="approveRequest?id=${request.id}" id="approve">Duyệt</a>
			<a class="button" id="reject">Từ chối</a>
			<form action="rejectRequest" id="reason">
				<input type="hidden" name="requestId" value="${request.id}">
				<label for="content" class="col_2">Lý do: </label>
				<textarea style="display: inline; position: relative; top: 6px; left: 10px;" cols="100" name="rejectContent" rows="5" placeholder="Lý do từ chối"></textarea>
				<br>
				<input type="submit" value="Xác nhận">
			</form>
		</div>
	</c:if>
</c:if>
<c:if test="${request.requesttypeCd == 'Task'}">
	<div id="make-task">
		<div>
			<label for="title" class="col_2">Tiêu đề: </label>
			<input id="title" type="text" disabled="disabled" value="${request.title}" class="col_3 column"/>
			<c:if test="${request.status == 'Created'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small blue">Created</button>
			</c:if>
			<c:if test="${request.status == 'Updated'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small orange">Updated</button>
			</c:if>
			<c:if test="${request.status == 'Rejected'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small red">Rejected</button>
			</c:if>
			<c:if test="${request.status == 'Doing'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small green">Doing</button>
			</c:if>
			<c:if test="${request.status == 'Confirm'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small green">Confirm</button>
			</c:if>
			<c:if test="${request.status == 'Done'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small green">Done</button>
			</c:if>
		</div>
	    <div>
			<label for="title" class="col_2">Người Tạo: </label>
			<input id="title" type="text" disabled="disabled" value="${request.createdbyName}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Người Nhận: </label>
			<input id="title" type="text" disabled="disabled" value="${request.assignedName}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Người Quản lý: </label>
			<input id="title" type="text" disabled="disabled" value="${request.managerName}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Ngày bắt đầu: </label>
			<input id="title" type="date" disabled="disabled" value="${startDate}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Ngày kết thúc: </label>
			<input id="title" type="date" disabled="disabled" value="${endDate}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Thời lượng: </label>
			<input id="title" type="text" disabled="disabled" value="${request.duration}" class="col_3 column"/>
			<label for="title" class="col_1">${request.durationunit}</label>
		</div>
		<div>
			<label for="title" class="col_2">Chi tiết công việc: </label>
			<textarea disabled="disabled" style="display:inline; position: relative; top:5px; left:10px;" cols="120" id="taskContent" name="taskContent" rows="15">${request.content}</textarea>
		</div>
		<input type="hidden" value="${request.id}" id="requestId"/>
		<c:if test="${not empty request.comment}">
			<div>
				<label for="title" class="col_2">Comment: </label>
				<textarea disabled="disabled" style="display:inline; position: relative; top:10px; left:10px;" cols="120" id="taskContent" name="taskContent" rows="15">${request.comment}</textarea>
			</div>
		</c:if>
		
		<c:if test="${not empty isAssigner}">
			<div style="position:relative; top:20px;">
				<c:if test="${request.status == 'Created'}">
					<a class="button" href="approveRequest?id=${request.id}">Nhận yêu cầu</a>
				</c:if>
				<c:if test="${request.status == 'Doing'}">
					<a class="button" id="confirmTask">Yêu cầu xác nhận hoàn thành</a>
				</c:if>
			</div>
		</c:if>
		<c:if test="${not empty isCreator}">
			<div style="position:relative; top:20px;">
				<c:if test="${request.status == 'Created' || request.status == 'Rejected' || request.status == 'Updated'}">
					<a class="button" href="editRequest?id=${request.id}">Sửa nội dung yêu cầu</a>
				</c:if>
				<c:if test="${request.status == 'Doing' || request.status == 'Update1' || request.status == 'Confrim' || request.status == 'Done'}">
					<a class="button" id="commentTask">Góp ý</a>
					<form action="addComment" id="comment">
						<input type="hidden" name="requestId" value="${request.id}">
						<label for="content" class="col_2">Nội dung: </label>
						<textarea style="display: inline; position: relative; top: 6px; left: 10px;" cols="100" name="commentContent" rows="5" placeholder="Nội dung góp ý"></textarea>
						<br>
						<input type="submit" value="Xác nhận">
					</form> 
				</c:if>
			</div>
		</c:if>
		<c:if test="${not empty isCreatorAssigner}">
			<div style="position:relative; top:20px;">
				<a class="button" href="editRequest?id=${request.id}">Sửa nội dung yêu cầu</a>
				<c:if test="${request.status == 'Doing'}">
					<a class="button" id="confirmTask" href="confirm.task?requestId=${request.id}">Xác nhận hoàn thành</a>
				</c:if>
			</div>
		</c:if>
		<c:if test="${not empty isManager}">
			<div style="position:relative; top:20px;">
				<c:choose>
					<c:when test="${request.status == 'Confirm'}">
		            	<a class="button" id="confirmComplete" href="completedtask?requestId=${request.id}">Xác nhận hoàn thành</a>
						<a class="button" id="confirmIncomplete">Xác nhận chưa hoàn thành</a>
		            </c:when>
		            <c:otherwise>
		                <a class="button" id="commentTask">Góp ý</a>
		            </c:otherwise>
		        </c:choose>
				<form action="addComment" id="comment">
					<input type="hidden" name="requestId" value="${request.id}">
					<label for="content" class="col_2">Nội dung: </label>
					<textarea style="display: inline; position: relative; top: 6px; left: 10px;" cols="100" name="commentContent" rows="5" placeholder="Nội dung góp ý"></textarea>
					<br>
					<input type="submit" value="Xác nhận">
				</form> 
			</div>
		</c:if>
	</div>
</c:if>