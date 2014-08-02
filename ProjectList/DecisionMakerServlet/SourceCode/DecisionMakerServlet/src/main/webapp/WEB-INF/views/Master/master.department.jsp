<!--
 Screen: Manage master "Department"
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">

<link rel="stylesheet" href="resources/jstree/themes/default/style.min.css" />
<script src="resources/jstree/jstree.min.js"></script>

<form:form action="saveMasterDepartment" method="POST">
  <div id="jstree_demo_div" class="col_3">
  </div>
  <div class="col_9">
    <div>
      <label for="parentDepartment">Chọn phòng ban</label>
       <form:select id="parentDepartment" path="parentDepartment">
        <option value="0">Công ty</option>
      </form:select>
    </div>

    <div>
        <div id="dataTable"></div>
<%--         <form:input type="hidden" id="initDepartmentData" path="jsonDepartments"/> --%>
<!-- <input type="hidden" id="initDepartmentData" value="[['','','','']]"/> -->
        <div id="separator"></div>
        <a id="save" class="button" href="master.department">Lưu</a>
    </div>
  </div>
</form:form>
<script>
  $(function () {
    $('#jstree_demo_div').jstree({
    		  "core" : {
    			    "animation" : 0,
    			    "check_callback" : true,
    			    "themes" : { "stripes" : true },
    			    'data' : {
    			      'url' : function (node) {
    			        return node.id === '#' ?
    			          'master.department.getNodeRoot' : 'master.department.getNodeChildren';
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
    			      "icon" : "/static/3.0.2/assets/images/tree_icon.png",
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
      // var departmentData = $('#initDepartmentData').val();
//       var departmentData = [['','','','']];
      
//       alert("departmentData=" + departmentData);

        var container = $("#dataTable");
        var parent = container.parent();
        container.handsontable({
            startRows: 5,
            // dataShema: [cd: null, name: null, manager: null, note: null],
            startCols: 4,
            rowHeaders: true,
            colHeaders: ['Mã phòng ban', 'Tên phòng ban', 'Trưởng phòng', 'Ghi chú'],
            colWidths: [90, 150, 100, 150],
            manualColumnResize: true,
            minSpareRows: 1
        });

        $("#save").click(function() {
            var tableData = container.handsontable('getData');

            var parentDepartment = $('#parentDepartment').val();

            var formDataJson = JSON.stringify({"parentDepartment": parentDepartment,  "data":tableData});
            
            $.ajax({
                type: "POST",
            	dataType: 'json',
                contentType: 'application/json',
                url: "saveMasterDepartment",
                data: formDataJson,
                success: function(res) {
                    alert(res.data);
                    window.location = "master.department";
                },
                error: function() {
                    window.location = "master.department";
                }
            });
        });

        // Load current data of department
        $.ajax({
            url: "master.department.load",
            dataType: 'json',
            type: 'GET',
            success: function (res) {
              var handsontable = container.data('handsontable');
              handsontable.loadData(res.data);
            },
            error: function() {
            	alert("Không thể lấy dữ liệu của Phòng ban. Hãy liên hệ người quản trị hệ thống.");
            }
          });
    });


</script>