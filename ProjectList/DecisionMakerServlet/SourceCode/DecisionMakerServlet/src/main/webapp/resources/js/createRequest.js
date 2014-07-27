$(document).ready ( function() {
	$("#make-announcement").hide();
	$("#make-rule").hide();
	$("#make-task").show();
	$("#make-leave").hide();
	
	
	$("#reqType").on('change', function() {
		if ($("#reqType").val() == 'Announcement') {
			$("#make-announcement").show();
			$("#make-rule").hide();
			$("#make-task").hide();
			$("#make-leave").hide();
		}

		if ($("#reqType").val() == 'Rule') {
			$("#make-announcement").hide();
			$("#make-rule").show();
			$("#make-task").hide();
			$("#make-leave").hide();
		}

		if ($("#reqType").val() == 'Task') {
			$("#make-announcement").hide();
			$("#make-rule").hide();
			$("#make-task").show();
			$("#make-leave").hide();
		}
		
		if ($("#reqType").val() == 'Leave') {
			$("#make-announcement").hide();
			$("#make-rule").hide();
			$("#make-task").hide();
			$("#make-leave").show();
		}
	});
});