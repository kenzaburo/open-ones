/**
 * Setting for date-picker
 */
$(function() {
	$.datepicker.setDefaults($.datepicker.regional['vi']);
});


function viewRequestContent(viewUrl, title) {
    if (window.showModelessDialog) {        // Internet Explorer
        showModelessDialog (viewUrl, window, "dialogWidth:980px; dialogHeight:620px");
    } 
    else {
        window.open (viewUrl, title,"width=980, height=620, alwaysRaised=yes");
    }
}