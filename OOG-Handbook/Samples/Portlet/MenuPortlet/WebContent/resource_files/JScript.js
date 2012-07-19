

function trimAll(sString) {

    while (sString.substring(0, 1) == ' ') {

        sString = sString.substring(1, sString.length);

    }

    while (sString.substring(sString.length - 1, sString.length) == ' ') {

        sString = sString.substring(0, sString.length - 1);

    }

    return sString;

}

// VanNT
function isNumberKeyOnly(evt) {
    /*
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
    return false;

    return true;
    */
    var e = evt
    if (window.event) { // IE
        var charCode = window.event.keyCode;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which
    }
    //confirm(charCode);

    //46 dau cham
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;

}

function numbersonly(myfield, e, dec) {
    var key;
    var keychar;

    if (window.event)
        key = window.event.keyCode;
    else if (e)
        key = e.which;
    else
        return true;
    keychar = String.fromCharCode(key);

    // control keys
    if ((key == null) || (key == 0) || (key == 8) ||
    (key == 9) || (key == 13) || (key == 27))
        return true;


    // numbers
    else if ((("0123456789").indexOf(keychar) > -1))
        return true;

    // decimal point jump
    else if (dec && (keychar == ".")) {
        myfield.form.elements[dec].focus();
        return false;
    }
    else
        return false;
}



function isNumberKey(evt) {
    /*
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
    return false;

    return true;
    */
    var e = evt
    if (window.event) { // IE
        var charCode = window.event.keyCode;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which
    }

    //46 dau cham
    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 46)
        return false;
    return true;

}

//vinhhv------------------------------------------------------------
function replaceAll(source, stringToFind, stringToReplace) {
    var temp = source;
    var index = temp.indexOf(stringToFind);
    while (index != -1) {
        temp = temp.replace(stringToFind, stringToReplace);
        index = temp.indexOf(stringToFind);
    }
    return temp;
}
function keyUpPlus(evt, obj) {
    var text = replaceAll(obj.value, "+", "10");
    obj.value = text;
    return true;
}
function isNumberKeySpace2(evt) {
    var e = evt
    if (window.event) { // IE
        var charCode = window.event.keyCode;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which
    }
    //43 dấu +
    if (charCode == 43) {
        return true;
    }
    //46 dau cham 32 Space; 
    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 32)
        return false;
    return true;
}
function isNumberKey3(evt) {

    var e = evt
    if (window.event) { // IE
        var charCode = window.event.keyCode;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which
    }
    //43 dấu +
    if (charCode == 43) {
        return true;
    }
    //46 dau cham 32 Space
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
//end:vinhhv-----------------------------------------------

function isNumberKeySpace(evt) {

    var e = evt
    if (window.event) { // IE
        var charCode = window.event.keyCode;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which
    }
    //confirm(charCode);
    //46 dau cham 32 Space; 
    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 32)
        return false;
    return true;

}
//---------------------- Hungnd --------------
function numberKeyOnly(evt) {
    var e = evt
    if (window.event) { // IE
        var charCode = e.keyCode;
        if (((charCode >= 48 && charCode <= 57) // default numberkey
             || (charCode >= 96 && charCode <= 105)) // numlock numberkey
             || charCode == 46 // Delete key
             || charCode == 8) // Backspace
            return true;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which;
        if (((charCode >= 48 && charCode <= 57) // default numberkey
             || (charCode >= 96 && charCode <= 105)) // numlock numberkey
             || charCode == 46 // Delete key
             || charCode == 8) // Backspace
            return true;
    }
    return false;
}

//-------------------- End Hungnd ------------------

function isNumberKey2(evt) {

    var e = evt
    if (window.event) { // IE
        var charCode = window.event.keyCode;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which
    }
    //46 dau cham 32 Space
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;

}



function alertMark(txtMark) {
    var text = txtMark.value;
    if (text.length == 2) {
        if (text > 10) {
            txtMark.value = "";
        }
    }

}

function Conver20(txtMark) {
    var text = txtMark.value;
    var num = "0123456789";
    var num1 = "23456789";

    if (text.length == 2) {
        if (text > 10) {
            txtMark.value = text.charAt(0) + ',' + text.charAt(1);
        } else if (text.charAt(0) == '0' && num.indexOf(text.charAt(1)) > -1) {
            txtMark.value = text.charAt(0) + ',' + text.charAt(1);
        }

    } else if (text.length == 3) {
        if (num.indexOf(text.charAt(1)) == -1) {
            txtMark.value = text.charAt(0) + ',' + text.charAt(2);
        } else txtMark.value = text.charAt(0) + ',' + text.charAt(1);
    }

    if (num.indexOf(text.charAt(0)) == -1) {
        txtMark.value = "";
    }

}

function Conver21(txtMark) {
    var text = txtMark.value;
    if (text.length == 3) {
        //txtMark.value = '';

    }
}

function Conver22(txtMark) {
    var text = txtMark.value;
    var num = "0123456789";

    if (num.indexOf(text.charAt(0)) == -1) {
        txtMark.value = "";
    }

    /*if (text.length == 2) {
    if (num.indexOf(text.charAt(1)) == -1) {
    txtMark.value = text.charAt(0);  //+ ',0';
    }
    }*/

    if (text.length == 2) {
        if (text > 10) {
            txtMark.value = text.charAt(0) + '.' + text.charAt(1);
        } else if (text.charAt(0) == '0' && num.indexOf(text.charAt(1)) > -1) {
            txtMark.value = text.charAt(0) + '.' + text.charAt(1);
        }

    } else if (text.length == 3) {
        if (num.indexOf(text.charAt(1)) == -1) {
            txtMark.value = text.charAt(0) + '.' + text.charAt(2);
        } else txtMark.value = text.charAt(0) + '.' + text.charAt(1);
    }

    if (num.indexOf(text.charAt(0)) == -1) {
        txtMark.value = "";
    }
}

function ConverM(txtMark) {
    var text = txtMark.value;
    var num = "0123456789";

    if (num.indexOf(text.charAt(0)) == -1) {
        txtMark.value = "";
    }

    if (text.length == 2) {
        if (text > 10) {
            txtMark.value = text.charAt(0);
        } else if (text.charAt(0) == '0' && num.indexOf(text.charAt(1)) > -1) {
            txtMark.value = text.charAt(0);
        }

    } else if (text.length == 3) {
        if (num.indexOf(text.charAt(1)) == -1) {
            txtMark.value = text.charAt(0);
        } else txtMark.value = text.charAt(0);
    }
}


//khong cho phep nhap qua so luong ky tu cho phep
function textareaCounter(field, maxlimit, msg) {
    var countMe = field.value;
    var count = 0;
    var escapedStr = encodeURI(countMe);
    if (escapedStr.indexOf("%") != -1) {
        count = escapedStr.split("%").length - 1
        if (count == 0) count++  //perverse case; can't happen with real UTF-8
        var tmp = escapedStr.length - (count * 3)
        count = count + tmp
    } else {
        count = escapedStr.length
    }


    if (count > maxlimit) {
        alert('Độ dài trường ' + msg + ' không được vượt quá ' + maxlimit + ' ký tự');
        field.focus();
        return false;
        /*var agree = confirm('Độ dài trường ' + msg + ' không được vượt quá ' + maxlimit + ' ký tự!\n Nếu bạn đồng ý hệ thống sẽ tự động cắt bỏ số ký tự vượt giới hạn cho phép.');
        if (agree) {
        field.value = escapedStr.substring(0, maxlimit);  //decodeURI(countMe.substring(0, maxlimit-1));
        return true;
        }
        else {
        return false;
        }
        field.focus();*/
    }
    return true;
}

function limitText(limitField, limitNum, msg) {
    var count = limitField.value.length;

    if (count > limitNum) {
        alert('Độ dài trường ' + msg + ' không được vượt quá ' + limitNum + ' ký tự!');
        limitField.focus();
        return false;
        /*var agree = confirm('Độ dài trường ' + msg + ' không được vượt quá ' + limitNum + ' ký tự!\n Nếu bạn đồng ý hệ thống sẽ tự động cắt bỏ số ký tự vượt giới hạn cho phép.');
        if (agree) {
        limitField.value = limitField.value.substring(0, limitNum);
        return true;
        }
        else {
        return false;
        }
        limitField.focus();*/
    }
    return true;
}

function checkMarkM(txtMark) {
    var text = txtMark.value;
    if (text.lenght <= 0) return;
    var aryTest = text.split(" ");
    var strVal = "";
    var mark;
    if (aryTest.length <= 0) return;

    for (i = 0; i < aryTest.length; i++) {
        if ((aryTest[i] != '' || aryTest[i] != ' ') && (aryTest[i] <= 0 || aryTest[i] > 10)) {
            for (j = 0; j < aryTest[i].length; j++) {
                if (j + 1 <= aryTest[i].length) {
                    if (aryTest[i].charAt(j) == 1 && aryTest[i].charAt(j + 1) == 0) {
                        mark = aryTest[i].charAt(j) + aryTest[i].charAt(j + 1);
                        strVal += mark + " ";
                    }
                    else if (aryTest[i].charAt(j) > 1 && aryTest[i].charAt(j + 1) == 0) {
                        mark = aryTest[i].charAt(j);
                        strVal += mark + " ";
                    }
                    else if (aryTest[i].charAt(j) == 0) {
                        strVal += "";
                    }
                    else {
                        strVal += aryTest[i].charAt(j) + " ";
                    }

                }
            }
        }
        else {
            strVal += aryTest[i] + " ";
        }
    }


    txtMark.value = strVal.substring(0, strVal.length - 1);

}

function SelectAll(id, clientID, columnID) {

    var gv = document.getElementById(clientID);
    var rows = gv.rows;
    var ckb;
    var browser = navigator.appName;
    var checkBoxAll = document.getElementById(id);
    var inputs = gv.getElementsByTagName('input');
    for (var i = 0; i < inputs.length; i++) {
        /*if (browser == "Netscape") ckb = rows[i].cells[columnID].childNodes[1];
        else ckb = rows[i].cells[columnID].childNodes[0];
        ckb = rows[i].cells[columnID].childNodes[0];
        ckb.checked = checkBoxAll.checked;*/
        if (inputs[i].type == "checkbox" && !inputs[i].disabled) {
            inputs[i].checked = checkBoxAll.checked;
        }
    }
}

function SelectAllByEndColumnID(id, clientID, EndColumnID) {

    var gv = document.getElementById(clientID);
    var checkBoxAll = document.getElementById(id);
    var inputs = gv.getElementsByTagName('input');

    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type == "checkbox" && !inputs[i].disabled) {

            var str = inputs[i].id;
            var lastIndex = str.lastIndexOf(EndColumnID);

            if ((lastIndex > -1) && (lastIndex + EndColumnID.length == str.length)) {
                inputs[i].checked = checkBoxAll.checked;
            }
        }
    }
}

function childCheckByEndColumnID(headerCheckBoxID, clientID, EndColumnID) {

    var gv = document.getElementById(clientID);
    var checkBoxAll = document.getElementById(headerCheckBoxID);
    var inputs = gv.getElementsByTagName('input');

    var isCheck = true;

    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type == "checkbox" && !inputs[i].disabled) {

            var str = inputs[i].id;
            var lastIndex = str.lastIndexOf(EndColumnID);

            if ((lastIndex > -1) && (lastIndex + EndColumnID.length == str.length)) {
                if (inputs[i].id != headerCheckBoxID && inputs[i].checked == false) {
                    isCheck = false;
                }
            }
        }
    }

    if (checkBoxAll.type == "checkbox") {
        checkBoxAll.checked = isCheck;
    }
}



function childCheck(headerCheckBoxID, clientID, columnID) {


    //var headerCheckBox = document.getElementById(headerCheckBoxID);
    //var gv = document.getElementById(clientID);
    //var rows = gv.rows;
    //var ckb;
    //var browser = navigator.appName;
    //for (i = 1; i < rows.length; i++) {

    /*if (browser == "Netscape") ckb = rows[i].cells[columnID].childNodes[1];
    else ckb = rows[i].cells[columnID].childNodes[0];
    ckb = rows[i].cells[columnID].childNodes[0];
    alert(rows[1].cells[columnID].childNodes[0]);
    if (!ckb.checked && !ckb.disabled) {
    headerCheckBox.checked = false;
    return;
    }*/
    //}
    //headerCheckBox.checked = true;
    var x = 1;
    var headerCheckBox = document.getElementById(headerCheckBoxID);
    var inputs = document.getElementById(clientID).getElementsByTagName("input");
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].type == "checkbox" && !inputs[i].checked && !inputs[i].disabled) {
            headerCheckBox.checked = false;
            return;
        }
    }

    /*if (x == 0) {
        
    headerCheckBox.checked = false;
    return;
    } else {
    headerCheckBox.checked = true;
    }*/
    headerCheckBox.checked = true;

}

function white_space(field) {
    field.value = field.value.replace(/\s+/g, " ");
}


function removeVN(str) {
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g, "-");
    /* tìm và thay thế các kí tự đặc biệt trong chuỗi sang kí tự - */
    str = str.replace(/-+-/g, "-"); //thay thế 2- thành 1- 
    str = str.replace(/^\-+|\-+$/g, "");
    //cắt bỏ ký tự - ở đầu và cuối chuỗi  
    return str;
}


function SelectCheckboxInsert(clientID, msg) {


    var gv = document.getElementById(clientID);
    var inputs = gv.getElementsByTagName('input');
    var isCheck = false;
    for (i = 1; i < inputs.length; i++) {
        if (inputs[i].checked && inputs[i].type == "checkbox") {
            isCheck = true;
        }
    }
    if (!isCheck) {
        alert(msg);
        return false;
    }
    var check = confirm("Bạn có chắc chắn muốn cập nhật điểm của học sinh");
    if (!check) {
        return false;
    }
    return true;
}

function DeleteMark(clientID, columnID) {

    var isCheck = false;

    var inputs = document.getElementById(clientID).getElementsByTagName("input");
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].type == "checkbox" && inputs[i].checked && !inputs[i].disabled) {
            isCheck = true;
            break;
        }
    }

    if (!isCheck) {
        alert("Chọn học sinh để xóa điểm .");
        return false;
    }
    var check = confirm("Bạn có chắc chắn muốn xóa?");
    if (!check) {
        return false;
    }
    return true;

}

// modified 25/01/2011

function isMoneyFormat(value) {
    var regExpMoney = new RegExp(/^[0-9\,]+$/);
    var result = value.match(regExpMoney);
    if (result == null)
        return false;
    else
        return true;
}


function isNumberFormat(value) {
    var regExpNumber = new RegExp(/^[0-9]+$/);
    var result = value.match(regExpNumber);
    if (result == null)
        return false;
    else
        return true;
}

// Ham kiem tra chuoi nhap vao co chua cac ky tu html khong
function isHtmlTagFormat(value) {
    var reDoubleTag = new RegExp(/<.+>.*<\/.+>/);
    var reSingleTag = new RegExp(/<.+\/?>?/);
    var result = value.match(reDoubleTag);
    var result2 = value.match(reSingleTag);
    if (result != null || result2 != null)
        return true;
    else
        return false;
}

//Ham kiem tra chuoi nhap vao co cac ky tu dac biet khong
function isValidInput(sText, bEmptyAllowed, bSpaceAllowed) {
    var re;
    if (bEmptyAllowed) {
        if (bSpaceAllowed)
            re = new RegExp(/^[a-zA-Z0-9_\-\s]*$/);
        else
            re = new RegExp(/^[a-zA-Z0-9_\-]*/);
    }
    else {
        if (bSpaceAllowed)
            re = new RegExp(/^[a-zA-Z0-9_\-\s]+$/);
        else
            re = new RegExp(/^[a-zA-Z0-9_\-]+$/);
    }
    return re.test(sText);

}


function isLinkFormat(value) {
    var regExpLink = '(((file|gopher|news|nntp|telnet|http|ftp|https|ftps|sftp)://)|(www\.))+(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(/[a-zA-Z0-9\&amp;%_\./-~-]*)?';
    var result = value.match(regExpLink);
    if (result == null)
        return false;
    else
        return true;
}

function isFormalCharacter(value) {
    var regExp = new RegExp(/^[a-zA-Z0-9\-\_]+$/);
    var result = value.match(regExp);
    if (result == null)
        return false;
    else
        return true;
}



function isDateFormat(value) {

    var date = value.substr(0, 10);

    // Regular expression used to check if date is in correct format 

    var pattern = new RegExp(/^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/);

    // kiem tra date

    if (date.match(pattern)) {

        var date_array = date.split('/');

        var day = date_array[0];

        // Attention! Javascript consider months in the range 0 - 11 

        var month = date_array[1] - 1;

        var year = date_array[2];

        // This instruction will create a date object 

        source_date = new Date(year, month, day);

        if (year != source_date.getFullYear()) {

            return false;

        }
        if (month != source_date.getMonth()) {
            return false;
        }

        if (day != source_date.getDate()) {
            return false;
        }

    } else {
        return false;

    }

    // kiem tra time

    if (value.length > 10) {

        var time = value.substr(11);

        if (time.length == 8) {

            var hour = time.substr(0, 2);

            var minute = time.substr(3, 2);

            var second = time.substr(6);



            if (parseInt(hour) > 23 && parseInt(hour) < 0) {

                return false;

            }

            if (parseInt(minute) > 60 && parseInt(minute) < 0) {

                return false;

            }

            if (parseInt(second) > 60 && parseInt(second) < 0) {

                return false;

            }

        } else {

            return false;

        }

    }
    return true;

}


//ham so sanh xem date1 co nho hon date2 ko? date
//pattern = "dd/MM/yyyy HH:mm:ss"
function compareDates(date1, date2) {

    var arrayDate1 = date1.split("/");

    var arrayDate2 = date2.split("/");

    var arrayTime1 = date1.split(":");
    var arrayTime2 = date2.split(":");

    var nam1 = parseFloat(arrayDate1[2]);

    var nam2 = parseFloat(arrayDate2[2]);

    var thang1 = parseFloat(arrayDate1[1]);

    var thang2 = parseFloat(arrayDate2[1]);

    var ngay1 = parseFloat(arrayDate1[0]);

    var ngay2 = parseFloat(arrayDate2[0]);


    var h1 = parseFloat(arrayTime1[0]);
    var h2 = parseFloat(arrayTime2[0]);

    var p1 = parseFloat(arrayTime1[1]);
    var p2 = parseFloat(arrayTime2[1]);

    var s1 = parseFloat(arrayTime1[2]);
    var s2 = parseFloat(arrayTime2[2]);


    if (nam1 < nam2) {
        return true;
    }
    if (nam1 == nam2) {
        if (thang1 < thang2) {
            return true;
        }
        if (thang1 == thang2) {
            if (ngay1 < ngay2) {
                return true;
            }
            if (ngay1 == ngay2) {
                if (h1 < h2) {
                    return true;
                }
                if (p1 < p2) {
                    return true;
                }
                if (s1 < s2) {
                    return true;
                }
            }
        }
    }
    return false;

}


function NoFirstZeroToString(txtMark) {
    var text = txtMark.value;
    if (text.length > 0) {
        while (text.substring(0, 1) == '0') {
            text = text.substring(1, text.length);
        }
    }
    txtMark.value = text;
    return txtMark.value;
}
function Trim(sString) {
    while (sString.substring(0, 1) == ' ') {
        sString = sString.substring(1, sString.length);
    }
    while (sString.substring(sString.length - 1, sString.length) == ' ') {
        sString = sString.substring(0, sString.length - 1);
    }
    return sString;
}


function AddMoveButtonEvent(GridID) {
    $("input[type=text]", GridID).unbind("keydown").keydown(function(event) {
        var td = $(this).parents("td");
        var tr = $(this).parents("tr");
        var index = $("td", tr).index(td);

        switch (event.keyCode.toString()) {
            case "13":
            case "40": //Move Down
                var ntr = tr.next("tr");
                if (ntr.is(":hidden"))
                    ntr = ntr.next("tr");
                var ntd = $("td:eq(" + index + ")", ntr);
                $("input[type=text]:first", ntd).focus(function
 () {
                    this.select();
                }).focus();

                return false;
            case "38": //Move Up
                var ntr = tr.prev("tr");
                if (ntr.is(":hidden"))
                    ntr = ntr.prev("tr");
                var ntd = $("td:eq(" + index + ")", ntr);
                $("input[type=text]:first", ntd).focus(function
 () {
                    this.select();
                }).focus();

                return false;
            case "39": //Move Right
                var ntd = $(td).next("td");
                $("input[type=text]:first", ntd).focus(function
 () {
                    this.select();
                }).focus();

                return false;
            case "37": //Move Left
                var ntd = $(td).prev("td");
                $("input[type=text]:first", ntd).focus(function
 () {
                    this.select();
                }).focus();

                return false;
        }

    });
}

// Baolvt
var ValidateString = {
    isFormalCharacter: function (value) {// khong cho phep dau /
        //var regExp = new RegExp(/^([^#${}\[\]@;.\-*/,:()?&<>%!])+$/);
        var regExp = new RegExp(/^([^#${}\[\]@;\-*/:()?&<>%!])+$/);
        var result = value.match(regExp);
        if (result == null)
            return false;
        else
            return true;
    },
    isFormalCharacter1: function (value) {// cho phep dau /
        //var regExp = new RegExp(/^([^#${}\[\]@;.\-*/,:()?&<>%!])+$/);
        var regExp = new RegExp(/^([^#${}\[\]@;\-*:()?&<>%!])+$/);
        var result = value.match(regExp);
        if (result == null)
            return false;
        else
            return true;
    },
    replaceHtmlTag: function(text) {
        text = text.replace(/<[^>]+>/ig, "");
        return text;
    }
};

var StringUtil = {
    replaceDecimalMask: function (element, hElement) {
        var value = hElement.val();
        var arr = new Array();
        arr = value.split('.');
        arr[0] = arr[0].replace('_', '');
        if (arr[1] != undefined)
        {
            arr[1] = arr[1].replace('_', '0');
        }
        else
        {
            arr[1] = "";
        }
        value = arr[0] + '.' + arr[1];
        console.log(value);
        $(element).val(value);
    }
};