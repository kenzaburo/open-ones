/**
 * Change the detailed screen depending on select request type.
 * @param formName
 */
function displayDetailedRequest(formName) {
	var frm = document.forms[formName];
	var optionReqType = frm.elements["reqType"];
	var index = optionReqType.selectedIndex;
	var selectedReqType = optionReqType.options[index].value;
	
	
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
$(function() {
	var reqType = $("#reqType").val();
	$("#make-task").show();
	$("#make-announcement").hide();
	$("#make-rule").hide();
	$("#make-leave").hide();
});