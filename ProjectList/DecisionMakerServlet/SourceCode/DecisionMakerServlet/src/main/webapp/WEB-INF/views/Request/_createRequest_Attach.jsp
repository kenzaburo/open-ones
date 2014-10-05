<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

    <%-- Refer: http://crunchify.com/spring-mvc-tutorial-how-to-upload-multiple-files-to-specific-location/ --%>
        <div id="attachment${param.requestType}">
          <label for="attachment0" class="col_2"><s:message code="Attach"/></label>
          <c:choose>
              <c:when test="${not empty model.request.filename1}">
              
                <a href="downloadFile?id=${model.request.id}" target="_blank" title='<s:message code="Download_attachment"/>'>${model.request.filename1}</a>
                
                <a href="#" onclick='showConfirmDialog("${model.request.id}", "${model.request.title}")' title="Xóa ${model.request.filename1}"><i class="icon-trash"></i></a>
              </c:when>
              <c:otherwise>
                <input name="attachments[0]" type="file" class="col_8"/>
              </c:otherwise>
          </c:choose>
          <form:hidden path="request.filename1"/>
        </div>