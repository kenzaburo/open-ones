<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional: Include the jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Optional: Incorporate the Bootstrap JavaScript plugins -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<title>Login</title>


</head>
<body>
<div class="container">
  <%-- header --%>
  <div class="header">
    <tiles:insertAttribute name="login-header" />
  </div>
  
  <!-- Content -->
  <div class="row">
    <%-- Left part --%>
    <div class="col-lg-9">
      <tiles:insertAttribute name="login-body-left" />
    </div>
    
    <%--Right part --%>
    <div class="col-lg-3">
      <tiles:insertAttribute name="login-body-right" />
    </div>
  </div>
  <div class="footer">
    <tiles:insertAttribute name="login-footer" />
  </div>
</div>
</body>
</html>