<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<form:form enctype="multipart/form-data" action="saveComment" method="POST">
	<input type="hidden" name="requestId" value="${requestId}"/>
	<div style="position:relative; top:10px;">
		<label for="content" class="col_2 left">Comment:</label>
		<br>
		<textarea name="comment.content" id="content" style="display:inline; position: relative; top:6px; left:10px;" cols="100" rows="15" placeholder=""></textarea>
	</div>
	<div style="position:relative; top:40px; left: 3%">
		<input type="submit" value="Add">
<!-- 		<a class="button" id="addComment">Add</a> -->
		<a class="button" href="browseRequest.html?id=${requestId}"><s:message code="Back"/></a>
	</div>
</form:form>