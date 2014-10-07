<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Maker Decision - Default Page</title>

<!-- KICKSTART -->
<script src="resources/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<script src="resources/htmlkickstart/js/kickstart.js"></script> 
<link rel="stylesheet" href="resources/htmlkickstart/css/kickstart.css" media="all" /> 
<link rel='stylesheet' href='resources/css/main.css' type='text/css' />

</head>
<body>


  <!--header-->
  <div id="header-container">
    <tiles:insertAttribute name="header" ignore="true" />
  </div>
  
  <div id="menu-container">
    <tiles:insertAttribute name="menu" />
  </div>

<div class="grid">
  <!-- Content -->
  <div id="body-container">
    <tiles:insertAttribute name="body" />
  </div>
</div> <!-- End Grid -->

  <div id="footer-container">
    <tiles:insertAttribute name="footer" />
  </div>


</body>
</html>