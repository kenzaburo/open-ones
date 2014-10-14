<!--
 Screen: Manage master "Department"
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">

<link rel="stylesheet" href="resources/jstree/themes/default/style.min.css" />
<script src="resources/jstree/jstree.min.js"></script>

<%-- Left tree --%>
  <div id="jstree_ldap_div" class="col_3">
  </div>
  <div class="col_9">
   <form>
    <div>
      <s:message code="Selected_group"/>:&nbsp;<input type="text" id="grouptext"></input>
      
      <input type="hidden" id="groupinput" name="groupou"></input>
              
        <div id="dataTable"></div>

        <div id="separator"></div>
        <a id="save" class="button" href="#"><s:message code="Save_account_to_app"/></a>
    </div>
    <div>
        <label id="notification" class="error"></label>
    </div>
    </form>
  </div>

<script>
  $(function () {
    $('#jstree_ldap_div')
         .on('changed.jstree', function(e, data) {
                            console.log("Selected id:" + data.selected);
                            var id = data.selected[0];
                            
                            if (typeof data.node !== "undefined") {
                              var dn = data.node["a_attr"].dn;
                              console.log("Selected dn:" + dn);

                              $('#groupinput').val(dn);
                              $('#grouptext').val(data.node["text"]);
                            }

                            // Load current data of account
                            $.ajax({
                                url: "account.load",
                                dataType: 'json',
                                data: {"groupDn" : dn},
                                type: 'GET',
                                success: function (res) {
                                  var handsontable = container.data('handsontable');
                                  handsontable.loadData(res.data);
                                },
                                error: function() {
                                    // alert("Không thể lấy dữ liệu của Phòng ban. Hãy liên hệ người quản trị hệ thống.");
                                }
                              });
                            
                        })
         .jstree({
    		  "core" : {
    			    "animation" : 0,
    			    "check_callback" : true,
    			    "themes" : { "stripes" : true },
    			    'data' : {
    			      'url' : function (node) {
    			        return node.id === '#' ?
    			          'ldap.getNodeRoot' : 'ldap.getNodeChildren';
    			      },
    			      'dataType' : 'JSON',
    			      'data' : function (node) {
    			        return { 'id' : node.id };
    			      }
    			    }
    			  },
    			  "types" : {
    			    "#" : {
    			      "max_children" : 1, 
    			      "max_depth" : 4, 
    			      "valid_children" : ["root"]
    			    },
    			    "root" : {
    			      "icon" : "",
    			      "valid_children" : ["default"]
    			    },
    			    "default" : {
    			      "valid_children" : ["default","file"]
    			    },
    			    "file" : {
    			      "icon" : "glyphicon glyphicon-file",
    			      "valid_children" : []
    			    }
    			  },
    			  "plugins" : [
    			    "contextmenu", "dnd", "search",
    			    "state", "types", "wholerow"
    			  ]
    }      
    );
    }
  );
</script>

<script>
    $(document).ready(function() {

        var container = $("#dataTable");
        var parent = container.parent();
        container.handsontable({
            startRows: 5,
            // dataShema: [cd: null, name: null, manager: null, note: null],
            startCols: 5,
            rowHeaders: true,
            colHeaders: ['Tài khoản', 'Email', 'Họ', 'Tên', 'Trạng thái'],
            colWidths: [160, 160, 150, 100],
            manualColumnResize: true,
            minSpareRows: 1
        });

        $("#save").click(function() {
            var tableData = container.handsontable('getData');

            var groupDn = $('#grouptext').val();

            var formDataJson = JSON.stringify({"groupDn": groupDn,  "data":tableData});
            
            $.ajax({
                type: "POST",
            	dataType: 'json',
                contentType: 'application/json',
                url: "processLoadAccountLdap",
                data: formDataJson,
                success: function(res) {
                	location.reload(true);
                },
                error: function(res) {
                	$('#notification').html("Có lỗi.");
                }
            });
        });
    });


</script>