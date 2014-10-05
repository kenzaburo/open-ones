
  function viewRequestContent(viewUrl) {
    // alert("Open windows url=" + viewUrl);
    if (window.showModelessDialog) {        // Internet Explorer
        showModelessDialog (viewUrl, window, "dialogWidth:980px; dialogHeight:620px");
    } 
    else {
        window.open (viewUrl, "Nội dung thông báo","width=980, height=620, alwaysRaised=yes");
    }
  }

function confirmRejectFunction(){
 	var confrim = confirm("Bạn có muốn từ chối yêu cầu này?");
 	if (confrim) {
 		return true;
 	} else {
 		return false;
 	}
}