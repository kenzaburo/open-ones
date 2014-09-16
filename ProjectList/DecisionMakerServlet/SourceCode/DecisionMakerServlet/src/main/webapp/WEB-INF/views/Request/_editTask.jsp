<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--    Task -->
<c:choose>
	<c:when test="${not empty isManager}">
		<div id="make-task">
		    <form:form name="createTask" id="createTask" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
		      <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Task"/>
		      <form:hidden path="request.id"/>
				<div>
				  <label for="request.title" class="col_2">Tiêu đề</label>
				  <form:input path="request.title" disabled="disabled" id="request.title" type="text" required="required" class="col_8"/>
				</div>
				<div>
					<label for="request.content" class="col_2 left">Nội dung</label>
		            
					<form:textarea path="request.content" disabled="disabled" id="request.content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="taskContent" rows="15" placeholder="Mô tả chi tiết công việc"></form:textarea>
				</div>
		
				<div>
					
				 	<label for="request.assignedId.id" class="col_2">Người thực hiện</label>
				 	<form:select path="request.assignedId.id" disabled="disabled" id="request.assignedId.id" name="request.assignedId.id"
		                class="col_8 chosen-select">
		                <c:forEach var="user" items="${listUser}">
		                  <c:choose>
		                    <c:when test="${model.request.assignedCd == user.cd}">
		                      <option value="${user.id}" selected="selected">${user.username}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <option value="${user.id}">${user.username}</option>
		                    </c:otherwise>
		                  </c:choose>
		                    
		                </c:forEach>
		            </form:select>
				</div>
				<div>
		            <label for="request.managerId.id" class="col_2">Người quản lý</label>
		            <form:select path="request.managerId.id" disabled="disabled" id="request.managerId.id" name="request.managerId.id" class="chosen-select col_8">
		                <c:forEach var="user" items="${listUser}">
		                  <c:choose>
		                    <c:when test="${model.request.managerCd == user.cd}">
		                      <option value="${user.id}" selected="selected">${user.username}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <option value="${user.id}">${user.username}</option>
		                    </c:otherwise>
		                  </c:choose>
		                </c:forEach>
		            </form:select>
				</div>
				<div>
		            <label for="listWatcher " class="col_2">Chia sẻ</label>
		            <form:select path="listWatcher" disabled="disabled" name="listWatcher" class="col_8 chosen-select" multiple="true">
		                <c:forEach var="user" items="${listUser}">
		                    <option value="${user.id}">${user.username }</option>
		                </c:forEach>
		            </form:select>
				</div>
				<div>
		            <label for="scopes" class="col_2">Ngày bắt đầu</label>
		            <form:input path="request.startdate" disabled="disabled" id="request_startdate" size="10" class="col_2"/>
		            
		            <label for="scopes" class="col_2">Ngày kết thúc</label>
		            <form:input path="request.enddate" disabled="disabled" id="request_enddate" size="10" class="col_2"/>
		
				</div>
				<div>
				 	<label for="scopes" class="col_2">Nhãn</label>
				 	<form:input path="labels" disabled="disabled" id="labels" type="text" class="col_8"/>
				</div>
				<div>
				 	<label for="request.duration" class="col_2">Thời lượng</label>
				 	<form:input path="request.duration" disabled="disabled" id="request.duration" type="text" class="col_2" style="display:inline;"/>
		      
		            <form:select path="request.durationunit" disabled="disabled" id="durationunit" class="col_2">
		                <c:forEach var="duration" items="${listDurationUnits}">
		                  <c:choose>
		                    <c:when test="${model.request.durationunit == duration.cd}">
		                      <option value="${duration.cd}" selected="selected">${duration.name}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <option value="${duration.cd}">${duration.name}</option>
		                    </c:otherwise>
		                  </c:choose>
		                </c:forEach>
		            </form:select>
				</div>
				<div>
				  <label for="attachment1" class="col_2">Đính kèm</label>
				  <form:input path="request.attachment1" disabled="disabled" id="attachment1" type="file" class="col_8"/>
				</div>
		
		      <div>
		        <input type="submit" value="Save" class="button"/>
		         <input type="reset" value="Reset" class="button" />
		      </div>
		  </form:form>
		</div>
	</c:when>
	<c:otherwise>
		<div id="make-task">
		    <form:form name="createTask" id="createTask" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
		      <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Task"/>
		      <form:hidden path="request.id"/>
				<div>
				  <label for="request.title" class="col_2">Tiêu đề</label>
				  <form:input path="request.title" id="request.title" type="text" required="required" class="col_8"/>
				</div>
				<div>
					<label for="request.content" class="col_2 left">Nội dung</label>
		            
					<form:textarea path="request.content" id="request.content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="taskContent" rows="15" placeholder="Mô tả chi tiết công việc"></form:textarea>
				</div>
				<form:hidden path="request.createdbyId.id"/>
				<form:hidden path="request.creatorRead"/>
				<div>
					
				 	<label for="request.assignedId.id" class="col_2">Người thực hiện</label>
				 	<form:select path="request.assignedId.id" id="request.assignedId.id" name="request.assignedId.id"
		                class="col_8 chosen-select">
		                <c:forEach var="user" items="${listUser}">
		                  <c:choose>
		                    <c:when test="${model.request.assignedCd == user.cd}">
		                      <option value="${user.id}" selected="selected">${user.username}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <option value="${user.id}">${user.username}</option>
		                    </c:otherwise>
		                  </c:choose>
		                    
		                </c:forEach>
		            </form:select>
				</div>
				<div>
		            <label for="request.managerId.id" class="col_2">Người quản lý</label>
		            <form:select path="request.managerId.id" id="request.managerId.id" name="request.managerId.id" class="chosen-select col_8">
		                <c:forEach var="user" items="${listUser}">
		                  <c:choose>
		                    <c:when test="${model.request.managerCd == user.cd}">
		                      <option value="${user.id}" selected="selected">${user.username}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <option value="${user.id}">${user.username}</option>
		                    </c:otherwise>
		                  </c:choose>
		                </c:forEach>
		            </form:select>
				</div>
				<div>
		            <label for="listWatcher " class="col_2">Chia sẻ</label>
		            <form:select path="listWatcher" name="listWatcher" class="col_8 chosen-select" multiple="true">
		                <c:forEach var="user" items="${listUser}">
		                    <option value="${user.id}">${user.username }</option>
		                </c:forEach>
		            </form:select>
				</div>
				<div>
		            <label for="scopes" class="col_2">Ngày bắt đầu</label>
		            <form:input path="request.startdate" id="request_startdate" size="10" class="col_2"/>
		            
		            <label for="scopes" class="col_2">Ngày kết thúc</label>
		            <form:input path="request.enddate" id="request_enddate" size="10" class="col_2"/>
		
				</div>
				<div>
				 	<label for="scopes" class="col_2">Nhãn</label>
				 	<form:input path="labels" id="labels" type="text" class="col_8"/>
				</div>
				<div>
				 	<label for="request.duration" class="col_2">Thời lượng</label>
				 	<form:input path="request.duration" id="request.duration" type="text" class="col_2" style="display:inline;"/>
		      
		            <form:select path="request.durationunit" id="durationunit" class="col_2">
		                <c:forEach var="duration" items="${listDurationUnits}">
		                  <c:choose>
		                    <c:when test="${model.request.durationunit == duration.cd}">
		                      <option value="${duration.cd}" selected="selected">${duration.name}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <option value="${duration.cd}">${duration.name}</option>
		                    </c:otherwise>
		                  </c:choose>
		                </c:forEach>
		            </form:select>
				</div>
				<div style="position:relative; top:10px;">
					<label for="title" class="col_2">Góp ý: </label>
					<textarea disabled="disabled" style="display:inline; position: relative; top:10px; left:10px;" cols="120" id="taskContent" rows="15">${model.request.comment}</textarea>
				</div>
				<form:hidden path="request.comment"/>
				<div style="position:relative; top:20px;">
					<label for="title" class="col_2">Thêm góp ý: </label>
					<textarea style="display:inline; position: relative; top:10px; left:10px;" cols="120" id="taskContent" name="commentTask" rows="15"></textarea>
				</div>
				<div style="position:relative; top:30px;">
				  <label for="attachment1" class="col_2">Đính kèm</label>
				  <form:input path="request.attachment1" id="attachment1" type="file" class="col_8"/>
				</div>
		
		      <div style="position:relative; top:40px;">
		        <input type="submit" value="Save" class="button"/>
		         <input type="reset" value="Reset" class="button" />
		      </div>
		  </form:form>
		</div>
	</c:otherwise>
</c:choose>
