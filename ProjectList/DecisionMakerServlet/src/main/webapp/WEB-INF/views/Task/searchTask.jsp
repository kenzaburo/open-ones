<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="resources/handsontable/lib/jquery-1.10.2.js"></script>
<script src="resources/handsontable/jquery.handsontable.full.js"></script>
<script src="resources/handsontable/lib/jquery-ui/js/jquery-ui.custom.min.js"></script>

<link rel="stylesheet" media="screen" href="resources/handsontable/jquery.handsontable.full.css">
<link rel="stylesheet" media="screen" href="resources/handsontable/lib/jquery-ui/css/ui-bootstrap/jquery-ui.custom.css">

        <div>
          <div class="fl-col view-filter">
            <label for="unit">Phòng ban</label>
            <select id="scopes">
              <option value="">Tất cả</option>
              <option value="">Ban giám đốc</option>
              <option value="">Phòng kế toán</option>
              <option value="">Phòng kinh doanh</option>
              <option value="">Kho</option>
            </select>
          </div>
          <div class="fl-col flc-pager-top view-pager">
            <ul id="pager-top" class="fl-pager-ui">
              <li class="flc-pager-previous"><a href="javascript:;">&lt; Trước</a></li>
              <li>
                <ul class="fl-pager-links flc-pager-links" style="margin:0; display:inline">
                  <li class="flc-pager-pageLink"><a href="javascript:;">1</a></li>
                  <%-- <li class="flc-pager-pageLink-disabled">2</li>
                  <li class="flc-pager-pageLink"><a href="javascript:;">3</a></li>--%>
                </ul>
              </li>
              <li class="flc-pager-next"><a href="javascript:;">Tiếp &gt;</a></li>
              <li>
                <span class="flc-pager-summary">Hiển thị</span>
                <span> <select class="pager-page-size flc-pager-page-size">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
                </select></span> dòng trên trang
              </li>
            </ul>
          </div>
        </div>
        <div style="clear:both"></div>

        <div>
            <div id="dataTable"></div>
            <div id="separator"></div>
            <a id="ViewDetail" class="button" href=${viewDetailUrl}>Xem chi tiết</a>
        </div>

      
      </div>

        

     <script type="text/javascript">    
        $(document).ready(function() {

            function createJsonSection(json) {
                var IDX_TITLE = 0;
                var IDX_SELECT = 3;
                var jsonObj = [];
                for (var i in json) {
                    var obj = {};
                    
                    obj["select"] = json[i][IDX_SELECT];
                    obj["title"] = json[i][IDX_TITLE];
                    jsonObj.push(obj);

                }
                return jsonObj;
            }

            var data = [
                        ["Lập kế hoạch cho chương trình khuyến mãi tháng 12", "Nguyễn Văn Lẹ", "Trần Chiến Thắng", "Đang thực hiện", "1/9/2014", "1/9/2014", ""],
                        ["Nhập hàng tháng 12", "Nguyễn Văn Lẹ", "Trần Chiến Thắng", "Chưa thực hiện", "1/9/2014", "1/9/2014", ""],
                        ["Nhập hàng tháng 1/2015", "Nguyễn Văn Lẹ", "Trần Chiến Thắng", "Đã thực hiện", "1/9/2014", "1/9/2014", ""],
                        ["Nhập hàng tháng 7/2014", "Nguyễn Văn Lẹ", "Trần Chiến Thắng", "Đóng", "1/6/2014", "15/6/2014", "30/6/2014"],
                    ];
            
            var container = $("#dataTable");
            var parent = container.parent();
            container.handsontable({
                data: data,
                maxRows: 3,
                maxCols: 7,
                rowHeaders: true,
                colHeaders: ['Công việc', 'Thực hiện', 'Nhận báo cáo', "Trạng thái", "Ngày tạo", "Cập nhật", "Hoàn thành"],
                colWidths: [300, 70, 70, 50, 50, 50, 50],
                manualColumnResize: true,
                columns: [
                          {
                        	  readOnly: true
                          },
                          {
                              readOnly: true
                          },
                          {
                              readOnly: true
                          },
                          {
                              readOnly: true
                          },
                          {
                            type: 'date',
                            dateFormat: 'dd/mm/yy',
                            readOnly: true
                          },
                          {
                            type: 'date',
                            dateFormat: 'dd/mm/yy',
                            readOnly: true
                          },
                          {
                            type: 'date',
                            dateFormat: 'dd/mm/yy',
                            readOnly: true
                          }
                        ],
                minSpareRows: 0,

                contextMenu: {
                	callback: function (key, options) {
                	      if (key === 'viewDetail') {
                	        setTimeout(function () {
                	         var json = container.handsontable('getData');
                	          //timeout is used to make sure the menu collapsed before alert is shown
                              var selectedRow = json[0]; 
                	          alert("Xem chi tiết thông báo.selectedRow" + selectedRow);
                	        }, 100);
                	      }
                	    },
                	items: {
                      "viewDetail": {name: 'Xem chi tiết'} 
                    }
                      
                }
            });

//             $("#ViewDetail").click(function() {
//                 var json = container.handsontable('getData');
//                 $.ajax({
//                     dataType: 'json',
//                     contentType: 'application/json',
//                     url: "${goNextUrl}",
//                     data: {"data": JSON.stringify(createJsonSection(json))}, //returns all cells' data
//                     type: 'POST',
//                     success: function(res) {
//                         alert("Chuẩn bị lưu dữ liệu.");
//                     },
//                     error: function() {
//                         alert("Có lỗi hệ thống. Hãy liên lạc với người quản trị.");
//                     }
//                 });
//             });
            
            $("button#selectFirst").on('click', function () {
                container.handsontable("selectCell", 0, 0);
            });

            $("input#rowHeaders").change(function () {
              container.handsontable("updateSettings", {rowHeaders: $(this).is(':checked')});
            });

            $("input#colHeaders").change(function () {
              container.handsontable("updateSettings", {colHeaders: $(this).is(':checked')});
            });
      });
    </script>
    