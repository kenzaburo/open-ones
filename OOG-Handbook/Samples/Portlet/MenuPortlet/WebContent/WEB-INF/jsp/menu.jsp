<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<portlet:defineObjects />
<c:set var="portletNamespace" scope="request"><portlet:namespace/></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="ctl00_Head1">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link href="/MenuPortlet//MenuPortlet/resource_files/Advanced.css" type="text/css" rel="stylesheet"><link href="/MenuPortlet/resource_files/Ajax.css" type="text/css" rel="stylesheet">
<link href="/MenuPortlet/resource_files/AjaxJQuery.css" type="text/css" rel="stylesheet">
<link href="/MenuPortlet/resource_files/GridView.css" type="text/css" rel="stylesheet"><link href="/MenuPortlet/resource_files/menustyles.css" type="text/css" rel="stylesheet">
<title>
	Demo Menu - version 0.2.0
</title>
<link rel="shortcut icon" href="logo.ico" type="image/ico">
<link rel="stylesheet" type="text/css" href="/MenuPortlet/resource_files/jquery.css" media="screen">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <script language="javascript" type="text/javascript">
        var sys_over_load = '<p style="width: 450px;">Hệ thống đang bị quá tải do quá nhiều người dùng đồng thời. Sử dụng hệ thống trong lúc này có thể bị chậm hoặc kết quả không mong muốn.'
                            + ' Quý thầy cô vui lòng nhấn "Tiếp tục" để sử dụng chương trình hoặc nhấn "Thoát" để quay lại sau.</p>'
                            + '<p style="text-align: center;"><button id="smasContinue" style="margin-right: 10px;">Tiếp tục</button><button id="smasExit">Thoát</button></p>';
        var tmp_pending = false;
        function checkSystem() {
            setTimeout(function () {

                $.fancybox(sys_over_load, {
                    'keys': null,
                    'closeBtn': false,
                    'afterShow': function () {
                        $('#smasContinue').click(function () {
                            window.location = buildUrl('cnts', 'true');
                        });
                        $('#smasExit').click(function () {
                            window.location = buildUrl('warning_out', 'true');
                        });
                    }
                });
            }
            , 200);
        }		
        function buildUrl(key, value) {
            key = escape(key); value = escape(value);
            var s = document.location.search;
            var kvp = key + "=" + value;
            if (s.indexOf(kvp) == -1) {
                if (s.indexOf("?") == -1) {
                    s += "?" + kvp;
                } else {
                    s += "&" + kvp;
                }
            }
            return s;
        }
        function checkTextBox() {
            var elems = document.getElementsByTagName('input');
            for (var i = 0; i < elems.length; i++) {
                var obj = elems[i];
                if (obj.type == 'text') {
                    var regrex = new RegExp(/<\/?[a-z][a-z0-9]*[^<>]*>/ig);
                    if (regrex.test(obj.value)) {
                        obj.value = obj.value.replace(/<\/?[a-z][a-z0-9]*[^<>]*>/ig, "");
                    }
                }
            }
            return true;
        }
    </script>
<link href="/MenuPortlet/resource_files/Advanced.css" type="text/css" rel="stylesheet"><link href="/MenuPortlet/resource_files/Ajax.css" type="text/css" rel="stylesheet"><link href="/MenuPortlet/resource_files/AjaxJQuery.css" type="text/css" rel="stylesheet"><link href="/MenuPortlet/resource_files/GridView.css" type="text/css" rel="stylesheet"><link href="/MenuPortlet/resource_files/menustyles.css" type="text/css" rel="stylesheet"><style type="text/css">
	.ctl00_MainMenu_0 { background-color:white;visibility:hidden;display:none;position:absolute;left:0px;top:0px; }
	.ctl00_MainMenu_1 { text-decoration:none; }
	.ctl00_MainMenu_2 {  }
	.ctl00_MainMenu_3 { border-style:none; }
	.ctl00_MainMenu_4 {  }
	.ctl00_MainMenu_5 {  }
	.ctl00_MainMenu_6 { border-style:none; }
	.ctl00_MainMenu_7 { padding:2px 0px 2px 0px; }
	.ctl00_MainMenu_8 {  }
	.ctl00_MainMenu_9 { border-style:none; }
	.ctl00_MainMenu_10 {  }
	.ctl00_MainMenu_11 { border-style:none; }
	.ctl00_MainMenu_12 {  }

</style></head>
<body style="padding-bottom: 29px;">
    <form name="aspnetForm" method="post" action="/Pages/List/TypeOfWork.aspx" onsubmit="javascript:return WebForm_OnSubmit();" id="aspnetForm" onkeyup="checkTextBox();">
<div>
<input name="ctl00_ToolkitScriptManager1_HiddenField" id="ctl00_ToolkitScriptManager1_HiddenField" value=";;AjaxControlToolkit, Version=1.0.11119.41102, Culture=neutral, PublicKeyToken=28f01b0e84b6d53e:vi-VN:b6c6a29e-a678-4f82-8215-1947249c9eb0:e2e86ef9:1df13a87:3858419b:9ea3f0e2:96741c43:c4c00916:c7c04611:cd120801:38ec41c0" type="hidden">
<input name="__EVENTTARGET" id="__EVENTTARGET" value="" type="hidden">
<input name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="" type="hidden">
<input name="__LASTFOCUS" id="__LASTFOCUS" value="" type="hidden">
<input name="__VIEWSTATE" id="__VIEWSTATE" value="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" type="hidden">
</div>

<script type="text/javascript">
//<![CDATA[
var theForm = document.forms['aspnetForm'];
if (!theForm) {
    theForm = document.aspnetForm;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
//]]>
</script>


<script src="/MenuPortlet/resource_files/WebResource.js" type="text/javascript"></script>


<script src="/MenuPortlet/resource_files/ScriptResource_004.js" type="text/javascript"></script>
<script src="/MenuPortlet/resource_files/ScriptResource_002.js" type="text/javascript"></script>
<script type="text/javascript">
//<![CDATA[
var __cultureInfo = {"name":"vi-VN","numberFormat":{"CurrencyDecimalDigits":2,"CurrencyDecimalSeparator":",","IsReadOnly":true,"CurrencyGroupSizes":[3],"NumberGroupSizes":[3],"PercentGroupSizes":[3],"CurrencyGroupSeparator":".","CurrencySymbol":"₫","NaNSymbol":"NaN","CurrencyNegativePattern":8,"NumberNegativePattern":1,"PercentPositivePattern":0,"PercentNegativePattern":0,"NegativeInfinitySymbol":"-Infinity","NegativeSign":"-","NumberDecimalDigits":2,"NumberDecimalSeparator":",","NumberGroupSeparator":".","CurrencyPositivePattern":3,"PositiveInfinitySymbol":"Infinity","PositiveSign":"+","PercentDecimalDigits":2,"PercentDecimalSeparator":",","PercentGroupSeparator":".","PercentSymbol":"%","PerMilleSymbol":"‰","NativeDigits":["0","1","2","3","4","5","6","7","8","9"],"DigitSubstitution":1},"dateTimeFormat":{"AMDesignator":"SA","Calendar":{"MinSupportedDateTime":"\/Date(-62135596800000)\/","MaxSupportedDateTime":"\/Date(253402275599999)\/","AlgorithmType":1,"CalendarType":1,"Eras":[1],"TwoDigitYearMax":2029,"IsReadOnly":true},"DateSeparator":"/","FirstDayOfWeek":1,"CalendarWeekRule":0,"FullDateTimePattern":"dd MMMM yyyy h:mm:ss tt","LongDatePattern":"dd MMMM yyyy","LongTimePattern":"h:mm:ss tt","MonthDayPattern":"dd MMMM","PMDesignator":"CH","RFC1123Pattern":"ddd, dd MMM yyyy HH\u0027:\u0027mm\u0027:\u0027ss \u0027GMT\u0027","ShortDatePattern":"dd/MM/yyyy","ShortTimePattern":"h:mm tt","SortableDateTimePattern":"yyyy\u0027-\u0027MM\u0027-\u0027dd\u0027T\u0027HH\u0027:\u0027mm\u0027:\u0027ss","TimeSeparator":":","UniversalSortableDateTimePattern":"yyyy\u0027-\u0027MM\u0027-\u0027dd HH\u0027:\u0027mm\u0027:\u0027ss\u0027Z\u0027","YearMonthPattern":"MMMM yyyy","AbbreviatedDayNames":["CN","Hai","Ba","Tư","Năm","Sáu","Bảy"],"ShortestDayNames":["C","H","B","T","N","S","B"],"DayNames":["Chủ Nhật","Thứ Hai","Thứ Ba","Thứ Tư","Thứ Năm","Thứ Sáu","Thứ Bảy"],"AbbreviatedMonthNames":["Thg1","Thg2","Thg3","Thg4","Thg5","Thg6","Thg7","Thg8","Thg9","Thg10","Thg11","Thg12",""],"MonthNames":["Tháng Giêng","Tháng Hai","Tháng Ba","Tháng Tư","Tháng Năm","Tháng Sáu","Tháng Bảy","Tháng Tám","Tháng Chín","Tháng Mười","Tháng Mười Một","Tháng Mười Hai",""],"IsReadOnly":true,"NativeCalendarName":"Dương Lịch","AbbreviatedMonthGenitiveNames":["Thg1","Thg2","Thg3","Thg4","Thg5","Thg6","Thg7","Thg8","Thg9","Thg10","Thg11","Thg12",""],"MonthGenitiveNames":["Tháng Giêng","Tháng Hai","Tháng Ba","Tháng Tư","Tháng Năm","Tháng Sáu","Tháng Bảy","Tháng Tám","Tháng Chín","Tháng Mười","Tháng Mười Một","Tháng Mười Hai",""]},"eras":[1,"A.D.",null,0]};//]]>
</script>

<script src="/MenuPortlet/resource_files/ScriptResource_003.js" type="text/javascript"></script>
<script type="text/javascript">
//<![CDATA[
if (typeof(Sys) === 'undefined') throw new Error('ASP.NET Ajax client-side framework failed to load.');
//]]>
</script>

<script src="/MenuPortlet/resource_files/ScriptResource.js" type="text/javascript"></script>
<script src="/MenuPortlet/resource_files/TypeOfWork.js" type="text/javascript"></script>
<script type="text/javascript">
//<![CDATA[
function WebForm_OnSubmit() {
if (typeof(ValidatorOnSubmit) == "function" && ValidatorOnSubmit() == false) return false;
return true;
}
//]]>
</script>

<div>

	<input name="__SCROLLPOSITIONX" id="__SCROLLPOSITIONX" value="0" type="hidden">
	<input name="__SCROLLPOSITIONY" id="__SCROLLPOSITIONY" value="0" type="hidden">
	<input name="__VIEWSTATEENCRYPTED" id="__VIEWSTATEENCRYPTED" value="" type="hidden">
</div>
    <script type="text/javascript">
//<![CDATA[
Sys.WebForms.PageRequestManager._initialize('ctl00$ToolkitScriptManager1', 'aspnetForm', ['tctl00$ContentPlaceHolder1$UITypeWork1$uplResult','','tctl00$ContentPlaceHolder1$UITypeWork1$uplPopUp',''], ['ctl00$ContentPlaceHolder1$UITypeWork1$btnSearch',''], [], 0, 'ctl00');
//]]>
</script>


    
    <script language="javascript" type="text/javascript" src="/MenuPortlet/resource_files/jquery.js"></script>
    
    <script language="javascript" src="/MenuPortlet/resource_files/dropdown_menu_hack.js" type="text/javascript"></script>
    <script type="text/javascript">
        //02/02/2012 hungnd - Fix hack

        function DisableSpecialKey(event) {
            // IE
            if (window.event) {
                var charCode = window.event.keyCode;
                //Dau < va dau >
                if (charCode == 60) {
                    window.event.returnValue = false;
                }
            }
            // Safari 4, Firefox 3.0.4
            else {
                if (event.which == 60 || event.which == 62) {
                    CancelKey(event);
                }
            }

        }
        // 02/02/2012 hungnd
        function CancelKey(e) {
            e.stopPropagation();
            e.preventDefault();
        }
    </script>
<script type="text/javascript" src='/MenuPortlet/scripts/common.js'></script>

    <div id="SMASMainPage">
        <div class="GridFull">
            
            <div class="Banner">
            </div>
            <div class="MenuNav">
                <div class="GridFix">
                    <div class="bgMenu">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tbody><tr>
                                <td align="left" valign="middle">
                                    <div class="navigationArea">
                                        <table id="ctl00_MainMenu" class="dmRootmenu ctl00_MainMenu_5 ctl00_MainMenu_2" border="0" cellpadding="0" cellspacing="0">
	<tbody><tr>
		<td onmouseover="Menu_HoverStatic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun0"><table class="dmRootItem ctl00_MainMenu_4" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody><tr>
				<td style="white-space: nowrap;"><a class="ctl00_MainMenu_1 dmRootItem ctl00_MainMenu_3" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/home.png" alt="" style="border-style: none; vertical-align: middle;">&nbsp; Trang chủ</a></td>
			</tr>
		</tbody></table></td><td style="width: 3px;"></td><td onmouseover="Menu_HoverStatic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun1"><table class="dmRootItem ctl00_MainMenu_4" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody><tr>
				<td style="white-space: nowrap;"><a class="ctl00_MainMenu_1 dmRootItem ctl00_MainMenu_3" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/admin.png" alt="" style="border-style: none; vertical-align: middle;">&nbsp;Hệ thống</a></td>
			</tr>
		</tbody></table></td><td style="width: 3px;"></td><td onmouseover="Menu_HoverStatic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun2"><table class="dmRootItem ctl00_MainMenu_4" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody><tr>
				<td style="white-space: nowrap;"><a class="ctl00_MainMenu_1 dmRootItem ctl00_MainMenu_3" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/pupil.png" alt="" style="border-style: none; vertical-align: middle;">&nbsp;Quản lý học sinh</a></td>
			</tr>
		</tbody></table></td><td style="width: 3px;"></td><td onmouseover="Menu_HoverStatic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun3"><table class="dmRootItem ctl00_MainMenu_4" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody><tr>
				<td style="white-space: nowrap;"><a class="ctl00_MainMenu_1 dmRootItem ctl00_MainMenu_3" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/teacher.png" alt="" style="border-style: none; vertical-align: middle;">&nbsp;Quản lý cán bộ</a></td>
			</tr>
		</tbody></table></td><td style="width: 3px;"></td><td onmouseover="Menu_HoverStatic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun4"><table class="dmRootItem ctl00_MainMenu_4" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody><tr>
				<td style="white-space: nowrap;"><a class="ctl00_MainMenu_1 dmRootItem ctl00_MainMenu_3" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/until.png" alt="" style="border-style: none; vertical-align: middle;">&nbsp;Tiện ích</a></td>
			</tr>
		</tbody></table></td><td style="width: 3px;"></td><td onmouseover="Menu_HoverStatic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun5"><table class="dmRootItem ctl00_MainMenu_4" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody><tr>
				<td style="white-space: nowrap;"><a class="ctl00_MainMenu_1 dmRootItem ctl00_MainMenu_3" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/list.png" alt="" style="border-style: none; vertical-align: middle;">&nbsp;Danh mục</a></td>
			</tr>
		</tbody></table></td>
	</tr>
</tbody></table><div id="ctl00_MainMenun1Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun6">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href='<portlet:renderURL><portlet:param name="action" value="initPersonalInfo"/></portlet:renderURL>' style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Thông tin cá nhân</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun7">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Khởi tạo dữ liệu đầu năm</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Khởi tạo dữ liệu đầu năm" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun1ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun1ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun7Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun8">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Cập nhật thời khóa biểu</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun7ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun7ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun2Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun9">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Quản lý học tập</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Quản lý học tập" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun10">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Quản lý rèn luyện</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Quản lý rèn luyện" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun11">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Thống kê báo cáo</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Thống kê báo cáo" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun2ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun2ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun9Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun12">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Sổ điểm môn tính điểm.</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun13">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Sổ điểm môn nhận xét.</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun14">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Xếp loại hạnh kiểm</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun15">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Xếp loại học sinh</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun16">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Xếp loại tập thể lớp</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun17">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Đăng kí môn thi lại</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun18">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Cập nhật điểm thi lại</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun9ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun9ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun10Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun19">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Điểm danh</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun20">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Khen thưởng - Kỷ luật</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun10ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun10ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun11Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun21">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;In học bạ học sinh theo mẫu</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun22">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Bảng điểm của lớp</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun23">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Thống kê kết quả học tập</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun24">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Thống kê tình hình lưu chuyển học sinh</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun11ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun11ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun3Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun25">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Quản lý khen thưởng, kỷ luật</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Quản lý khen thưởng, kỷ luật" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun3ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun3ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun25Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun26">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Đánh giá xếp loại giáo viên</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun27">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Danh hiệu thi đua cán bộ</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun28">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Danh hiệu thi đua tập thể</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun25ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun25ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun4Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun29">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Quản lý thông báo</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun30">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Gửi tin nhắn SMS</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Gửi tin nhắn SMS" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun4ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun4ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun30Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun31">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Lịch sử gửi  tin nhắn SMS</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun32">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Tin nhắn cho Giáo viên</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Tin nhắn cho Giáo viên" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun33">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em; cursor: text;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Tin nhắn cho phụ huynh</a></td><td style="width: 0px;"><img src="/MenuPortlet/resource_files/arrow1.gif" alt="Expand &amp;nbsp;&amp;nbsp;Tin nhắn cho phụ huynh" style="border-style: none; vertical-align: middle;"></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun30ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun30ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun32Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun34">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Thông báo đột xuất đến giáo viên</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun32ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun32ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun33Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun35">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Thông báo kết quả học tập</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun36">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Thông báo đột xuất đến học sinh, phụ huynh</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun33ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun33ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div><div id="ctl00_MainMenun5Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
	<table class="vingdlumenutable" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun37">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Loại công việc</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun38">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Hình thức xử lý VPQC thi</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun39">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Sáng kiến kinh nghiệm</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun40">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Công việc kiêm nhiệm</a></td>
				</tr>
			</tbody></table></td>
		</tr><tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun41">
			<td><table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td style="white-space: nowrap; width: 100%;"><a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href="#" style="border-style: none; font-size: 1em;"><img src="/MenuPortlet/resource_files/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;Hình thức khen thưởng - kỷ luật</a></td>
				</tr>
			</tbody></table></td>
		</tr>
	</tbody></table><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun5ItemsUp" onmouseover="PopOut_Up(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource.gif" alt="Scroll up">
	</div><div class="dmItem1 ctl00_MainMenu_7 ctl00_MainMenu_0" id="ctl00_MainMenun5ItemsDn" onmouseover="PopOut_Down(this)" onmouseout="PopOut_Stop(this)" style="text-align:center;">
		<img src="/MenuPortlet/resource_files/WebResource_002.gif" alt="Scroll down">
	</div>
</div>
                                    </div>
                                </td>
                                <td style="padding-right: 5px;" align="right" valign="middle">
                                    
                                    &nbsp;

                                </td>
                            </tr>
                        </tbody></table>
                    </div>
                </div>
            </div>
        </div>

        <div class="GridFull">
            
            <div class="GridFix" onkeypress="DisableSpecialKey(event)">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tbody><tr valign="top">
                        <td class="BoxContent" style="height: 450px;">
                            <input name="ctl00$hdfUserToken" id="ctl00_hdfUserToken" value="fhhvnvsnkok0atmzug5gfbrv" type="hidden">
                            
    
<div id="ctl00_ContentPlaceHolder1_UITypeWork1_uplPopUp">
	
        <div id="ctl00_ContentPlaceHolder1_UITypeWork1_divPopUp" onkeypress="javascript:return WebForm_FireDefaultButton(event, 'ctl00_ContentPlaceHolder1_UITypeWork1_btnSave')" style="display: none; width: 400px; position: fixed; z-index: 100001;">
		
            <div>
                <div class="vtt_boxtitle">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tbody><tr>
                            <td style="width: 80%;" id="tdPopupText">
                                <span id="ctl00_ContentPlaceHolder1_UITypeWork1_lblPopupText" class="boxtitle"></span>
                            </td>
                            <td align="right">
                                <input name="ctl00$ContentPlaceHolder1$UITypeWork1$ibtClose" id="ctl00_ContentPlaceHolder1_UITypeWork1_ibtClose" src="/MenuPortlet/resource_files/stop-error.ico" style="width: 16px; border-width: 0px;" type="image">
                            </td>
                        </tr>
                    </tbody></table>
                </div>
            </div>       
	</div>
        
        <input name="ctl00$ContentPlaceHolder1$UITypeWork1$lbtHide" value="" id="ctl00_ContentPlaceHolder1_UITypeWork1_lbtHide" style="border-width: 0px; width: 80px; display: none;" type="submit">
        
<style type="text/css">
    .modalBackground
    {
        background-color: Gray;
        filter: alpha(opacity=60);
        opacity: 0.60;
        z-index: 999;
    }
    .updateProgress
    {
        background-image: url(../../images/loading_bg.png);
    }
    .updateProgressMessage
    {
        margin: 3px;
        font-family: Arial;
        font-size: 12px;
        color: Red;
        vertical-align: middle;
    }
</style>

<script type="text/javascript" language="javascript">

    //  register for our events
    Sys.WebForms.PageRequestManager.getInstance().add_beginRequest(BeginRequestHandler);
    Sys.WebForms.PageRequestManager.getInstance().add_endRequest(EndRequestHandler);

    function BeginRequestHandler(sender, args) {
        // show the popup
        var pop = ($find('mdlPopup') == null ? $find('ctl00_ContentPlaceHolder1_UITypeWork1_UIProgressPopup1_mdlPopup') : $find('mdlPopup'));
        if (pop != null)
            pop.show();
    }

    function EndRequestHandler(sender, args) {
        //  hide the popup
        var pop = ($find('mdlPopup') == null ? $find('ctl00_ContentPlaceHolder1_UITypeWork1_UIProgressPopup1_mdlPopup') : $find('mdlPopup'));
        if (pop != null)
            pop.hide();
    }
</script>


<div id="ctl00_ContentPlaceHolder1_UITypeWork1_UIProgressPopup1_pnlPopup" style="height: 55px; width: 320px; background-image: url(&quot;../../images/loading_bg.png&quot;); display: none; position: fixed; z-index: 100001;">
		
    <div style="margin-top: 13px;" align="center">
        <table>
            <tbody><tr>
                <td valign="middle">
                    <img id="ctl00_ContentPlaceHolder1_UITypeWork1_UIProgressPopup1_imgPop" src="/MenuPortlet/resource_files/loading5.gif" style="border-width: 0px;">&nbsp;
                </td>
                <td valign="middle">
                    <span id="ctl00_ContentPlaceHolder1_UITypeWork1_UIProgressPopup1_lblText" style="font-weight:bold;">Đang tải dữ liệu ...</span>
                </td>
            </tr>
        </tbody></table>
    </div>

	</div>

    
<div class="modalBackground" style="display: none; position: fixed; left: 0px; top: 0px; z-index: 10000;" id="mpe_backgroundElement"></div><div class="modalBackground" style="display: none; position: fixed; left: 0px; top: 0px; z-index: 10000;" id="mdlPopup_backgroundElement"></div></div>

<script type="text/javascript">
    function HideModalPopup() {
        var modal = $find('mpe');
        modal.hide();
        return false;
    }
    function clearMessage() {

        var lblMessage = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_lblMsg');

        lblMessage.innerHTML = ''

    }
    function validateSave() {
        var lblMessage = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_lblMsg');
        lblMessage.innerHTML = ''
        WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions('ctl00_ContentPlaceHolder1_UITypeWork1_btnSave', '', true, 'popup', '', false, false));
        if (!WebForm_OnSubmit()) {
            return false;
        }

        var txtDescription = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_txtDesciptionPopUp');
        if (Page_ClientValidate()) {

            return textareaCounter(txtDescription, 100, 'Mô tả');
        }

    }
    function resetForm() {
        var lblMessage = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_lblMsg');
        var txtName = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_txtNamePopUp');
        var txtDes = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_txtDesciptionPopUp');
        var lblMsgDelete = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_lblMsgDelete');
        var hdd = document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_hddTypeOfWorkId');
        lblMsgDelete.innerHTML = '';
        lblMessage.innerHTML = '';
        txtName.value = '';
        txtDes.value = '';
        hdd.value = '';
        return false;
    }
</script>



                            <div class="clear">
                            </div>
                        </td>
                    </tr>
                </tbody></table>
            </div>
        </div>


    </div>
    
<div class="QuickMenu">
    <div class="GridFix">
        <div class="QuickBg">
            <div class="QuickIcon">
            </div>
            <div class="QuickNav">
                <ul><li class="active"><a href="#">&nbsp;&nbsp;Loại công việc</a></li><li><a href="#">&nbsp;&nbsp;Hình thức xử lý VPQC thi</a></li><li><a href="#">&nbsp;&nbsp;Sáng kiến kinh nghiệm</a></li><li><a href="#">&nbsp;&nbsp;Công việc kiêm nhiệm</a></li><li><a href="#">&nbsp;&nbsp;Hình thức khen thưởng - kỷ luật</a></li></ul>
                <div class="clear">
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var _quickMenu = {
        init: function () {
            _quickMenu.getMenuInSameLevel();
        },
        getMenuInSameLevel: function () {
            var currURL = location.href;
            var lastIndex = currURL.lastIndexOf("/");
            var lastURL = currURL.substring(lastIndex + 1, lastIndex.length);
            //get a
            var a = $("a[href='" + lastURL + "']", ".navigationArea");
            if (a.size() == 0) {
                $("a[href]", ".navigationArea").each(function () {
                    if ($(this).attr("href").toLowerCase() == lastURL.toLowerCase()) {
                        a = $(this);
                        return;
                    }
                });
            }
            //end
            var parents = $($(a.parents("table")[0]).parents("table")[0]);//Phatlh 02/06/2012
            //a.parents("table:first").parents("table:first");(Vinhhv)
            $(".QuickMenu .QuickNav ul").html("");
            var prefix = "";
            if (parents.attr("id") == undefined) {
                parents.addClass("vingdlumenutable");
                prefix = ".vingdlumenutable";
            }
            else
                prefix = "#" + parents.attr("id");
            $(prefix + ">tbody>tr").each(function () {
                var subA = $("a:first", $(this));
                if (subA) {//neu ton tai
                    var active = "";
                    var thisURL = subA.attr("href");
                    if (thisURL.toLowerCase() != currURL.toLowerCase()) {//!Menu null
                        if (thisURL.toLowerCase() == lastURL.toLowerCase())
                            active = "class='active'";
                        var li = "<li " + active + "><a href='" + thisURL + "'>" + subA.text() + "</a></li>";
                        $(".QuickMenu .QuickNav ul").append(li);
                    }
                }
            });
            if ($(".QuickMenu .QuickNav li").size() == 0) {
                $(".QuickMenu").remove();
            }
            else {
                $("body").css({ "padding-bottom": $(".QuickMenu").outerHeight() });
            }
        }
    }

    $(function () {
        _quickMenu.init();
    });
   
</script>

    
    
<script type="text/javascript">
//<![CDATA[
var Page_Validators =  new Array(document.getElementById("ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired"), document.getElementById("ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1"));
//]]>
</script>

<script type="text/javascript">
//<![CDATA[
var ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired = document.all ? document.all["ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired"] : document.getElementById("ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired");
ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired.controltovalidate = "ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroup";
ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired.focusOnError = "t";
ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired.errormessage = "Bạn chưa chọn loại công việc";
ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired.display = "Dynamic";
ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired.validationGroup = "a";
ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired.initialvalue = "";
var ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1 = document.all ? document.all["ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1"] : document.getElementById("ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1");
ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1.controltovalidate = "ctl00_ContentPlaceHolder1_UITypeWork1_txtNamePopUp";
ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1.focusOnError = "t";
ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1.errormessage = "Loại công việc phải nhập";
ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1.display = "Dynamic";
ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1.validationGroup = "popup";
ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1.initialvalue = "";
//]]>
</script>


<script type="text/javascript">
//<![CDATA[
var ctl00_MainMenu_Data = new Object();
ctl00_MainMenu_Data.disappearAfter = 100;
ctl00_MainMenu_Data.horizontalOffset = 0;
ctl00_MainMenu_Data.verticalOffset = 0;
ctl00_MainMenu_Data.hoverClass = 'ctl00_MainMenu_12 dmItem1hover';
ctl00_MainMenu_Data.hoverHyperLinkClass = 'ctl00_MainMenu_11 dmItem1hover';
ctl00_MainMenu_Data.staticHoverClass = 'ctl00_MainMenu_10 dmRootmenuHover';
ctl00_MainMenu_Data.staticHoverHyperLinkClass = 'ctl00_MainMenu_9 dmRootmenuHover';

var Page_ValidationActive = false;
if (typeof(ValidatorOnLoad) == "function") {
    ValidatorOnLoad();
}

function ValidatorOnSubmit() {
    if (Page_ValidationActive) {
        return ValidatorCommonOnSubmit();
    }
    else {
        return true;
    }
}
        
theForm.oldSubmit = theForm.submit;
theForm.submit = WebForm_SaveScrollPositionSubmit;

theForm.oldOnSubmit = theForm.onsubmit;
theForm.onsubmit = WebForm_SaveScrollPositionOnSubmit;

document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired').dispose = function() {
    Array.remove(Page_Validators, document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_ddlTypeWorkGroupRequired'));
}

document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1').dispose = function() {
    Array.remove(Page_Validators, document.getElementById('ctl00_ContentPlaceHolder1_UITypeWork1_RequiredFieldValidator1'));
}
Sys.Application.add_init(function() {
    $create(AjaxControlToolkit.ModalPopupBehavior, {"BackgroundCssClass":"modalBackground","CancelControlID":"ctl00_ContentPlaceHolder1_UITypeWork1_ibtClose","PopupControlID":"ctl00_ContentPlaceHolder1_UITypeWork1_divPopUp","PopupDragHandleControlID":"tdPopupText","dynamicServicePath":"/Pages/List/TypeOfWork.aspx","id":"mpe"}, null, null, $get("ctl00_ContentPlaceHolder1_UITypeWork1_lbtHide"));
});
Sys.Application.add_init(function() {
    $create(AjaxControlToolkit.ModalPopupBehavior, {"BackgroundCssClass":"modalBackground","PopupControlID":"ctl00_ContentPlaceHolder1_UITypeWork1_UIProgressPopup1_pnlPopup","dynamicServicePath":"/Pages/List/TypeOfWork.aspx","id":"mdlPopup"}, null, null, $get("ctl00_ContentPlaceHolder1_UITypeWork1_UIProgressPopup1_pnlPopup"));
});
//]]>
</script>
</form>


</body></html>