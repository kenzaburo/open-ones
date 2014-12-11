<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">

<div>
  <H5><s:message code="Request_type"/></H5>
      <div>
        <div>
          <label id="labelRequestType"></label>
        </div>
        <div id="dataTableRequestType"></div>
        <a id="saveRequestType" class="button" href="#"><s:message code="Save"/></a>
      </div>
  <H5><s:message code="Department"/></H5>
  <div>
    <div>
      <label id="labelDepartment"></label>
    </div>  
    <div id="dataTableDepartment"></div>
    <a id="saveDepartment" class="button" href="saveDepartment"><s:message code="Save"/></a>
  </div>

  <H5><s:message code="User"/></H5>
  <div>
    <div>
      <label id="labelUser"></label>
    </div>
    <div id="dataTableUser"></div>
    <a id="saveUser" class="button" href="saveUser"><s:message code="Save"/></a>
  </div>
  
  <H5><s:message code="Status_flow_request"/></H5>
  <div>
    <div>
      <label id="labelStatusFlowRequest"></label>
    </div>
    <div id="dataTableStatusFlowRequest"></div>
    <a id="saveStatusFlowRequest" class="button" href="saveStatusFlowRequest"><s:message code="Save"/></a>
  </div>

  <H5><s:message code="Parameter"/></H5>
  <div>
    <div>
      <label id="labelParameter"></label>
    </div>
    <div id="dataTableParameter"></div>
    <a id="saveParameter" class="button" href="saveParameter"><s:message code="Save"/></a>
  </div>

</div>
    
<div class="box-body table-responsive">
</div>

<!-- Request types -->
<script>
    $(document).ready(function() {

        var container = $("#dataTableRequestType");
        var parent = container.parent();
        container.handsontable({
            startRows: 4,
            startCols: 3,
            rowHeaders: true,
            colHeaders: ['<s:message code="Code"/>', '<s:message code="Name"/>', '<s:message code="Status"/>'],
            colWidths: [150, 200, 150],
            manualColumnResize: true,
            minSpareRows: 1
        });

        // Load current data of request types
        $.ajax({
            url: "load-request-type",
            dataType: 'json',
            type: 'GET',
            success: function (res) {
              var handsontable = container.data('handsontable');
              handsontable.loadData(res.data);
              if (res.isSample) {
            	  $('#labelRequestType').html('<s:message code="Sample_data"/>');
              } else {
            	  $('#labelRequestType').html('<s:message code="Current_data"/>');
              }
            },
            error: function() {
            	alert('<s:message code="Could_not_load_data"/>: <s:message code="Request_type"/>');
            }
        });

        // Save
        $("#saveRequestType").click(function() {
            var tableData = container.handsontable('getData');

            var formDataJson = JSON.stringify({"data":tableData});

            $.ajax({
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                url: "saveAllRequestType",
                data: formDataJson,
                success: function(res) {
                    location.reload(true);
                },
                error: function(data, status) {
                	alert(status);
					alert(JSON.stringify(data));
                }
            });
        });
    });
</script>

<!-- Department -->
<script>
    $(document).ready(function() {

        var container = $("#dataTableDepartment");
        var parent = container.parent();
        container.handsontable({
            startRows: 1,
            startCols: 5,
            rowHeaders: true,
            colHeaders: ['<s:message code="Code"/>', '<s:message code="Name"/>', '<s:message code="Manager"/>', '<s:message code="Note"/>', '<s:message code="Status"/>'],
            colWidths: [60, 120, 200, 200, 150],
            manualColumnResize: true,
            minSpareRows: 1
        });

        // Load current data of request types
        $.ajax({
            url: "load-department",
            dataType: 'json',
            type: 'GET',
            success: function (res) {
              var handsontable = container.data('handsontable');
              handsontable.loadData(res.data);
              if (res.isSample) {
                  $('#labelDepartment').html('<s:message code="Sample_data"/>');
              } else {
                  $('#labelDepartment').html('<s:message code="Current_data"/>');
              }
            },
            error: function() {
                alert('<s:message code="Could_not_load_data"/>: <s:message code="Department"/>');
            }
          });
        // Save
        $("#saveDepartment").click(function() {
            var tableData = container.handsontable('getData');

            var formDataJson = JSON.stringify({"data":tableData});

            $.ajax({
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                url: "saveSystemDepartment",
                data: formDataJson,
                success: function(res) {
                    location.reload(true);
                },
                error: function(data, status) {
                    alert(status);
                    alert(JSON.stringify(data));
                }
            });
        });
    });
</script>

<!-- User -->
<script>
    $(document).ready(function() {

        var container = $("#dataTableUser");
        var parent = container.parent();
        container.handsontable({
            startRows: 1,
            startCols: 5,
            rowHeaders: true,
            colHeaders: ['<s:message code="Account"/>', '<s:message code="FirstName"/>', '<s:message code="LastName"/>', '<s:message code="Email"/>', '<s:message code="Status"/>'],
            colWidths: [100, 120, 200, 200, 150],
            manualColumnResize: true,
            minSpareRows: 1
        });

        // Load current data of request types
        $.ajax({
            url: "load-system-user",
            dataType: 'json',
            type: 'GET',
            success: function (res) {
              var handsontable = container.data('handsontable');
              handsontable.loadData(res.data);
              if (res.isSample) {
                  $('#labelUser').html('<s:message code="Sample_data"/>');
              } else {
                  $('#labelUser').html('<s:message code="Current_data"/>');
              }
            },
            error: function() {
                alert('<s:message code="Could_not_load_data"/>: <s:message code="User"/>');
            }
        });
    });
</script>

<!-- Statuses of request -->
<script>
    $(document).ready(function() {

        var container = $("#dataTableStatusFlowRequest");
        var parent = container.parent();
        container.handsontable({
            startRows: 1,
            startCols: 5,
            rowHeaders: true,
            colHeaders: ['<s:message code="Request_type"/>', '<s:message code="User_type"/>', '<s:message code="Current_status"/>', '<s:message code="Next_status"/>', '<s:message code="Status"/>'],
            colWidths: [100, 120, 200, 200, 150],
            manualColumnResize: true,
            minSpareRows: 1
        });

        // Load current data of request types
        $.ajax({
            url: "load-status-flow-request",
            dataType: 'json',
            type: 'GET',
            success: function (res) {
              var handsontable = container.data('handsontable');
              handsontable.loadData(res.data);
              if (res.isSample) {
                  $('#labelStatusFlowRequest').html('<s:message code="Sample_data"/>');
              } else {
                  $('#labelStatusFlowRequest').html('<s:message code="Current_data"/>');
              }
            },
            error: function() {
                alert('<s:message code="Could_not_load_data"/>: <s:message code="Status_flow_request"/>');
            }
          });
    });
</script>

<!-- Parameters -->
<script>
    $(document).ready(function() {

        var container = $("#dataTableParameter");
        var parent = container.parent();
        container.handsontable({
            startRows: 1,
            startCols: 5,
            rowHeaders: true,
            colHeaders: ['<s:message code="Code"/>', '<s:message code="Name"/>', '<s:message code="Value"/>', '<s:message code="Description"/>', '<s:message code="Status"/>'],
            colWidths: [60, 120, 200, 200, 150],
            manualColumnResize: true,
            minSpareRows: 1
        });

        // Load current data of request types
        $.ajax({
            url: "load-parameter",
            dataType: 'json',
            type: 'GET',
            success: function (res) {
              var handsontable = container.data('handsontable');
              handsontable.loadData(res.data);
              if (res.isSample) {
                  $('#labelParameter').html('<s:message code="Sample_data"/>');
              } else {
                  $('#labelParameter').html('<s:message code="Current_data"/>');
              }
            },
            error: function() {
                alert('<s:message code="Could_not_load_data"/>: <s:message code="Parameter"/>');
            }
          });
    });
</script>