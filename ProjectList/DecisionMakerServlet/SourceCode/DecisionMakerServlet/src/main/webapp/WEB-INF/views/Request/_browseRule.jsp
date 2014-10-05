<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<link type="text/css" href="resources/css/app-validation.css" rel="stylesheet">
<script>
  $(function() {
	// The instanceReady event is fired, when an instance of CKEditor has finished
	// its initialization.
	CKEDITOR.on( 'instanceReady', function( ev )
    {
        editor = ev.editor;
        editor.setReadOnly(true);

        editor.config.toolbarStartupExpanded = false;  
    });

	CKEDITOR.replace( 'content', {toolbarStartupExpanded : false} );
  });
</script>
<!-- Rule -->
<div id="browse-Rule">
		<div>
  		  <H5>${model.request.title}</H5>
		</div>

		<div>
			<label for="content" class="col_2 left"><s:message code="Content"/>:</label>
            <textarea id="content" class="ckeditor col_8" rows="10" disabled>
              ${model.request.content}
            </textarea>
            <a href="#" onclick='viewRequestContent("viewRule?id=${model.request.id}", "<s:message code="Rule_content"/>")'><s:message code="View_in_another_window"/></a>
		</div>

        <div>
            <label for="scopes" class="col_2"><s:message code="Scope"/>:</label>
            <label class="col_8">${model.request.departmentName}</label>
        </div>
        
		<div id="attachment">
		  <label for="attachment0" class="col_2"><s:message code="Attach"/>:</label>
          <label class="col_8">
            <c:choose>
                <c:when test="${not empty model.request.filename1}">
                  <a href="downloadFile?id=${model.request.id}" target="_blank" title='<s:message code="Download_attachment"/>"'>${model.request.filename1}</a>
                </c:when>
                <c:otherwise>
                  <s:message code="NONE"/>
                </c:otherwise>
            </c:choose>
          </label>
        </div>
</div>