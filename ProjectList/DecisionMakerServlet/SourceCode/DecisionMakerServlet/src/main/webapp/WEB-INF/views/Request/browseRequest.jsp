<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<%-- <script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script> --%>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<script type="text/javascript" src="resources/ckeditor-3.6.6.1/ckeditor.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<style>
#disabled {
   pointer-events: none;
   cursor: default;
   color:#A7A7A7; 
}
#disable{
	
	background:#dce5e8;
	pointer-events: none;
   	cursor: default; 
	-webkit-box-shadow:0px 0px 2px #bababa, inset 0px 0px 1px #ffffff; 
	-moz-box-shadow: 0px 0px 2px #bababa,  inset 0px 0px 1px #ffffff;  
	box-shadow:0px 0px 2px #bababa, inset 0px 0px 1px #ffffff;  
}

.disable {
    background:#dce5e8;
    pointer-events: none;
    cursor: default; 
}


</style>
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
      
      $( "#dialog-form1" ).dialog({
          autoOpen: false,
          height: 450,
          width: 700,
          modal: true,
          buttons: {
        	  '<s:message code="Done"/>': function() {
                  // Send http request to update Assginee
                  var frm = document.forms["finishRequest"];
                  var requestId = frm.elements["request.id"].value ; // $('#request.id').val();
                  var rateLevel = $("#level option:selected").val();
                  var rateLevelDetail = $("#level option:selected").text();
                  var confirmNote = $("#confirmNote").val();
					
                  alert(requestId);
                  alert(rateLevel);
                  $('#notification').html("Đang cập nhật...(" + requestId + "," + rateLevelDetail + ")");
                  $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json',
                    url: "confirm.Request",

                    data: {"id": requestId, "confirmNote": confirmNote, "rateLevel": rateLevel},
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

      $("#assignMember" ).click(function() {
          $( "#dialog-form" ).dialog( "open" );
      });
      $("#finishRequest" ).click(function() {
          $( "#dialog-form1" ).dialog( "open" );
      });
  });
</script>
<!-- Button Bar for Owner -->
<ul class="button-bar">
	<li><a href="editRequest?id=${model.request.id}"><i class="icon-edit"></i><s:message code="Edit"/></a></li>
	<li><a href="addComment?id=${model.request.id}"><i class="icon-comment"></i><s:message code="Comment"/></a></li>
	<li><a href="#" id="assignMember"><i class="icon-user-md"></i><s:message code="Assign"/></a></li>
    <%-- Setting label for button of owner --%>
	<c:if test="${not empty ownerNextStatus}">
		<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name == model.request.assigneeUsername}">
				<li><a href="updateStatus?id=${model.request.id}&status=${ownerNextStatus}&note=" title='<s:message code="Update_next_status"/>'><i class="icon-tasks"></i><s:message code="${ownerNextStatus}"/></a></li>
			</c:when>
			<c:otherwise>
	            <li class="disable"><a href="#" class="disable"><i class="icon-tasks disable"></i><s:message code="${ownerNextStatus}"/></a></li>
	    	</c:otherwise>
	    </c:choose>
	</c:if>
</ul>

<!-- Button Bar for Manager -->
<ul class="button-bar">
    <c:if test="${not empty managerNextStatus}">
      <c:choose>
          <c:when test="${pageContext.request.userPrincipal.name == model.request.managerUsername}">
              <li><a href="updateStatus?id=${model.request.id}&status=${managerNextStatus}&note=" title='<s:message code="Update_next_status"/>'><i class="icon-tasks"></i><s:message code="${managerNextStatus}"/></a></li>
          </c:when>
          <c:otherwise>
              <li class="disable"><a href="#" class="disable"><i class="icon-check disable"></i><s:message code="${managerNextStatus}"/></a></li>
          </c:otherwise>
      </c:choose>
    </c:if>
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
<div id="dialog-form1" title="<s:message code="Done"/>">
    <form:form name="finishRequest" class="horizontal" action="confirmRequest" modelAttribute="model" method="POST">
      <form:hidden path="request.id"/>
      <div>
        <label for="rateLevel" class="col_2"><s:message code="Level"/></label>
        <form:select path="" class="col_4 chosen-select" id="level">
               <option value="Bad"><s:message code="Bad"/></option>
               <option value="Normal"><s:message code="Normal"/></option>
               <option value="Good"><s:message code="Good"/></option>
               <option value="Perfect"><s:message code="Perfect"/></option>
               <option value="Excellent"><s:message code="Excellent"/></option>
        </form:select>
      </div>
      <div>
        <label for="note" class="col_2"><s:message code="Note"/></label>
        <form:textarea path="" style="display:inline; position: relative; top:6px; left:10px;" cols="45" rows="14" id="confirmNote"></form:textarea>
      </div>
      <div>
        <label id="notification"></label>
      </div>
    </form:form>
</div>
<c:if test='${model.request.requesttypeCd == "Task"}'>
    <jsp:include page="_browseTask.jsp"></jsp:include>
</c:if>

<c:if test='${model.request.requesttypeCd == "Leave"}'>
    <jsp:include page="_browseLeave.jsp"></jsp:include>
</c:if>

<c:if test='${model.request.requesttypeCd == "Announcement"}'>
    <jsp:include page="_browseAnnouncement.jsp"></jsp:include>
</c:if>

<c:if test='${model.request.requesttypeCd == "Rule"}'>
    <jsp:include page="_browseRule.jsp"></jsp:include>
</c:if>