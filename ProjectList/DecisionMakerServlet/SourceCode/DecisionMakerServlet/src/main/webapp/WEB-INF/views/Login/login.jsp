<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="loginContainer">
  <form id="wizard" name='f' action="<c:url value='j_spring_security_check' />"
    method='POST' class="form-horizontal" role="form">
    <div class="form-group" style="border-bottom: solid 1px #c4c4c4">
      <h4 class="col-lg-12">Management Account LDAP</h4>
    </div>
    <div class="form-group">
      <label class="col-lg-12 control-label" for="username">Username:</label>
      <div class="col-lg-12">
        <input id="username" type="text" name='j_username'
          class="form-control required" placeholder="Enter your username ...">
        <span class="icon16 icomoon-icon-user right gray marginR10"></span>
      </div>
    </div>
    <!-- End .form-group  -->
    <div class="form-group">
      <label class="col-lg-12 control-label" for="password">Password:</label>
      <div class="col-lg-12">
        <input id="password" type="password" name='j_password'
          class="form-control required"  minlength='6' placeholder="Enter your password ...">
      </div>
    </div>


    <div class="form-group">
      <c:if test="${not empty error}">
        <p class="col-lg-12 text-danger">${error}</p>
      </c:if>
      <c:if test="${not empty success}">
        <p class="col-lg-12 text-success">${success}</p>
      </c:if>
    </div>
    <!-- End .form-group  -->
    <div class="form-group">
      <div class="col-lg-12 clearfix form-actions">
        <img alt="logofpt" src="resources/images/logo.png">
        <button type="submit" class="btn btn-info right" id="loginBtn">Login</button>
      </div>
    </div>
    <!-- End .form-group  -->
  </form>
</div>
