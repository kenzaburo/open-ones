<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
.login-form {
  border: 1px solid rgba(150, 150, 150, 0.34);
  padding: 20px 10px 10px 10px;
  position: relative;
  margin-bottom: 8px
}

.login-form hr {
  margin: 0;
  margin-top: -10px
}

.login-form .title {
  position: absolute;
  background: #FFF;
  top: -8px;
  font-size: 18px;
  padding: 0 10px
}
</style>

 <div class="row">
	<div class="col-lg-9">
	  <div class="span8">
         <H3>Quản lý công việc hiệu quả hơn với <strong>Decision Maker System</strong></H3>
         <div class="callout callout-info">
           Quản lý Công việc, Thông báo, Quy định, Đơn nghỉ phép trực tuyến
         </div> 
         <br/>
         <a href="#" class="btn btn-success btn-sm">Dùng thử ngay</a>
	  </div>
	</div>
	<div class="col-lg-3">
		  <div class="large-12 login-form">
		    <span class="title">Đăng nhập</span>
		  <form action="j_spring_security_check" method='POST' role="form">
		    <div class="col_5">
		      <div class="form-group">
		        <label for="username"><s:message code="Username"/>:</label>
		          <input id="username" type="text" name='j_username' class="form-control"/>
		      </div>
		      <div class="form-group">
		        <label for="password"><s:message code="Password"/>:</label>
		        <div>
		          <input id="password" type="password" name='j_password' class="form-control"/>
		        </div>
		      </div>
		  
		  
		      <div>
		        <c:if test="${not empty error}">
		          <p>${error}</p>
		        </c:if>
		        <c:if test="${not empty success}">
		          <p>${success}</p>
		        </c:if>
		      </div>
		  
		          <button type="submit" id="loginBtn">Đăng nhập</button>
                  <a href='reset-password'><s:message code="Forgot_password"/></a>
		        <br/>
		         <small>Chương trình hiển thị tốt nhất trên trình duyệt: Chrome, Firefox.</small>
		    </div>
		    
		  </form>
		  </div>
	</div>
</div>
