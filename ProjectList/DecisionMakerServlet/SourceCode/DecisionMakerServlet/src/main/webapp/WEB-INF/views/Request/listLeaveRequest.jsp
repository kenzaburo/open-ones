<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">
<script type="text/javascript">
	var requestContent = "";
	var requestTitle = "";
	var jsonArr = [];

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
		var htmlOptionDefault = $("#reqCreator").html();
		$.ajax({
            url: "search.leave.request",
            data: {startDay: "", endDay: "", userCd: "", departmentCd: ""},
            dataType: 'json',
            type: 'GET',
            success: function (res) {
            	for (var i = 0; i < res.length; i++) {
	            	var obj = new Object();
        			obj.requestType = res[i].requestType;
        			obj.requestId = "<a href='detailRequest?id=" +res[i].requestId + "'>" + res[i].requestTitle + "</a>" ;
        			obj.managerName = res[i].managerName;
        			obj.time = "<span style='color:blue'>" + res[i].startDate + "</span> <strong>-</strong> <span style='color:red'>" + res[i].endDate + "</span>"
        			obj.startDay = res[i].startDate;
        			obj.endDay = res[i].endDate;
        			obj.managerId = res[i].managerId;
        			obj.assignId = res[i].assignId;
        			obj.content = res[i].content.substr(0, 40) + "<a href='javascript: void(0)' onclick=" + '"' + "window.open('detailContent?id=" + res[i].requestId + "', 'windowname1', 'width=400, height=200'); return false;" + '"' + "> ... </a>";
        			obj.status = res[i].status;
        			jsonArr.push(obj);
            	}
        		
        		var $container = $("#example1");
            	$container.handsontable({
            		data: jsonArr,
            	    colWidths: [150, 150, 100, 200, 300, 100],
            	    colHeaders: ["Loại yêu cầu", "Tiêu đề", "Người nhận", "Thời gian", "Lý do", "Trạng thái"],
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
		
		$("#reqDepartement").change(function(){
			var requestDepartment = $("#reqDepartement option:selected").val();
			if (requestDepartment != "0") {
				$.ajax({
					url: "load.user",
					data: {departmentCd: requestDepartment},
					dataType: "json",
					type: "GET",
					success: function (res) {
						var htmlOption = "<option value='0'>All User</option>";
						for (var i = 0; i < res.length; i++) {
							htmlOption += "<option value='" + res[i].cd +"'>" + res[i].name + "</option>";
						}
						$("#reqCreator").html(htmlOption);
					},
					error: function() {
			            alert("Không thể lấy danh sách yêu cầu. Hãy liên hệ với người quản lý");
			        }
				});
			}
			else{
				$("#reqCreator").html(htmlOptionDefault);
			}
		});
		
		$("#searchButton").click(function(){
			var startDay = $("#startDate").val();
			var endDay = $("#endDate").val();
			var requestDepartment = $("#reqDepartement option:selected").val();
			var requestCreator = $("#reqCreator option:selected").val();
			if ($("#reqContent").val() != null && $("#reqContent").val() != "")
				requestContent = $("#reqContent").val();
			if ($("#reqTitle").val() != null && $("#reqTitle").val() != "")
				requestTitle = $("#reqTitle").val();
			$.ajax({
	            url: "search.leave.request",
	            data: {createdbyCd: "", startDate: startDay, endDate: endDay, userCd: requestCreator, departmentCd: requestDepartment},
	            dataType: 'json',
	            type: 'GET',
	            success: function (res) {
	            	for (var i = 0; i < res.length; i++) {
		            	var obj = new Object();
	        			obj.requestType = res[i].requestType;
	        			obj.requestId = "<a href='detailRequest?id=" +res[i].requestId + "'>" + res[i].requestTitle + "</a>" ;
	        			obj.managerName = res[i].managerName;
	        			obj.time = "<span style='color:blue'>" + res[i].startDate + "</span> <strong>-</strong> <span style='color:red'>" + res[i].endDate + "</span>"
	        			obj.startDay = res[i].startDate;
	        			obj.endDay = res[i].endDate;
	        			obj.managerId = res[i].managerId;
	        			obj.assignId = res[i].assignId;
	        			obj.content = res[i].content.substr(0, 40) + "<a href='javascript: void(0)' onclick=" + '"' + "window.open('detailContent?id=" + res[i].requestId + "', 'windowname1', 'width=400, height=200'); return false;" + '"' + "> ... </a>";
	        			obj.status = res[i].status;
	        			jsonArr.push(obj);
	            	}
            		
            		var $container = $("#example1");
                	$container.handsontable({
                		data: jsonArr,
                	    colWidths: [150, 150, 100, 200, 300, 100],
                	    colHeaders: ["Loại yêu cầu", "Tiêu đề", "Người nhận", "Thời gian", "Lý do", "Trạng thái"],
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
		});
		
	});
</script>
<h3>Tìm kiếm</h3>
<div>
	<label class="col_2">Ngày bắt đầu : </label>
	<input id="startDate" type="date" class="col_2 column"/>
	<label class="col_2">Ngày kết thúc : </label>
	<input id="endDate" type="date" class="col_2 column"/>
</div>
<div>
	
	<label class="col_2">Phòng ban : </label>
	<select id="reqDepartement" class="col_2 column">
		<option value="0">-- Tất cả --</option>
        <c:forEach var="dept" items="${listDepartment}">
            <option value="${dept.cd}">${dept.name}</option>
        </c:forEach>
	</select>
	<label class="col_2">Người xin nghỉ : </label>
	<select id="reqCreator" class="col_2 column">
		<option value="0">-- Tất cả --</option>
        <c:forEach var="user" items="${listUsers}">
            <option value="${user.username}">${user.username}</option>
        </c:forEach>
	</select>
	<button id="searchButton" style="margin-left: 0.83333333333333%;">Search</button>
</div>
<div>
	<label class="col_2">Tiêu đề : </label>
	<input id="reqTitle" type="text" class="col_2 column"/>
	<label class="col_2">Nội dung : </label>
	<input id="reqContent" type="text" class="col_2 column"/>
</div>

<div id="example1" class="handsontable" style="top:10px;"></div>
