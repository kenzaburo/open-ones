<%--
<script type="text/javascript" src="menu/jquerycssmenu.js"></script>
<link rel="stylesheet" type="text/css" href="menu/jquerycssmenu.css" />
 --%>
<script type="text/javascript">
  //Submit function

  function MenuOnSubmit(url) {
    var strRole = window.document.forms[0].Role.value;
    var form = window.document.forms[0];

    clearInvalidControls(form);

    // Logout
    if (url == 'Logout') {
        LogoutClientHandler('TimesheetHomepage');
    }
    // ChangePassword
    else if (url == 'ChangePassword') {
        urlChangePasswordClientHandler(url);
    }
    //Addnew
    else if (url == 'AddTimesheet') {
        if ( strRole.substring(0, 1) == '1' || strRole.substring(6, 7) == '1'
             || strRole.substring(7, 8) == '1' || strRole.substring(8, 9) == '1') {
            urlAddnewClientHandler(url);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Developer');
        }
    }
    else if (url == 'ListTimesheet') {
        if ( strRole.substring(0, 1) == '1' || strRole.substring(6, 7) == '1'
             || strRole.substring(7, 8) == '1' || strRole.substring(8, 9) == '1') {
            urlListingClientHandler(url);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Developer');
        }
    }
    //Import
    else if (url == 'ImportTimesheet') {
        if (strRole.substring(4, 5) == '1') {
            urlImportClientHandler(url);
        }
        else {
            alert('Sorry, unauthorized access. You are not a QA');
        }
    }
    else if (url == 'Summary') {
        if ( strRole.substring(6, 7) == '1' || strRole.substring(7, 8) == '1' ) {
            alert('Sorry, unauthorized access. You are not a Developer');
        }
        else {
            urlSummaryClientHandler("SummaryReport");
        }
    }
    else if (url == 'Inquiry') {
        if ( strRole.substring(6, 7) == '1' || strRole.substring(7, 8) == '1' ) {
            alert('Sorry, unauthorized access. You are not a Developer');
        }
        else {
            urlInquiryClientHandler("InquiryReport");
        }
    }
    //HanhTN added -- 08/08/2006
    else if (url == 'Tracking') {
        if ( strRole.substring(6, 7) == '1' || strRole.substring(7, 8) == '1' ) {
            alert('Sorry, unauthorized access. You are not a Developer');
        }
        else {
            urlTrackingClientHandler("LackTSGroup");
        }
    }
    else if (url == 'HelpActivityCode') {
        HelpActivityCodeClientHandler('urlHelpActivityCode');
    }

    //LD Approve
    else if (url == 'ListPL') {
        if (strRole.substring(1, 2) == '1' || strRole.substring(2, 3) == '1') {
            urlLDApproveClientHandler(url);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Leader');
        }
    }
    //Other Approve
    else if (url == 'ListGL') {
        if (strRole.substring(3, 4) == '1' || strRole.substring(2, 3) == '1') {
            urlOtherApproveClientHandler(url);
        }
        else if (strRole.substring(1, 2) == '1' || strRole.substring(2, 3) == '1') {
            urlOtherApproveClientHandler(url);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Leader');
        }
    }
    //QA Approve
    else if (url == 'ListQA') {
        if (strRole.substring(4, 5) == '1') {
            urlQAApproveClientHandler(url);
        }
        else {
            alert('Sorry, unauthorized access. You are not a QA');
        }
    }
  }
  
  //Clear invalid date values if this form contains them
  function clearInvalidControls(form) {
//       var control;
//       for (var count = 0x00; count < form.length; count++) {
//           control = form.item(count);
//           if (control.name.indexOf("FromDate", 0) >= 0 ||
//                   control.name.indexOf("ToDate", 0) >= 0) {
              
//               if (control.value.length > 0) {
//                   if (!isDate(control.value)) {
//                       form.item(count).value = "";
//                   }
//               }
//           }
//       }
  }


//*****************************************************************************
// Menu Client Handlers....

function LogoutClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = eventSourceName;    //approval action
    fireMenuServerEvent(eventSourceName);
}

function urlChangePasswordClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//
function urlListingClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function urlAddnewClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function urlImportClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//
function urlInquiryClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "RA";       //report action
    fireMenuServerEvent(eventSourceName);
}

function urlSummaryClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "RA";
    fireMenuServerEvent(eventSourceName);
}

function urlWeeklyClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "RA";
    fireMenuServerEvent(eventSourceName);
}

//HanhTN added -- 08/08/2006
function urlTrackingClientHandler(eventSourceName) {
//  alert("Hello -- urlTrackingClientHandler");
    document.forms[0].hidAction.value = "RA";
    fireMenuServerEvent(eventSourceName);
}

function HelpActivityCodeClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//////////////////////////////////
function urlLDApproveClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function urlQAApproveClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function urlOtherApproveClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//////////////////////////////

function fireMenuServerEvent(eventSourceName) {
    document.forms[0].hidActionDetail.value = eventSourceName;
    document.forms[0].action = "TimesheetServlet";
    document.forms[0].submit();
}
</script>

<!--[if lte IE 7]>
<style type="text/css">
html .jquerycssmenu{height: 1%;} /*Holly Hack for IE7 and below*/
</style>
<![endif]-->
<%--
<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="menu/jquerycssmenu.js"></script>
 --%>
<body>
<div id="myjquerymenu" class="jquerycssmenu">
<ul>
<li><a href="javascript:MenuOnSubmit('AddTimesheet')">Timesheet</a>
  <ul>
    <li><a href="#" onclick="javascript:MenuOnSubmit('AddTimesheet')">Add New</a></li>
    <li><a href="#" onclick="javascript:MenuOnSubmit('ListTimesheet')">List</a></li>
    <li><a href="#" onclick="javascript:MenuOnSubmit('ImportTimesheet')">Import</a></li>
  </ul>
</li>
<li><a href="javascript:MenuOnSubmit('ListPL')">Approve</a>
  <ul>
    <li><a href="#" onclick="javascript:MenuOnSubmit('ListPL')">Approved for Internal/External Projects</a></li>
    <li><a href="#" onclick="javascript:MenuOnSubmit('ListGL')">Approved for Public Projects</a></li>
    <li><a href="#" onclick="javascript:MenuOnSubmit('ListQA')">Approve By Quality Assurance</a></li>
  </ul>
</li>
<li><a href="javascript:MenuOnSubmit('Summary')">Report</a>
  <ul>
  <li><a href="#" onclick="javascript:MenuOnSubmit('Summary')">Summary</a></li>
  <li><a href="#" onclick="javascript:MenuOnSubmit('Inquiry')">Inquiry</a></li>
  <li><a href="#" onclick="javascript:MenuOnSubmit('Tracking')">Tracking</a></li>
  </ul>
</li>
<li><a href="#" onclick="javascript:MenuOnSubmit('ChangePassword')">Change Password</a></li>
<li><a href="javascript:MenuOnSubmit('Logout')">Logout</a>
</li>
<li><a href="Guideline_Timesheet.htm" target="_blank">Help</a></li>
</ul>
<br style="clear: left" />
</div>
</body>