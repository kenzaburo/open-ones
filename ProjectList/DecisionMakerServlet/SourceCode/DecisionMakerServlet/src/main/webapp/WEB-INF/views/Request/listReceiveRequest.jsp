<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">
<script type="text/javascript">
	$(document).ready(function () {
		var jsonArr = [];
		var user = $("#loginUser").html();
		alert(user);
		$.ajax({
            url: "receive.request.load",
            data: {username: user},
            dataType: 'json',
            type: 'GET',
            success: function (res) {
            	for (var i = 0; i < res.length; i++) {
            		var obj = new Object();
            			obj.requestId = "<a href='detailRequest?id=" +res[i].requestId + "'>" + res[i].requestTitle + "</a>" ;
            			obj.createName = res[i].createName;
            			obj.time = "<span style='color:blue'>" + res[i].startDate + "</span> <strong>-</strong> <span style='color:red'>" + res[i].endDate + "</span>"
            			obj.reason = res[i].reason;
            		var jsonIn = JSON.stringify(obj);
            		jsonArr.push(obj);
            	}  
            	     	  function strip_tags(input, allowed) {
            	     	    // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
            	     	    allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join(''); // making sure the allowed arg is a string containing only tags in lowercase (<a><b><c>)
            	     	    var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
            	     	      commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
            	     	    return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
            	     	      return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
            	     	    });
            	     	  }
            	     	  
            	     	  var safeHtmlRenderer = function (instance, td, row, col, prop, value, cellProperties) {
            	     	    var escaped = Handsontable.helper.stringify(value);
            	     	    escaped = strip_tags(escaped, '<em><b><strong><a><big>'); //be sure you only allow certain HTML tags to avoid XSS threats (you should also remove unwanted HTML attributes)
            	     	    td.innerHTML = escaped;
            	     	    return td;
            	     	  };
            	     	  
            	     	  var coverRenderer = function (instance, td, row, col, prop, value, cellProperties) {
            	     	    var escaped = Handsontable.helper.stringify(value);
            	     	    if (escaped.indexOf('http') === 0) {
            	     	      var $img = $('<img>');
            	     	      $img.attr('src', value);
            	     	      $img.on('mousedown', function (event) {
            	     	        event.preventDefault(); //prevent selection quirk
            	     	      });
            	     	      $(td).empty().append($img); //empty is needed because you are rendering to an existing cell
            	     	    }
            	     	    else {
            	     	      Handsontable.renderers.TextRenderer.apply(this, arguments); //render as text
            	     	    }
            	     	    return td;
            	     	  };
            	     	  
            	     	  var $container = $("#example1");
            	     	  $container.handsontable({
            	     	  	data: jsonArr,
            	     	    colWidths: [150, 100, 200, 300],
            	     	    colHeaders: ["Tiêu đề", "Người xin", "Thời gian", "Lý do"],
            	     	    columns: [
            	     	      	{data: "requestId", renderer: "html"},
            	     	      	{data: "createName", renderer: "html"},
            	     	     	{data: "time", renderer: "html"},
            	     	     	{data: "reason", renderer: "html"},
            	     	    ],
            	     	   cells: function(r,c, prop) {
            	     	        var cellProperties = {};
            	     	        cellProperties.readOnly = true;
            	     	        return cellProperties;        
            	     	    }	
            	     	  });
            	     	  
            	     	  function bindDumpButton() {
            	     	      $('body').on('click', 'button[name=dump]', function () {
            	     	        var dump = $(this).data('dump');
            	     	        var $container = $(dump);
            	     	        console.log('data of ' + dump, $container.handsontable('getData'));
            	     	      });
            	     	    }
            	     	  bindDumpButton();
//             	$.each(res, function(i, item){
                    
//                 });

//               	var handsontable = container.data('handsontable');
//               	handsontable.loadData(res.data);
            },
            error: function() {
            	alert("Fail");
//             	alert($.parseJSON(data));
            	
//             	alert("Không thể lấy danh sách yêu cầu. Hãy liên hệ với người quản lý");
            }
         });
	});
</script>

<p id="loginUser" hidden="hidden">${pageContext.request.userPrincipal.name}</p>
<div id="example1" class="handsontable"></div>
