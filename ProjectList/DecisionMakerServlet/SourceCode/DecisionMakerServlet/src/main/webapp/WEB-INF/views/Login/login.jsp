<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="center grid">
  <form action="j_spring_security_check" method='POST' class="vertical">
    <div class="col_5">
      <div>
        <label for="username" class="left">Tài khoản:</label>
          <input id="username" type="text" name='j_username'/>
      </div>
      <div>
        <label for="password" class="left">Mật khẩu:</label>
        <div>
          <input id="password" type="password" name='j_password'/>
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
  
      <div>
        <div>
          <button type="submit" id="loginBtn">Đăng nhập</button>
        </div>
        <div>
        <br/>
          Chương trình hiển thị tốt nhất trên trình duyệt: Chrome, Firefox.
        </div>
      </div>
    </div>
    
  </form>
  
</div>
