<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- <script type="text/javascript" src="resources/js/validateFunction.js"></script> -->
<!--    Leave -->
<c:choose>
	<c:when test="${not empty isManager}">
		<div id="make-leave">
		  <form:form id="createLeave" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
		    <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Leave"/>
		    <form:hidden path="request.id"/>
		        <div>
		          <label for="request.title" class="col_2">Tiêu đề</label>
		          <form:input path="request.title" disabled="disabled" id="request.title" type="text" required="required" class="col_8"/>
		        </div>
		        <div>
		            <label for="request.content" class="col_2">Lý do</label>
		            <form:textarea path="request.content" disabled="disabled" id="request.content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="leaveContent" rows="15" placeholder="Mô tả chi tiết lý do và sắp xếp công việc đảm bảo không ảnh hưởng"></form:textarea>
		        </div>
		        <div>
				 	<label for="scopes" class="col_2">Quản lý</label>
				 	<form:select path="request.managerId.id" disabled="disabled" class="col_3" id="request.managerId.id" title="Người sẽ nhận đơn và duyệt nghỉ phép">
		         		<option value="0">-- Chọn --</option>
		         		<c:forEach var="user" items="${listUsers}">
		                  <c:choose>
		                    <c:when test="${model.request.managerCd == user.cd}">
		                        <option value="${user.id}" selected="selected">${user.username}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <c:if test="${user.username != pageContext.request.userPrincipal.name}">
		                        <option value="${user.id}">${user.username}</option>
		                      </c:if>
		                    </c:otherwise>
		                  </c:choose>
		         			
		         		</c:forEach>
			  		</form:select>        
				</div>
		        <div>
		            <label for="scopes" class="col_2">Ngày bắt đầu</label>
		            <form:input path="request.startdate" disabled="disabled" id="request_startdate_leave" class="col_2"/>
		        </div>
		        <div>
		            <label for="scopes" class="col_2">Ngày kết thúc</label>
		            <form:input path="request.enddate" disabled="disabled" id="request_enddate_leave" class="col_2"/>
		        </div>
		        <div>
		            <label for="scopes" class="col_2">Nhãn</label>
		            <form:input path="labels" id="labels" disabled="disabled" type="text" class="col_8"/>
		        </div>
		        <div>
		          <label for="attachment" class="col_2">Đính kèm</label>
		          <form:input path="request.attachment1" disabled="disabled" id="attachment1" type="file" class="col_8"/>
		        </div>
		        <c:if test="${not empty model.request.comment}">
					<div>
						<label for="request.comment" class="col_2">Góp ý</label>
						<form:textarea path="request.comment" disabled="disabled" id="request.content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="leaveContent" rows="15"></form:textarea>
					</div>
				</c:if>
		        <div>
		        	<label for="comment" class="col_2">Comment</label>
		        	<textarea style="display: inline; position: relative; top: 6px; left: 10px;" cols="50" name="comment" rows="5" placeholder="Comment"></textarea>
		        </div>
		      	<div>
		      		<input type="submit" value="Save" class="button" />
					<input type="reset" value="Reset" class="button"  />
		      	</div>
		  </form:form>
		</div>
		</c:when>
	<c:otherwise>
		<div id="make-leave">
		  <form:form id="createLeave" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
		    <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Leave"/>
		    <form:hidden path="request.id"/>
		        <div>
		          <label for="request.title" class="col_2">Tiêu đề</label>
		          <form:input path="request.title" id="request.title" type="text" required="required" class="col_8"/>
		        </div>
		        <div>
		            <label for="request.content" class="col_2">Lý do</label>
		            <form:textarea path="request.content" id="request.content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="leaveContent" rows="15" placeholder="Mô tả chi tiết lý do và sắp xếp công việc đảm bảo không ảnh hưởng"></form:textarea>
		        </div>
		        <div>
		          <input name="leaveCreate" type="hidden" class="col_8" value="${pageContext.request.userPrincipal.name}"/>
		        </div>	
		        <div>
				 	<label for="scopes" class="col_2">Quản lý</label>
				 	<form:select path="request.managerId.id" class="col_3" id="request.managerId.id" title="Người sẽ nhận đơn và duyệt nghỉ phép">
		         		<option value="0">-- Chọn --</option>
		         		<c:forEach var="user" items="${listUsers}">
		                  <c:choose>
		                    <c:when test="${model.request.managerCd == user.cd}">
		                        <option value="${user.id}" selected="selected">${user.username}</option>
		                    </c:when>
		                    <c:otherwise>
		                      <c:if test="${user.username != pageContext.request.userPrincipal.name}">
		                        <option value="${user.id}">${user.username}</option>
		                      </c:if>
		                    </c:otherwise>
		                  </c:choose>
		         			
		         		</c:forEach>
			  		</form:select>        
				</div>
		        <div>
		            <label for="scopes" class="col_2">Ngày bắt đầu</label>
		            <form:input path="request.startdate" id="request_startdate_leave" class="col_2"/>
		        </div>
		        <div>
		            <label for="scopes" class="col_2">Ngày kết thúc</label>
		            <form:input path="request.enddate" id="request_enddate_leave" class="col_2"/>
		        </div>
		        <div>
		            <label for="scopes" class="col_2">Nhãn</label>
		            <form:input path="labels" id="labels" type="text" class="col_8"/>
		        </div>
		        <div>
		          <label for="attachment" class="col_2">Đính kèm</label>
		          <form:input path="request.attachment1" id="attachment1" type="file" class="col_8"/>
		        </div>
		        <c:if test="${not empty model.request.comment}">
					<div>
						<label for="request.comment" class="col_2">Góp ý</label>
						<form:textarea path="request.comment"  id="request.content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="leaveContent" rows="15"></form:textarea>
					</div>
				</c:if>
		        <div>
		        	<label for="comment" class="col_2">Comment</label>
		        	<textarea style="display: inline; position: relative; top: 6px; left: 10px;" cols="50" name="comment" rows="5" placeholder="Comment"></textarea>
		        </div>
		      	<div>
		      		<input type="submit" value="Save" class="button" />
					<input type="reset" value="Reset" class="button"  />
		      	</div>
		  </form:form>
		</div>
	</c:otherwise>
</c:choose>