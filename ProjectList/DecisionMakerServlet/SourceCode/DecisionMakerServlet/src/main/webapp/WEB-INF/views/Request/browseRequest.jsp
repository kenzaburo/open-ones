<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!-- Button Bar w/icons -->
<ul class="button-bar">
<li><a href=""><i class="icon-edit"></i> Sửa</a></li>&nbsp;&nbsp;
<li><a href=""><i class="icon-comment"></i> Comment</a></li>
<li><a href=""><i class="icon-user-md"></i> Giao việc</a></li>
<li><a href=""><i class="icon-tasks"></i> Thực hiện</a></li>
<li><a href=""><i class="icon-check"></i> Kết thúc</a></li>
</ul>

<c:if test="${model.request.requesttypeCd == 'Task'}">
    <jsp:include page="_browseTask.jsp"></jsp:include>
</c:if>
