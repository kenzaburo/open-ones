<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- bootstrap 3.0.2 -->
<link href="resources/AdminLTE/css/AdminLTE.css" rel="stylesheet" type="text/css" />

<link href="resources/AdminLTE/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="resources/AdminLTE/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="resources/AdminLTE/css/ionicons.min.css" rel="stylesheet" type="text/css" />

<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">

<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>

 <%-- 
<script type="text/javascript" src="resources/js/common.js"></script>
--%>
<%@ include file="../_common/confirmDeleteComment.jsp" %>


<script src="resources/AdminLTE/js/bootstrap.min.js" type="text/javascript"></script>

<link href="resources/x-editable/css/bootstrap-editable.css" rel="stylesheet">
<script src="resources/x-editable/js/bootstrap-editable.min.js"></script>

<%-- Support inline edit when user click on a comment --%>
<script>
$(document).ready(function () {
  $('#commentContent').editable();  
  $.fn.editable.defaults.mode = 'inline';
});
</script>

<style>

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

<%-- For inline editor in comments --%>
<script>
  $.fn.editable.defaults.mode = 'inline';
  $(document).ready(function () {
    $('#commentContent').editable({
    });
  });
</script>

<!-- Button Bar for Owner -->
<ul class="button-bar">
	<li><a href="editRequest?id=${model.request.id}"><i class="icon-edit"></i><s:message code="Edit"/></a></li>
	<li><a href="addComment?id=${model.request.id}"><i class="icon-comment"></i><s:message code="Comment"/></a></li>
    <c:choose>
        <c:when test='${request.requesttypeCd == "Task"}'>
            <li><a href="#" id="assignMember"><i class="icon-user-md"></i><s:message code="Assign"/></a></li>
        </c:when>
        <c:otherwise>
            <li class="disable"><a href="#" class="disable"><i class="icon-user-md disable"></i><s:message code="Assign"/></a></li>
        </c:otherwise>
    </c:choose>
	
    <%-- Setting label for button of owner --%>
    <c:choose>
    	<c:when test="${not empty listOwnerNextStatus}">
          <c:forEach var="ownerNextStatus" items="${listOwnerNextStatus}">
    		<c:choose>
    			<c:when test="${pageContext.request.userPrincipal.name == model.request.assigneeUsername}">
    				<li><a href="updateStatus?id=${model.request.id}&status=${ownerNextStatus}&note=" title='<s:message code="Update_next_status"/>'><i class="icon-tasks"></i><s:message code="${ownerNextStatus}"/></a></li>
    			</c:when>
    			<c:otherwise>
    	            <li class="disable"><a href="#" class="disable"><i class="icon-tasks disable"></i><s:message code="${ownerNextStatus}"/></a></li>
    	    	</c:otherwise>
    	    </c:choose>
          </c:forEach>
    	</c:when>
       <c:otherwise>
         <li class="disable"><a href="#" class="disable" title='<s:message code="No_define"/>'><i class="icon-tasks disable"></i><s:message code="No_define"/></a></li>
       </c:otherwise>
   </c:choose>
</ul>

<!-- Button Bar for Manager -->
<ul class="button-bar">
    <c:if test="${not empty listManagerNextStatus}">
     <c:forEach var="managerNextStatus" items="${listManagerNextStatus}">
      <c:choose>
          <c:when test="${pageContext.request.userPrincipal.name == model.request.managerUsername}">
              <li><a href="updateStatus?id=${model.request.id}&status=${managerNextStatus}&note=" title='<s:message code="Update_next_status"/>'><i class="icon-tasks"></i><s:message code="${managerNextStatus}"/></a></li>
          </c:when>
          <c:otherwise>
              <li class="disable"><a href="#" class="disable"><i class="icon-check disable"></i><s:message code="${managerNextStatus}"/></a></li>
          </c:otherwise>
      </c:choose>
      </c:forEach>
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

<%-- Display comments --%>
<c:if test="${not empty listComment}">
<ul class="timeline">
    <!-- timeline time label -->
    <li class="time-label">
        <span>
            <s:message code="Comment"/>
        </span>
    </li>
    <!-- /.timeline-label -->

    <!-- timeline item -->
    <c:forEach var="comment" items="${listComment}">
    <li>
        <!-- timeline icon -->
        <i class="fa  fa-comments"></i>
        <div class="timeline-item">
            <span class="time"><i class="fa fa-clock-o"></i><fmt:formatDate value="${comment.created}" pattern="${DATETIME_FORMAT}"/></span>

            <span class="timeline-header">${comment.username}</span>

            <div class="timeline-body">
              <a href="#" id="commentContent" data-type="textarea" data-name="${comment.id}" data-pk="${model.request.id}" data-title="Enter comments" data-url="updateComment?requestId=${model.request.id}&commentId=${comment.id}">${comment.content}</a>
            </div>

            <div class='timeline-footer'>
             <c:if test="${comment.createdbyUsername == pageContext.request.userPrincipal.name}">
               <div class="input-group-btn">
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="border: NONE" title="Thao tác"><i class="icon-cog"></i><span class="fa fa-caret-down"></span></button>
                  <ul class="dropdown-menu">
                    
                      <li><a href="#" onclick='showConfirmDialogComment("deleteComment", "${model.request.id}", "${comment.id}")' title='<s:message code="Delete"/>'><s:message code="Delete"/></a></li>
                      <li><a href="#" title='<s:message code="Edit"/>'><s:message code="Edit"/></a></li>
                    
                  </ul>
              </div><!-- /btn-group -->
             </c:if>
            </div>
        </div>
    </li>
    </c:forEach>
    <!-- END timeline item -->
</ul>
</c:if>
<div>
<br/>
<input type="submit" disabled="disabled" value='<s:message code="Back"/>' class="button"/>
</div>