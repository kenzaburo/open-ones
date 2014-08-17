$(function() {
	$("#createLeave").submit(function () {
		if ($("input[name='leaveTitle']").val() == null || $("input[name='leaveTitle']").val() == '') {
			alert("Tiêu đề không được để trống");
			return false;
		}
		if ($("#leaveContent").val() == null || $("#leaveContent").val() == '') {
			alert("Lý do không được để trống");
			return false;
		}
		if ($("#leaveReceiveUser option:selected" ).val() == null || $("#leaveReceiveUser option:selected" ).val() == '') {
			alert("Chọn người nhận");
			return false;
		}
		if ($("input[name='leaveStartDay']").val() == null || $("input[name='leaveStartDay']").val() == '') {
			alert("Ngày bắt đầu không được để trống");
			return false;
		}
		if ($("input[name='leaveEndDay']").val() == null || $("input[name='leaveEndDay']").val() == '') {
			alert("Ngày kết thúc không được để trống");
			return false;
		}
	});
	$("#reason").submit(function () {
		var confrim = confirm("Bạn có muốn từ chối yêu cầu này?");
	 	if (confrim) {
	 		return true;
	 	} else {
	 		$("#approve").show("slow");
	 		return false;
	 	}
	});
	
});
