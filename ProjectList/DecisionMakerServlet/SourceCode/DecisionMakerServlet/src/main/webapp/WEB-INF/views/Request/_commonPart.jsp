<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Common part of creating Request -->
	<div>
	  <label for="type" class="col_2">Loại yêu cầu</label>
	   <select id="reqType" class="col_3" name="reqType">
         <option value="0">-- Lựa chọn --</option>
         <c:forEach var="reqType" items="${lstReqTypes}">
            <option value="${reqType.cd}">${reqType.name}</option>
         </c:forEach>
	  </select>
	</div>
