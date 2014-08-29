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

<textarea disabled="disabled" style="display:inline; position: relative; top:5px; left:-20px;" cols="70" rows="10" id="taskContent" name="taskContent" >${request.content}</textarea>