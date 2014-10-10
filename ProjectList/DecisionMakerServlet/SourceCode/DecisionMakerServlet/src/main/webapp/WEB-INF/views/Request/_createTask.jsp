<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<link type="text/css" href="resources/css/app-validation.css" rel="stylesheet">


<!-- Task -->
<div id="make-task">
    <form:form name="createTask" id="createTask" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
      <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Task"/>
      <form:hidden path="request.id"/>
		<div>
		  <label for="title" class="col_2"><s:message code="Title"/><span class="required">*</span></label>
		  <form:input path="request.title" type="text" required="required" class="col_8"/>
          <form:errors path="request.title" class="error"/>
		</div>
		<div>
			<label for="content" class="col_2 left"><s:message code="Content"/></label>
			<form:textarea path="request.content" id="content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" name="taskContent" rows="15" placeholder="Mô tả chi tiết công việc"></form:textarea>
		</div>

		<div>
			
		 	<label for="assigneeUsername" class="col_2"><s:message code="Assignee"/></label>
		 	<form:select path="request.assigneeUsername" class="col_8 chosen-select">
                   <option value=""></option>
                <c:forEach var="user" items="${listUser}">
                  <c:choose>
                    <c:when test="${model.request.assigneeUsername == user.username}">
                      <option value="${user.username}" selected="selected">${user.username}</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${user.username}">${user.username}</option>
                    </c:otherwise>
                  </c:choose>
                    
                </c:forEach>
            </form:select>
		</div>
		<div>
            <label for="managerUsername" class="col_2"><s:message code="Manager"/></label>
            <form:select path="request.managerUsername" class="chosen-select col_8">
                <option value=""></option>
                <c:forEach var="user" items="${listUser}">
                  <c:choose>
                    <c:when test="${model.request.managerUsername == user.username}">
                        <option value="${user.username}" selected="selected">${user.username}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${user.username}">${user.username}</option>
                    </c:otherwise>
                  </c:choose>
         		</c:forEach>
            </form:select>
		</div>
<!-- 		<div> -->
<!--             <label for="listWatcher" class="col_2">Chia sẻ</label> -->
<%--             <form:select path="listWatcher" name="listWatcher" class="col_8 chosen-select" multiple="true"> --%>
<!--                 <option value=""></option> -->
<%--                 <c:forEach var="user" items="${listUser}"> --%>
<%--                     <option value="${user.username}">${user.username}</option> --%>
<%--                 </c:forEach> --%>
<%--             </form:select> --%>
<!-- 		</div> -->
		<div>
            <label for="startDate" class="col_2"><s:message code="Start_date"/></label>
            <form:input path="request.startdate" id="request_startdate" size="10" class="col_2"/>
            
            <label for="endDate" class="col_2"><s:message code="End_date"/></label>
            <form:input path="request.enddate" id="request_enddate" size="10" class="col_2"/>
            <form:errors path="request.startdate" class="error"/>
		</div>
		<div>
		 	<label for="label" class="col_2"><s:message code="Label"/></label>
		 	<form:input path="request.label1" type="text" class="col_8"/>
		</div>
		<div>
		 	<label for="duration" class="col_2"><s:message code="Duration"/></label>
		 	<form:input path="request.duration" type="text" class="col_2" style="display:inline;"/>
            <form:errors path="request.duration" class="error"/>
            
            <form:select path="request.durationunit" class="col_2">
                <c:forEach var="duration" items="${listDurationUnit}">
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
        <jsp:include page="_createRequest_Attach.jsp">
            <jsp:param name="requestType" value="Task"/>
        </jsp:include>

      <div>
        <input type="submit" value='<s:message code="Save"/>' class="button"/>
        <input type="reset" value='<s:message code="Reset"/>' class="button"/>
      </div>
  </form:form>
</div>