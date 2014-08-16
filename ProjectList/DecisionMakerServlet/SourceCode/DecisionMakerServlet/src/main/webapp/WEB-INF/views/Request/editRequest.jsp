<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<script type="text/javascript" src="resources/ckeditor-3.6.6.1/ckeditor.js"></script>
<script>

</script>
	<c:if test="${request.requesttypeCd == 'Announcement'}">
	<!-- 	Announcement -->
	   <jsp:include page="_editAnnouncement.jsp"></jsp:include>
	</c:if>
	<c:if test="${request.requesttypeCd == 'Rule'}">
	<!--    Rule -->
	    <jsp:include page="_editRule.jsp"></jsp:include>
	</c:if>
	<c:if test="${request.requesttypeCd == 'Task'}">   
	<!-- 	Task -->
	    <jsp:include page="_editTask.jsp"></jsp:include>  
	</c:if>
	<c:if test="${request.requesttypeCd == 'Leave'}">
		<!--    Leave -->
	    <jsp:include page="_editLeave.jsp"></jsp:include>
	</c:if>
