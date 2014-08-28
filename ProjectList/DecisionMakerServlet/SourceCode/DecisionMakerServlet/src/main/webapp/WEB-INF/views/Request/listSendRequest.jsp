<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">
<script type="text/javascript">
	function compareDate(startDay, endDay) {
		var date1 = Date.parse(startDay);
	    var date2 = Date.parse(endDay);
	    if (date1 <= date2) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}

	function strip_tags(input, allowed) {
		// +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
		allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join(''); // making sure the allowed arg is a string containing only tags in lowercase (<a><b><c>)
		var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi, commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
		return input.replace(commentsAndPhpTags, '').replace(tags,function($0, $1) {
			return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0: '';
		});
	}
	
	var safeHtmlRenderer = function(instance, td, row, col, prop, value,
			cellProperties) {
		var escaped = Handsontable.helper.stringify(value);
		escaped = strip_tags(escaped, '<em><b><strong><a><big>'); //be sure you only allow certain HTML tags to avoid XSS threats (you should also remove unwanted HTML attributes)
		td.innerHTML = escaped;
		return td;
	};
	
	var coverRenderer = function(instance, td, row, col, prop, value,
			cellProperties) {
		var escaped = Handsontable.helper.stringify(value);
		if (escaped.indexOf('http') === 0) {
			var $img = $('<img>');
			$img.attr('src', value);
			$img.on('mousedown', function(event) {
				event.preventDefault(); //prevent selection quirk
			});
			$(td).empty().append($img); //empty is needed because you are rendering to an existing cell
		} else {
			Handsontable.renderers.TextRenderer.apply(this, arguments); //render as text
		}
		return td;
	};
	
	function bindDumpButton() {
	    $('body').on('click', 'button[name=dump]', function () {
	    	var dump = $(this).data('dump');
	     	var $container = $(dump);
	     	console.log('data of ' + dump, $container.handsontable('getData'));
	    });
	}
	
	$(document).ready(function () {
		var jsonArr = [];
		$.ajax({
            url: "send.request.load",
            dataType: 'json',
            type: 'GET',
            success: function (res) {
            	for (var i = 0; i < res.length; i++) {
            		if (res[i].readStatus == 1) {
            			var obj = new Object();
            			obj.requestType = "<strong>" + res[i].requestType + "</strong>";
            			obj.requestId = "<strong><a href='detailRequest?id=" +res[i].requestId + "'>" + res[i].requestTitle + "</a></strong>" ;
            			obj.managerName = "<strong>" + res[i].managerName + "</strong>";
            			obj.time = "<strong><span style='color:blue'>" + res[i].startDate + "</span> - <span style='color:red'>" + res[i].endDate + "</span></strong>";
            			obj.startDay = res[i].startDate;
            			obj.endDay = res[i].endDate;
            			obj.managerId = res[i].managerId;
            			obj.assignId = res[i].assignId;
            			obj.content = "<strong>" + res[i].content.substr(0, 40) +  "<a href='javascript: void(0)' onclick=" + '"' + "window.open('detailContent?id=" + res[i].requestId + "', 'windowname1', 'width=400, height=200'); return false;" + '"' + "> ... </a>"  + "</strong>";
            			obj.status = "<strong>" + res[i].status + "</strong>";
            		}
            		else {
            			var obj = new Object();
            			obj.requestType = res[i].requestType;
            			obj.requestId = "<a href='detailRequest?id=" + res[i].requestId + "'>" + res[i].requestTitle + "</a>" ;
            			obj.managerName = res[i].managerName;
            			obj.time = "<span style='color:blue'>" + res[i].startDate + "</span> <strong>-</strong> <span style='color:red'>" + res[i].endDate + "</span>"
            			obj.startDay = res[i].startDate;
            			obj.endDay = res[i].endDate;
            			obj.managerId = res[i].managerId;
            			obj.assignId = res[i].assignId;
            			obj.content = res[i].content.substr(0, 40) + "<a href='javascript: void(0)' onclick=" + '"' + "window.open('detailContent?id=" + res[i].requestId + "', 'windowname1', 'width=400, height=200'); return false;" + '"' + "> ... </a>";
            			obj.status = res[i].status;
            		}
            		jsonArr.push(obj);
            	}  
            	var $container = $("#example1");
            	$container.handsontable({
            		data: jsonArr,
            	    colWidths: [150, 150, 100, 200, 300, 100],
            	    colHeaders: ["Loại yêu cầu", "Tiêu đề", "Người quản lý", "Thời gian", "Nội dung", "Trạng thái"],
            	    columns: [
						{data: "requestType", renderer: "html"},
            	     	{data: "requestId", renderer: "html"},
            	     	{data: "managerName", renderer: "html"},
            	     	{data: "time", renderer: "html"},
            	     	{data: "content", renderer: "html"},
            	     	{data: "status", renderer: "html"},
            	    ],
            	    cells: function(r,c, prop) {
            	     	var cellProperties = {};
            	     	cellProperties.readOnly = true;
            	     	return cellProperties;        
            	    }	
            	});
            	bindDumpButton();
            },
            error: function() {
            	alert("Không thể lấy danh sách yêu cầu. Hãy liên hệ với người quản lý");
            }
         });
		
		$("#reqType").change(function(){
			var jsonArrResult = [];
			var requestType = $("#reqType option:selected").val();
			if((requestType == 0) && ((startDay == null) || (startDay == '')) && ((endDay == null) || (endDay == '')) && ((requestManager == 0)) && ((requestAssign == 0))) {
				var $container = $("#example1");
            	$container.handsontable({
            		data: jsonArr,
            	    colWidths: [150, 150, 100, 200, 300, 100],
            	    colHeaders: ["Loại yêu cầu", "Tiêu đề", "Người quản lý", "Thời gian", "Nội dung", "Trạng thái"],
            	    columns: [
						{data: "requestType", renderer: "html"},
            	     	{data: "requestId", renderer: "html"},
            	     	{data: "managerName", renderer: "html"},
            	     	{data: "time", renderer: "html"},
            	     	{data: "content", renderer: "html"},
            	     	{data: "status", renderer: "html"},
            	    ],
            	    cells: function(r,c, prop) {
            	     	var cellProperties = {};
            	     	cellProperties.readOnly = true;
            	     	return cellProperties;        
            	    }	
            	});
            	bindDumpButton();
			}
			else {
// 				alert("Change");
				var startDay = $("#startDate").val();
				var endDay = $("#endDate").val();
				var requestManager = $("#reqManager option:selected").val();
				var requestAssign = $("#reqAssign option:selected").val();
				for (var i = 0; i < jsonArr.length; i++) {
// 					if (startDay != '' && endDay != '') {
// 						if ((jsonArr[i].requestType.indexOf(requestType) >= 0) || (compareDate(jsonArr[i].startDay, startDay)) || (compareDate(endDay, jsonArr[i].endDay)) || ((requestManager != 0) && (jsonArr[i].managerId.indexOf(requestManager))) || ((requestAssign != 0) && (jsonArr[i].assignId.indexOf(requestAssign)))) {
// 							jsonArrResult.push(jsonArr[i]);
// 						}
// 					}
// 					else if (startDay != '' && endDay == '') {
// 						if ((jsonArr[i].requestType.indexOf(requestType) >= 0) || (compareDate(jsonArr[i].startDay, startDay)) || ((requestManager != 0) && (jsonArr[i].managerId.indexOf(requestManager))) || ((requestAssign != 0) && (jsonArr[i].assignId.indexOf(requestAssign)))) {
// 							jsonArrResult.push(jsonArr[i]);
// 						}
// 					}
// 					else if (startDay == '' && endDay != '') {
// 						if ((jsonArr[i].requestType.indexOf(requestType) >= 0) || (compareDate(endDay, jsonArr[i].endDay)) || ((requestManager != 0) && (jsonArr[i].managerId.indexOf(requestManager))) || ((requestAssign != 0) && (jsonArr[i].assignId.indexOf(requestAssign)))) {
// 							jsonArrResult.push(jsonArr[i]);
// 						}
// 					}
// 					else {
// 						if ((jsonArr[i].requestType.indexOf(requestType) >= 0) || ((requestManager != 0) && (jsonArr[i].managerId.indexOf(requestManager))) || ((requestAssign != 0) && (jsonArr[i].assignId.indexOf(requestAssign)))) {
// 							jsonArrResult.push(jsonArr[i]);
// 						}
// 					}
// 							alert(jsonArr[i].managerId);
// 							alert(requestManager);
// 							alert(jsonArr[i].assignId);
// 							alert(requestAssign);
// 					if ((jsonArr[i].requestType.indexOf(requestType) >= 0) && ((requestManager != 0) && (jsonArr[i].managerId == requestManager)) && ((requestAssign != 0) && (jsonArr[i].assignId == requestAssign))) {
// 					alert(requestType);
					if (jsonArr[i].requestType.indexOf(requestType) >= 0) {
						jsonArrResult.push(jsonArr[i]);
					}
				}
	 	     	var $container = $("#example1");
	 	     	$container.handsontable({
	 	     		data: jsonArrResult,
	 	     	    colWidths: [150, 150, 100, 200, 300, 100],
	 	     	    colHeaders: ["Loại yêu cầu", "Tiêu đề", "Người quản lý", "Thời gian", "Nội dung", "Trạng thái"],
	 	     	    columns: [
						{data: "requestType", renderer: "html"},
	 	     	      	{data: "requestId", renderer: "html"},
	 	     	      	{data: "managerName", renderer: "html"},
	 	     	     	{data: "time", renderer: "html"},
	 	     	     	{data: "content", renderer: "html"},
	 	     	     	{data: "status", renderer: "html"},
	 	     	    ],
	 	     	   cells: function(r,c, prop) {
	 	     	        var cellProperties = {};
	 	     	        cellProperties.readOnly = true;
	 	     	        return cellProperties;        
	 	     	    }	
	 	     	  });
	 	     	  bindDumpButton();
			}
			
		});
		
// 		$("#reqManager").change(function(){
// 			var jsonArrResult = [];
// 			if((requestType == 0) && ((startDay == null) || (startDay == '')) && ((endDay == null) || (endDay == '')) && ((requestManager == 0)) && ((requestAssign == 0))) {
// 				var $container = $("#example1");
//             	$container.handsontable({
//             		data: jsonArr,
//             	    colWidths: [150, 150, 100, 200, 300, 100],
//             	    colHeaders: ["Loại yêu cầu", "Tiêu đề", "Người nhận", "Thời gian", "Lý do", "Trạng thái"],
//             	    columns: [
// 						{data: "requestType", renderer: "html"},
//             	     	{data: "requestId", renderer: "html"},
//             	     	{data: "managerName", renderer: "html"},
//             	     	{data: "time", renderer: "html"},
//             	     	{data: "", renderer: "html"},
//             	     	{data: "status", renderer: "html"},
//             	    ],
//             	    cells: function(r,c, prop) {
//             	     	var cellProperties = {};
//             	     	cellProperties.readOnly = true;
//             	     	return cellProperties;        
//             	    }	
//             	});
//             	bindDumpButton();
// 			}
// 			else {
// 				if (startDay != null && startDay != '')
// 					var startCheck = compareDate(jsonArr[i].startDay, startDay);
// 				if (endDay != null && endDay != '')
// 					var endCheck = compareDate(endDay, jsonArr[i].endDay);
// 				for (var i = 0; i < jsonArr.length; i++) {
// 					if (jsonArr[i].requestType.indexOf($("#reqManager option:selected").val()) >= 0) {
// 						jsonArrResult.push(jsonArr[i]);
// 					}
// 				}
	 	     	  
// 	 	     	var $container = $("#example1");
// 	 	     	$container.handsontable({
// 	 	     		data: jsonArrResult,
// 	 	     	    colWidths: [150, 150, 100, 200, 300, 100],
// 	 	     	    colHeaders: ["Loại yêu cầu", "Tiêu đề", "Người nhận", "Thời gian", "Lý do", "Trạng thái"],
// 	 	     	    columns: [
// 						{data: "requestType", renderer: "html"},
// 	 	     	      	{data: "requestId", renderer: "html"},
// 	 	     	      	{data: "managerName", renderer: "html"},
// 	 	     	     	{data: "time", renderer: "html"},
// 	 	     	     	{data: "", renderer: "html"},
// 	 	     	     	{data: "status", renderer: "html"},
// 	 	     	    ],
// 	 	     	   cells: function(r,c, prop) {
// 	 	     	        var cellProperties = {};
// 	 	     	        cellProperties.readOnly = true;
// 	 	     	        return cellProperties;        
// 	 	     	    }	
// 	 	     	  });
// 	 	     	  bindDumpButton();
// 			}
			
// 		});
	});
</script>
<h3>Yêu cầu của tôi</h3>
<div>
	<label class="col_1.5">Loại yêu cầu :</label>
	<select id="reqType" class="col_2 column" name="reqType">
		<option value="0">-- Tất cả --</option>
		<c:forEach var="reqType" items="${lstReqTypes}">
			<c:choose>
				<c:when test="${reqType.cd == param.newReqType}">
					<option value="${reqType.cd}" selected="selected">${reqType.name}</option>
				</c:when>
				<c:otherwise>
					<option value="${reqType.cd}">${reqType.name}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<label class="col_1.5">Ngày bắt đầu : </label>
	<input id="startDate" type="date" class="col_2 column"/>
	<label class="col_1.5">Ngày kết thúc : </label>
	<input id="endDate" type="date" class="col_2 column"/>
</div>
<div>
	<label class="col_1.5">Người quản lý : </label>
	<select id="reqManager" class="col_2 column">
		<option value="0">-- Tất cả --</option>
        <c:forEach var="user" items="${listUsers}">
            <option value="${user.id}">${user.username}</option>
        </c:forEach>
	</select>
	<label class="col_1.5">Người được giao : </label>
	<select id="reqAssign" class="col_2 column">
		<option value="0">-- Tất cả --</option>
        <c:forEach var="user" items="${listUsers}">
            <option value="${user.id}">${user.username}</option>
        </c:forEach>
	</select>
</div>
<p id="test">
<a href="javascript: void(0)" 
   onclick="window.open('popup.html', 
  'windowname1', 
  'width=200, height=77'); 
   return false;">Click here for simple popup window</a>
</p>
<div id="example1" class="handsontable" style="top:10px;"></div>
