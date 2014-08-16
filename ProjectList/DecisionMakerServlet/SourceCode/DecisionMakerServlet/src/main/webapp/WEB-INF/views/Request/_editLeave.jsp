<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<fmt:formatDate value="${request.startdate}" pattern="yyyy-MM-dd" var="startDate"/>
<fmt:formatDate value="${request.endate}" pattern="yyyy-MM-dd" var="endDate"/>
<!--    Leave -->
<div id="make-leave">
  <form name="createLeave" class="horizontal" enctype="multipart/form-data" action="updateRequest">
        <input name="leaveCreate" type="hidden" class="col_8" value="${pageContext.request.userPrincipal.name}"/>
        <div>
        	<input name="requestId" type="hidden" value="${request.id}" class="col_3 column"/>
        	<input name="reqType" type="hidden" value="${request.requesttypeCd}" class="col_3 column"/>
		<div>
			<label for="title" class="col_2">Tiêu đề: </label>
			<input name="leaveTitle" type="text" value="${request.title}" class="col_3 column"/>
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
				<button class="small red">Updated</button>
			</c:if>
			<c:if test="${request.status == 'Approved'}">
				<label for="title" class="col_1">Trạng thái: </label>
				<button class="small green">Updated</button>
			</c:if>
		</div>
		<div>
		 	<label for="scopes" class="col_2">Người nhận</label>
		 	<select name="leaveReceiveUser" class="col_3">
         		<c:forEach var="receiveUser" items="${listUsers}">
            		<c:if test="${receiveUser.username == request.managerName}">
            			<option value="${receiveUser.id}" selected="selected">${receiveUser.username}</option>
            		</c:if>
            			<option value="${receiveUser.id}">${receiveUser.username}</option>
         		</c:forEach>
	  		</select>
		</div>
		<div>
			<label for="title" class="col_2">Ngày bắt đầu nghỉ: </label>
			<input name="leaveStartDay" type="date" value="${startDate}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Ngày kết thúc: </label>
			<input name="leaveEndDay" type="date" value="${endDate}" class="col_3 column"/>
		</div>
		<div>
			<label for="title" class="col_2">Lý do: </label>
			<textarea style="display:inline; position: relative; top:6px; left:10px;" cols="120" id="leaveContent" name="leaveContent" rows="15">${request.content}</textarea>
		</div>
		<div>
      		<input type="submit" value="Send" class="button" />
			<input type="reset" value="Reset" class="button"  />
      	</div>
	</div>
  </form>
</div>