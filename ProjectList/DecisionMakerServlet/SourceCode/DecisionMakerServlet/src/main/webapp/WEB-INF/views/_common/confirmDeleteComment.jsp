<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
/**
 * @sUrl url to be submitted to server
 * @reqId request Id
 * @comId comment Id
 * @jsonData data to be transferred to server
 */
function showConfirmDialogComment(sUrl, reqId, comId) {
      $( "#dialog-confirm-delete" ).dialog({
          resizable: false,
          height:190,
          modal: true,
          buttons: {
              '<s:message code="Delete"/>': function() {
                  // Send http request to delete Comment
                  $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json',
                    url: sUrl,

                    data: {"reqId": reqId, "comId": comId},
                    success: function(res) {
                        location.reload(true);
                    },
                    error: function(res) {
                        alert("Fail");
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
