<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<%-- <script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script> --%>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>

<script>
  $(function() {
      var assignUsername = $( "#request.assigneeUsername" ),
      assignNote = $( "#request.assigneeNote" ),
      allFields = $( [] ).add( assignUsername ).add( assignNote );
      
      $( "#dialog-form" ).dialog({
          autoOpen: false,
          height: 450,
          width: 700,
          modal: true,
          buttons: {
        	  '<s:message code="Assign"/>': function() {
                  // Send http request to update Assginee
                  var frm = document.forms["assignMember"];
                  var requestId = frm.elements["request.id"].value ; // $('#request.id').val();
                  var assigneeUsername = frm.elements['request.assigneeUsername'].value;
                  var assigneeNote = frm.elements['request.assigneeNote'].value;

                  $('#notification').html("Đang cập nhật...(" + requestId + "," + assigneeUsername + ")");
                  $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json',
                    url: "updateAssignee",

                    data: {"id": requestId, "assigneeUsername": assigneeUsername, "assigneeNote": assigneeNote},
                    success: function(res) {
                      $('#notification').html("Thành công");
                      // Refresh Assignee
                      // window.location.reload();
                      location.reload(true);
                    },
                    error: function(res) {
                    	$('#notification').html("Có lỗi.");
                    }               
                  });
                  $( this ).dialog( "close" );
              },
              '<s:message code="Cancel"/>': function() {
                  $( this ).dialog( "close" );
              }
          },
          close: function() {
              allFields.val( "" ).removeClass( "ui-state-error" );
          }
      });

      $("#assignMember" )
      .click(function() {
          $( "#dialog-form" ).dialog( "open" );
      });
  });
</script>
<!-- Button Bar w/icons -->
<ul class="button-bar">
<li><a href="editRequest?id=${model.request.id}"><i class="icon-edit"></i> Sửa</a></li>
<li><a href="#"><i class="icon-comment"></i> Comment</a></li>
<li><a href="#" id="assignMember"><i class="icon-user-md"></i><s:message code="Assign"/></a></li>
<li><a href="#" id="updateStatus"><i class="icon-tasks"></i> Thực hiện</a></li>
<li><a href="#" id="finish"><i class="icon-check"></i> Kết thúc</a></li>
</ul>

<div id="dialog-form" title="<s:message code="Assign"/>">
    <form:form name="assignMember" class="horizontal" action="updateAssignee" modelAttribute="model" method="POST">
      <form:hidden path="request.id"/>
      <div>
        <label for="assigneeUsername" class="col_2"><s:message code="Assignee"/></label>
        <form:select path="request.assigneeUsername" class="col_4 chosen-select">
               <option value=""></option>
            <c:forEach var="user" items="${listUser}">
              <c:choose>
                <c:when test="${model.request.assigneeUsername == user.username}">
                  <option value="${user.username}" selected="selected">${user.username}</option>
                </c:when>
                <c:otherwise>
                  <option value="${user.username}">${user.username}</option>
                </c:otherwise>
              </c:choose>
                
            </c:forEach>
        </form:select>
      </div>
      <div>
        <label for="note" class="col_2"><s:message code="Note"/></label>
        <form:textarea path="request.assigneeNote" style="display:inline; position: relative; top:6px; left:10px;" cols="45" rows="14"></form:textarea>
      </div>
      <div>
        <label id="notification"></label>
      </div>
    </form:form>
</div>
<c:if test='${model.request.requesttypeCd == "Task"}'>
    <jsp:include page="_browseTask.jsp"></jsp:include>
</c:if>
