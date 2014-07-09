$(document).ready ( function() {
	$("#make-announcement").hide();
	$("#make-rule").hide();
	$("#make-task").show();
	$("#make-leave").hide();
	
	
	$("#reqType").on('change', function() {
		if ($("#reqType").val() == 1) {
			$("#make-announcement").show();
			$("#make-rule").hide();
			$("#make-task").hide();
			$("#make-leave").hide();
		}

		if ($("#reqType").val() == 2) {
			$("#make-announcement").hide();
			$("#make-rule").show();
			$("#make-task").hide();
			$("#make-leave").hide();
		}

		if ($("#reqType").val() == 3) {
			$("#make-announcement").hide();
			$("#make-rule").hide();
			$("#make-task").show();
			$("#make-leave").hide();
		}
		
		if ($("#reqType").val() == 4) {
			$("#make-announcement").hide();
			$("#make-rule").hide();
			$("#make-task").hide();
			$("#make-leave").show();
		}
	});
});