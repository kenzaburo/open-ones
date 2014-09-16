<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--    Rule -->
<div id="make-rule">
  <form:form name="createRule" class="horizontal" enctype="multipart/form-data" action="saveRequest" modelAttribute="model" method="POST">
    <input id="request.requesttypeCd" name="request.requesttypeCd" type="hidden" value="Rule"/>
    <form:hidden path="request.id"/>
    <!--  Select type Request -->
<!--     <div> -->
<!--       <label for="request.requesttypeCd" class="col_2">Loại yêu cầu</label> -->
<%--        <form:select path="request.requesttypeCd"  id="reqType" class="col_3" name="reqType" onchange="displayDetailedRequest('createRule');"> --%>
<!--          <option value="0">-- Lựa chọn --</option> -->
<%--          <c:forEach var="reqType" items="${listRequestType}"> --%>
<%--            <c:choose> --%>
<%--              <c:when test='${reqType.cd == "Rule"}'> --%>
<%--                <option value="${reqType.cd}" selected="selected">${reqType.name}</option> --%>
<%--              </c:when> --%>
<%--              <c:otherwise> --%>
<%--                <option value="${reqType.cd}">${reqType.name}</option> --%>
<%--              </c:otherwise> --%>
<%--            </c:choose> --%>
<%--          </c:forEach> --%>
<%--       </form:select> --%>
<!--     </div> -->
        <div>
          <label for="request.title" class="col_2">Quy định</label>
          <form:input path="request.title" id="request.title" type="text" required="required" class="col_8"/>
        </div>
        
        <div>
            <label for="content" class="col_2">Nội dung</label>
            <form:textarea path="request.content" id="request.contentRule" class="ckeditor" rows="10"/>
        </div>
        
        <div>
          <label for="scopes" class="col_2">Phạm vi áp dụng</label>
          <select id="scopes" multiple="multiple" class="col_3 fancy">
            <option value="0">Tất cả</option>
          </select>
        </div>
        
        <div>
          <label for="attachment" class="col_2">Đính kèm</label>
          <input id="attachment" type="file" class="col_8"/>
        </div>

      <div>
        <input type="submit" value="Save" class="button"/>
         <input type="reset" value="Reset" class="button" />
      </div>
  </form:form>
</div>