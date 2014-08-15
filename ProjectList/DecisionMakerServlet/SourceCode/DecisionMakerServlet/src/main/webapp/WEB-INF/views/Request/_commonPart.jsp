<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Common part of creating Request -->
	<div>
	  <label for="reqType" class="col_2">Loại yêu cầu</label>
	   <select id="reqType" class="col_3" name="reqType" onchange="displayDetailedRequest('${param.formName}');">
         <option value="0">-- Lựa chọn --</option>
         <c:forEach var="reqType" items="${lstReqTypes}">
           <c:choose>
             <c:when test="${reqType.cd == param.newReqType}">
               <option value="${reqType.cd}" selected="selected">${reqType.name}</option>
             </c:when>
             <c:otherwise>
               <option value="${reqType.cd}">${reqType.name}</option>
             </c:otherwise>
           </c:choose>
         </c:forEach>
	  </select>
	</div>
