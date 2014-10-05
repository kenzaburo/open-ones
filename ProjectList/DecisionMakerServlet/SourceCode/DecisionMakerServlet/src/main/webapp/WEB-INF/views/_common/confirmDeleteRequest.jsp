<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
  function showConfirmDialog(requestId, requestTitle) {
      $( "#dialog-confirm-delete" ).dialog({
          resizable: false,
          height:190,
          modal: true,
          buttons: {
              '<s:message code="Delete"/>': function() {
                  // Send http request to delete Announcement
                  $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json',
                    url: "deleteRequest",

                    data: {"id": requestId},
                    success: function(res) {
                        alert(res);
                        location.reload(true);
                    },
                    error: function(res) {
                        location.reload(true);
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
<div id="dialog-confirm-delete" title='<s:message code="Confirmation"/>?' style="display: NONE">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><s:message code="Confirm_delete_content"/>?</p>
</div>
