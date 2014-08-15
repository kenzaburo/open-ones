<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<script type="text/javascript" src="resources/ckeditor-3.6.6.1/ckeditor.js"></script>
<script type="text/javascript" src="resources/js/createRequest.js"></script>
<link rel="stylesheet" type="text/css" href="resources/chosen/chosen.min.css" />
<script>
	$(function() {
		if ("${result}" == 1) {
			alert("Yeu cau khoi tao thanh cong");
		}
	});
</script>

	<!-- 	Announcement -->
	   <jsp:include page="_createAnnouncement.jsp"></jsp:include>
	<!--    Rule -->
	    <jsp:include page="_createRule.jsp"></jsp:include>
	<!-- 	Task -->
	    <jsp:include page="_createTask.jsp"></jsp:include>  
	<!--    Leave -->
	    <jsp:include page="_createLeave.jsp"></jsp:include>
