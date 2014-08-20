/**
 * Change the detailed screen depending on select combo box request type.
 * @param formName
 */
//function displayDetailedRequest(formName) {
//	var frm = document.forms[formName];
//	var optionReqType = frm.elements["reqType"];
//	var index = optionReqType.selectedIndex;
//	var selectedReqType = optionReqType.options[index].value;
//	
//	// Hide all screen section of detailed request
//	$("#make-announcement").hide();
//	$("#make-rule").hide();
//	$("#make-task").hide();
//	$("#make-leave").hide();
//	showHideDetailedRequest(selectedReqType);
//}

function showHideDetailedRequest(selectedReqType) {
	//alert("selectedReqType" + selectedReqType);
	if (selectedReqType == 'Announcement') {
		$("#make-announcement").show();
		$("#make-rule").hide();
		$("#make-task").hide();
		$("#make-leave").hide();
	}

	if (selectedReqType == 'Rule') {
		$("#make-announcement").hide();
		$("#make-rule").show();
		$("#make-task").hide();
		$("#make-leave").hide();
	}

	if (selectedReqType == 'Task') {
		$("#make-announcement").hide();
		$("#make-rule").hide();
		$("#make-task").show();
		$("#make-leave").hide();
	}
	
	if (selectedReqType == 'Leave') {
		$("#make-announcement").hide();
		$("#make-rule").hide();
		$("#make-task").hide();
		$("#make-leave").show();
	}
}
/**
 * Process when init the page
 */
$(function() {
	// Using id contains dot character, the jquery date picker does not work. 
    
	// For Task request
	$("#request_startdate").datepicker({dateFormat: 'dd-mm-yy'});
    $("#request_enddate").datepicker({dateFormat: 'dd-mm-yy'});
    
    // For Leave request
    $("#request_startdate_leave").datepicker({dateFormat: 'dd-mm-yy'});
    $("#request_enddate_leave").datepicker({dateFormat: 'dd-mm-yy'});
    
	///////////////////////////////////////////////////////////
	// Processing when init the screen "Create Request"
	// Hide all screen section of detailed request
	$("#make-announcement").hide();
	$("#make-rule").hide();
	$("#make-task").hide();
	$("#make-leave").hide();
	
	var selectedReqType = $("#reqType").val();
	// alert("init - selectedReqType" + selectedReqType);
	showHideDetailedRequest(selectedReqType);
	
    ///////////////////////////////////////////////////////////
	// Processing when the combo box "Request Type" is changed
	$("#reqType").on('change', function() {
		var selectedReqType = $("#reqType").val();
		showHideDetailedRequest(selectedReqType);
	});
});