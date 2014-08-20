<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- <script type="text/javascript" src="resources/js/validateFunction.js"></script> -->
<!--    Leave -->
<div id="make-leave">
  <form:form id="createLeave" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
    <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Leave"/>
    <!--  Select type Request -->
<!--     <div> -->
<!--       <label for="request.requesttypeCd" class="col_2">Loại yêu cầu</label> -->
<%--        <form:select path="request.requesttypeCd"  id="reqType" class="col_3" name="reqType" onchange="displayDetailedRequest('createLeave');"> --%>
<!--          <option value="0">-- Lựa chọn --</option> -->
<%--          <c:forEach var="reqType" items="${lstReqTypes}"> --%>
<%--            <c:choose> --%>
<%--              <c:when test='${reqType.cd == "Leave"}'> --%>
<%--                <option value="${reqType.cd}" selected="selected">${reqType.name}</option> --%>
<%--              </c:when> --%>
<%--              <c:otherwise> --%>
<%--                <option value="${reqType.cd}">${reqType.name}</option> --%>
<%--              </c:otherwise> --%>
<%--            </c:choose> --%>
<%--          </c:forEach> --%>
<%--       </form:select> --%>
<!--     </div> -->
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
		 	<label for="scopes" class="col_2">Người nhận</label>
		 	<form:select path="request.managerId.id" class="col_3" id="request.managerId.id">
         		<option value="0">-- Người nhận --</option>
         		<c:forEach var="user" items="${listUsers}">
         			<c:if test="${user.username != pageContext.request.userPrincipal.name}">
            			<option value="${user.id}">${user.username}</option>
            		</c:if>
         		</c:forEach>
	  		</form:select>
		</div>
        <div>
            <label for="scopes" class="col_2">Ngày bắt đầu</label>
            <form:input path="request.startdate" id="request.startdate" type="date" class="col_2"/>
        </div>
        <div>
            <label for="scopes" class="col_2">Ngày kết thúc</label>
            <form:input path="request.enddate" id="request.enddate" type="date" class="col_2"/>
        </div>
        <div>
            <label for="scopes" class="col_2">Nhãn</label>
            <form:input path="labels" id="labels" type="text" class="col_8"/>
        </div>
        <div>
          <label for="attachment" class="col_2">Đính kèm</label>
          <form:input path="request.attachment1" id="attachment1" type="file" class="col_8"/>
        </div>
      	<div>
      		<input type="submit" value="Send" class="button" />
			<input type="reset" value="Reset" class="button"  />
      	</div>
  </form:form>
</div>