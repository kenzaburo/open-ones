<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>
<script type="text/javascript" src="resources/ckeditor-3.6.6.1/ckeditor.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript" src="resources/js/createRequest.js"></script>
<link rel="stylesheet" type="text/css" href="resources/chosen/chosen.min.css" />
<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/i18n/jquery.ui.datepicker-vi.js"></script>
<script type="text/javascript" src="resources/js/validateFunction.js"></script>

<%--
 Dialog confirm is used in create requests: Task | Leave | Rule | Announcement
 --%>
<script>

  $(function() {
    $("dialog-confirm-delete-attachmentTask").hide();
    $("dialog-confirm-delete-attachmentLeave").hide();
    $("dialog-confirm-delete-attachmentRule").hide();
    $("dialog-confirm-delete-attachmentAnnouncement").hide();
  }); 

  function showConfirmDialog(requestId, requestTitle) {
    var selectedReqType = $("#reqType").val();  
    var dialogId = "#dialog-confirm-delete-attachment" + selectedReqType;
	  
      $(dialogId).dialog({
          resizable: false,
          height:140,
          modal: true,
          buttons: {
              "<s:message code="Delete"/>": function() {
                  // Send http request to delete Announcement
                  $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json',
                    url: "deleteAttachment",

                    data: {"id": requestId, "fileId" : 1},
                    success: function(res) {
                        // alert("success:" + res.status);
                        // display attachment
                        var attachHtml = '<label for="attachment0" class="col_2">';
                            attachHtml += '<s:message code="Attach"/>'; 
                            attachHtml +=  '</label>';
                        attachHtml += '<input name="attachments[0]" type="file" class="col_8"/>';
                        
                        $("#attachment" + selectedReqType).html(attachHtml);
                    },
                    error: function(res) {
                        alert("error:" + res.status);
                        //document.forms['listAnnouncement'].submit();
                    }               
                  });
                  $( this ).dialog( "close" );
              },
              '<s:message code="No_delete"/>': function() {
                  $( this ).dialog( "close" );
              }
          }
      });
  }
</script>

<%-- Confirmation dialog for Delete attached file  --%>
<div id="dialog-confirm-delete-attachmentTask" style="display: NONE" title='<s:message code="Delete_the_attachment"/>?'>
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><s:message code="The_attachment_will_be_deleted"/>.</p>
</div>
<div id="dialog-confirm-delete-attachmentLeave" style="display: NONE" title='<s:message code="Delete_the_attachment"/>?'>
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><s:message code="The_attachment_will_be_deleted"/>.</p>
</div>
<div id="dialog-confirm-delete-attachmentRule" style="display: NONE" title='<s:message code="Delete_the_attachment"/>?'>
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><s:message code="The_attachment_will_be_deleted"/>.</p>
</div>
<div id="dialog-confirm-delete-attachmentAnnouncement" style="display: NONE" title='<s:message code="Delete_the_attachment"/>?'>
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><s:message code="The_attachment_will_be_deleted"/>.</p>
</div>

  <%-- Display the result/error --%>
  <c:if test='${SAVE_STATUS != null && SAVE_STATUS == "SUCCESS"}'>
    <div id="" class="notice success">
        <i class="icon-ok icon-large"></i>Lưu thành công!
        <a href="#close" class="icon-remove"></a>
    </div>
  </c:if>

<div>
  <label for="requestTypeCd" class="col_2">Loại yêu cầu</label>
      <select id="reqType" class="col_3" name="reqType">
         <option value="0">-- Lựa chọn --</option>
         <c:forEach var="reqType" items="${listRequestType}">
           <c:choose>
             <c:when test='${reqType.cd == model.request.requesttypeCd}'>
               <option value="${reqType.cd}" selected="selected">${reqType.name}</option>
             </c:when>
             <c:otherwise>
               <option value="${reqType.cd}">${reqType.name}</option>
             </c:otherwise>
           </c:choose>
         </c:forEach>
      </select>
</div>      
	<!-- 	Announcement -->
	   <jsp:include page="_createAnnouncement.jsp"></jsp:include>
	<!--    Rule -->
	    <jsp:include page="_createRule.jsp"></jsp:include>
	<!-- 	Task -->
	    <jsp:include page="_createTask.jsp"></jsp:include>  
	<!--    Leave -->
	    <jsp:include page="_createLeave.jsp"></jsp:include>
