<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>


</head>
<body class="loginPage">
  <div class="row" style="margin-top: 20px">
    <div class="container">
      <a href="#"><img title="Decision Maker" alt="Decison Maker" src="resources/images/logo.png"
        class="bannerLogin"></a>
    </div>
  </div>
  <!-- End .row -->
  <!-- Content -->
  <div class="container">
    <tiles:insertAttribute name="body" />
  </div>


  <script>
			$("#wizard")
					.validate(
							{
								rules : {
									firstname : "required",
									lastname : "required",
									username : {
										required : true,
										minlength : 2
									},
									password : {
										required : true,
										minlength : 5
									},
									newpassword : {
										required : true,
										minlength : 5,
									},
									confirm_newpassword : {
										required : true,
										minlength : 5,
										equalTo : "#newpassword"
									},
									email : {
										required : true,
										email : true
									},
									topic : {
										required : "#newsletter:checked",
										minlength : 2
									},
									agree : "required"
								},
								messages : {
									firstname : "Please enter your firstname",
									lastname : "Please enter your lastname",
									username : {
										required : "Please enter a username",
										minlength : "Your username must consist of at least 2 characters"
									},
									password : {
										required : "Please provide a password",
										minlength : "Your password must be at least 5 characters long"
									},
									newpassword : {
										required : "Please provide a password",
										minlength : "Your password must be at least 5 characters long"
									},
									confirm_newpassword : {
										required : "Please provide a password",
										minlength : "Your password must be at least 5 characters long",
										equalTo : "Please enter the same password as above"
									},
									email : "Please enter a valid email address",
									agree : "Please accept our policy"
								}
							});
		</script>
</body>
</html>