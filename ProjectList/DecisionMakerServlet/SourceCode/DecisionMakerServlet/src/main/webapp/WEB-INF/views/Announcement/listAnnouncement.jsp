<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="resources/jquery/1.9.1/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/jquery-ui/1.9.2/ui/jquery-ui-1.9.2.js"></script>
<link type="text/css" href="resources/jquery-ui/1.9.2/themes/base/jquery.ui.all.css" rel="stylesheet">
<script>
  $(function() {
    $("#dialog-confirm").hide();
  }); 

  function showConfirmDialog(requestId, requestTitle) {

	  $( "#dialog-confirm" ).dialog({
          resizable: false,
          height:140,
          modal: true,
          buttons: {
              "Xóa": function() {
                  // Send http request to delete Announcement
                  $.ajax({
                    type: 'GET',
                    dataType: 'json',
                	contentType: 'application/json',
                    url: "deleteRequest",

                    data: {"id": requestId}                 
                  });
                  $( this ).dialog( "close" );
              },
              Cancel: function() {
                  $( this ).dialog( "close" );
              }
          }
      });
  }
  function viewRequestContent(viewUrl) {
    // alert("Open windows url=" + viewUrl);
    if (window.showModelessDialog) {        // Internet Explorer
        showModelessDialog (viewUrl, window, "dialogWidth:980px; dialogHeight:620px");
    } 
    else {
        window.open (viewUrl, "Nội dung thông báo","width=980, height=620, alwaysRaised=yes");
    }
  }

  function confirmDelete(requestId, requestTitle) {
    var confirmVal = confirm("Bạn chắc chắn muốn xóa thông báo này?<br/>" + 
          "[" + requestId + "]" + requestTitle);
    if (confirmVal) {
    } else {
    }
  }
</script>
<div id="dialog-confirm" title="Xóa thông báo?">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Thông báo sẽ bị xóa hoàn toàn và không thể khôi phục lại được. Bạn có chắc xóa không?</p>
</div>

<table class="sortable">
  <thead>
    <tr>
      <th width="20px">STT</th>
      <th width="500px">Thông báo</th>
      <th width="200px">Đính kèm</th>
      <th>Hành động</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="request" items="${lstAnnouncement}" varStatus="status">
      <tr>
        <td>${status.count}.</td>
        <td><a href="#" onclick='viewRequestContent("viewAnnouncement?id=${request.id}")'>${request.title}</a></td>
        <td></td>
        <td>
          <a href="#" title="Bạn thích thông báo này"><i class="icon-thumbs-up"></i></a>
          <s:authorize access="hasRole('ROLE_ADMIN')">
            <a href="editRequest?id=${request.id}" title="Sửa"><i class="icon-edit"></i></a>
            <a href="#" onclick='showConfirmDialog("${request.id}", "${request.title}")' title="Xóa"><i class="icon-remove"></i></a>
           </s:authorize>
         </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
