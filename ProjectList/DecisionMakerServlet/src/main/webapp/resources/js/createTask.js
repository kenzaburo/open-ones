$(document).ready ( function(){
	$("#make-report").hide();
	$("#make-task").hide();
	$("#select1").on('change', function() {
		if ($("#select1").val() == 1) {
			$("#make-report").show();
			$("#make-task").hide();
		}
		if ($("#select1").val() == 3) {
			$("#make-task").show();
			$("#make-report").hide();
		}
	});
});