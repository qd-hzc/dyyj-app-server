var rolePower = {
    ALL: 900,
    UNIT: 500,
    DEPARTMENT: 400,
    EMPLOYEE: 110,
    WEB: 100
};
/**
 * Created by YinBin on 14-7-1.
 */
    //全局ajax  若超时  则退出系统
    //全局ajax  若超时  则退出系统
//$.ajaxSetup({
//    type: 'POST',
//    complete: function (xhr, status) {
//        var sessionStatus = xhr.getResponseHeader('status');
//        if (sessionStatus == 'timeout') { //session超时处理
//            top.location.href = 'login.jsp?headerlog=0&lostSession=true';
//            return;
//        }
//        try {//异常处理
//            if (status != 'success') {
//                Common.error('系统发生异常，请联系管理员！');
//                if (typeof console != 'undefined' && console != null) {
//                    console.error(xhr.responseText);
//                }
//                return;
//            }
//        } catch (e) {
//        }
//    }
//});
function CommonClass() {

}
CommonClass.prototype.confirm=function(content,fn){
    bootbox.confirm(content, fn);
};
/**
 * 显示异常消息的方法
 * @param html
 *
 *
 */
CommonClass.prototype.error = function (html, where, bool) {
    $.toaster({priority: 'danger', title: '<span class="glyphicon glyphicon-remove"></span>', message: html});
    return;
    this._showMsg(where, html, "red", bool);

};
CommonClass.prototype.warn = function (html, where, bool) {
    $.toaster({priority: 'danger', title: '<span class="glyphicon glyphicon-remove"></span>', message: html});
    //$.toaster({priority: 'warning', title: '<span class="glyphicon glyphicon-remove"></span>', message: html});
    return;
    this._showMsg(where, html, 'yellow', bool);
};
CommonClass.prototype.info = function (html, where, bool) {
    $.toaster({priority: 'info', title: '<i class="icon-ok"></i>', message: html});
    //$.toaster({priority: 'info', title: '', message: html});
    return;
    this._showMsg(where, html, "green", bool);
};
CommonClass.prototype.success = function (html, where, bool) {
    $.toaster({priority: 'info', title: '<span class="glyphicon glyphicon-ok"></span>', message: html});
    //$.toaster({priority: 'success', title: '<span class="glyphicon glyphicon-ok"></span>', message: html});
    return;
    this._showMsg(where, html, "blue", bool);
};
CommonClass.prototype._showMsg = function (where, html, color, bool) {
    var unique_id = $.gritter.add({
        title: '提示信息',
        text: html,
        sticky: true,
        time: ''
    });
    return;
    var self = this, c = color || "red";
    if (self.interval) clearInterval(self.interval);
    if (self.interTimeout) clearTimeout(self.interTimeout);
    if (self.timeout) clearTimeout(self.timeout);
    if (!$("#error_msg_span_id").size()) {
        this.div_se = $("<span id='error_msg_span_id' style='margin-left: 100px;'></span>");
        if (!where || !where.size())where = $("#lblTitle");
        where.after(this.div_se);
    }
//    if (!this.oldHtml_se)
//        this.oldHtml_se = this.div_se.html();
    this.span_se = $("<span style='font-size:small;font-weight:bold;letter-spacing:1px;font-family: \@宋体;color:#2a3744;'></span>");
    this.x_se = this.div_se.empty().append(/*$("<center style='display: inline-block'></center>").append*/(this.span_se.append(html)));
    this.interval = setInterval(function () {
        self.flag ? (self.span_se.css("color", "#2a3744") && (self.flag = false)) : (self.span_se.css("color", c) && (self.flag = true))
    }, 150);
    this.interTimeout = setTimeout(function () {
        clearInterval(self.interval);
        self.span_se.css("color", c);
    }, 1500);
    if (!bool)
        this.timeout = setTimeout(function () {
            self.x_se.children().fadeOut(5000, function () {
                $(this).end().empty();
                self.div_se.remove();
            }) | clearInterval(self.interval) | clearTimeout(self.interTimeout) | clearTimeout(self.timeout);// | self.div_se.html(self.oldHtml_se);
        }, 3000);
};

/**
 * 显示异常消息的方法
 * @param html
 *
 *
 */
CommonClass.prototype.error2 = function (html, where, bool) {
    this._showMsg2(where, html, "red", bool);
};
CommonClass.prototype.info2 = function (html, where, bool) {
    this._showMsg2(where, html, "green", bool);
};

CommonClass.prototype._showMsg2 = function (where, html, color, bool) {
    var self = this, c = color || "red";
    if (self.interval) clearInterval(self.interval);
    if (self.interTimeout) clearTimeout(self.interTimeout);
    if (self.timeout) clearTimeout(self.timeout);
    if (!$("#error_msg_span_id").size()) {
        this.div_se = $("<span id='error_msg_span_id' style='margin-left: 100px;'></span>");
        if (!where || !where.size())where = $("#lblTitle");
        where.after(this.div_se);
    }
//    if (!this.oldHtml_se)
//        this.oldHtml_se = this.div_se.html();
    this.span_se = $("<span style='font-size:small;font-weight:bold;letter-spacing:1px;font-family: \@宋体;color:#2a3744;'></span>");
    this.x_se = this.div_se.empty().append(/*$("<center style='display: inline-block'></center>").append*/(this.span_se.append(html)));
    this.interval = setInterval(function () {
        self.flag ? (self.span_se.css("color", "#2a3744") && (self.flag = false)) : (self.span_se.css("color", c) && (self.flag = true))
    }, 150);
    this.interTimeout = setTimeout(function () {
        clearInterval(self.interval);
        self.span_se.css("color", c);
    }, 1500);
    if (!bool)
        this.timeout = setTimeout(function () {
            self.x_se.children().fadeOut(5000, function () {
                $(this).end().empty();
                self.div_se.remove();
            }) | clearInterval(self.interval) | clearTimeout(self.interTimeout) | clearTimeout(self.timeout);// | self.div_se.html(self.oldHtml_se);
        }, 3000);
};

CommonClass.prototype.uploadValidate = function () {

};

CommonClass.prototype.template = function (templateStr, data) {
    return templateStr.replace(/\{([\w\.]*)\}/g, function (str, key) {
        var keys = key.split("."), v = data[keys.shift()];
        for (var i = 0, l = keys.length; i < l; i++)
            v = v[keys[i]];
        return (typeof v !== "undefined" && v !== null) ? v : "";
    });
};

/**
 * 在当前window窗口中打开页面
 */
CommonClass.prototype.openAtFrame = function (openUrl, backUrl) {
//    alert(backUrl || window.location.href);
//    $(document.body).append('<form action="' + openUrl + '" method="get" target="_self">' +
//        '<input type="hidden" name="backUrl" value="' + (backUrl || window.location.href ) + '"' +
//        '</form>');
//    $('form:last').submit();
//    window.open(openUrl + (openUrl.indexOf('?') != -1 ? "" : "?" ) + "&backUrl=" + decodeURI(backUrl));
    backUrl = backUrl || window.location.href;
    window.location.href = openUrl + (openUrl.indexOf('?') != -1 ? "" : "?" ) + "&backUrl=" + decodeURI(backUrl);
};

CommonClass.prototype.getSession = function (key) {
    var result = {};
    $.ajax({
        type: 'POST',
        url: 'SessionCtrl.get.do',
        data: 'key=' + key,
        async: false,
        success: function (msg) {
            result = msg;
        }
    });
    return result;
};

CommonClass.prototype.setSession = function (key, value) {
    var result = {};
    $.ajax({
        type: 'POST',
        url: 'SessionCtrl.set.do',
        data: 'key=' + key + "&value=" + value,
        async: false,
        success: function (msg) {
            result = msg;
        }
    });
    return result;
};

CommonClass.prototype.checkSessionAndToLoginJsp = function () {
    try {
        var result = Common.getSession('LoginEmployeeID');
        if (result) {
            if (!result.success) {
                top.window.location.href = basePath + 'login.jsp?headerlog=0&lostSession=true'
            }
        }
    } catch (e) {
    }
};


var Common = new CommonClass();


function CourseClass() {

}
/**
 * 用iframe方式打开课件。
 * @param basePath
 * @param courseUrl
 * @param backUrl
 */
CourseClass.prototype.openInFrame = function (basePath, courseUrl, courseId, contentId, userId, loginAPPID) {
//    $(document.body).append('<form action=\'' + basePath + 'inner/hudong.jsp\' method=\'post\' target=\'_self\'><input type=\'hidden\' name=\'courseUrl\' value=\'' + courseUrl +
//        '\'><input type=\'hidden\' name=\'backUrl\' value=\'' + (top.window.location.href) + '\'>' +
//        '<input type=\'hidden\' name=\'courseId\' value=\'' + courseId + '\'>' +
//        '<input type=\'hidden\' name=\'contentId\' value=\'' + contentId + '\'>' +
//        '<input type=\'hidden\' name=\'userId\' value=\'' + userId + '\'>' +
//        '<input type=\'hidden\' name=\'loginAPPID\' value=\'' + loginAPPID + '\'>' +
//        '</form>');
//    $('form:last').submit();
//    window.open(courseUrl, '课件', 'top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no', true);

    var Main = {
        init: function () {
            var self = this;
            return function () {
                self.msgDiv = $('#lblTitle');
                self.iframe = $('#iframeId');
                self.back = $('#backId');
                self.eventBack();
                self.verify();
            }
        },
        getApi: function () {
            if (this.api)return this.api;
            var win = window, findAttempts = 0, findAttemptLimit = 500;
            while ((!win.FmvmApi) && (win.parent) && (win.parent != win) && (findAttempts <= findAttemptLimit)) {
                findAttempts++;
                win = win.parent;
            }
            this.api = win.FmvmApi;
            return this.api;
        },
        verify: function () {
            var self = this;
            var api = self.getApi();
            api.basePath = '';

            if (!api) {
                alert('程序bug：没有配置FmvmApi.js');
                return;
            }
            api.putData('dialog', this);
            api.putData('courseId', courseId);

            function success() {
//                    self.setMsg('指纹验证成功');
                self.showCourse();
            }

            function failed() {
                self.setMsg('验证失败，请再次录入指纹');
                self.verify();
            }

            function error(code, msg, e) {
                alert(msg);
            }

            api.putData('Msg', function () {
                self.setMsg('请录入指纹（录入指纹前，请确保安装了控件、插入指纹验证鼠标、并点击一下此课件窗口）');
            });
            api.verify(success, failed, error, false);
        },
        setMsg: function (msg) {
            alert(msg);
        },
        showCourse: function () {
//            document.getElementById("iframeId").src = courseUrl;
            var bu = window.location.href;
            Common.LocalStorage.setItem('backUrl', bu)
            if (courseUrl.indexOf("?") > -1) {
                courseUrl = courseUrl + "&backUrl=" + bu;
            } else {
                courseUrl = courseUrl + "?backUrl=" + bu;
            }
            window.location.href = courseUrl;
//        document.getElementById("iframeId").src = '14022407371202967367/kb_index.html';
        }
    };

    Main.verify();
//    window.location.href = courseUrl;
};

/**
 * datatable ajax 表单加分页 插件 的 构造函数
 *
 *
 *
 * @constructor
 */
function DefaultDataTable(url, columns, tableId, searchFormId) {
    this.tableId = tableId;
    $(tableId).addClass("table table-striped table-bordered table-hover");
    this.url = url;
    this.columns = columns;
    this.searchFormId = searchFormId;
    this._table = this._dataTable(this.tableId, this.url, this.columns);
    var self = this;
    if (searchFormId) {// 最简单的方式
        var form = $(searchFormId);
        form.children("table").addClass("searchTable");//自动应用查询表单的样式
        var formEle = form.get(0);
        var oldSubmit = formEle.onsubmit;
        form.submit(function (evt) {
            evt.preventDefault();
            evt.stopPropagation();
            self.search(searchFormId);
            if (typeof oldSubmit == 'function') {
                oldSubmit();
            }
            return false;
        });
    }
}

/**
 * 获取jquery的datatable对象后，可以自定义一些东东了，请参照datatable文档
 * @returns {*}
 */
DefaultDataTable.prototype.getDataTable = function () {
    return this._table;
};

/**
 * 应用指定的查询表单数据到此datatable上
 * @param _formId
 * @returns {boolean}
 */
DefaultDataTable.prototype.search = function (_formId) {
    var serialize = $(_formId || this.searchFormId).serialize();
    $(this.tableId).dataTable().fnReloadAjax(this.url + '?' + serialize);//重新加载
    return false;
};

/**
 * 私有方法，不要调用
 * @param tableId
 * @param url
 * @param columns
 * @private
 */
DefaultDataTable.prototype._dataTable = function (tableId, url, columns) {
    var table = $(tableId);
    table.dataTable({
        "sPaginationType": "full_numbers",//分页类型--simple(只有上一页，下一页),simple_numbers（上一页，下一页和数字显示）,full（首页，尾页，上一页和下一页）,full_numbers（首页，尾页，上一页，下一页和数字显示）
        "bServerSide": true,//是否从服务器获取数据
        "processing": true,//是否显示获取数据时的进度信息，譬如获取数据中...
        "bStateSave": false,//是否保存插件信息
        "bLengthChange": false,//是否可以改变每页显示数量
        "iDisplayLength": 10,//初始化显示数量--每页显示数据数目
        "bDeferRender": true,//延期渲染
        "aLengthMenu": [15, 30, 50, 100],
        "sAjaxDataProp": "dataList",//默认为"aaData",o后台返回数据字段
        "bAutoWidth": true,//是否宽度自适应
        "bFilter": false,//是否启动搜索过滤
        "bSort": false,//是否启动各个字段排序
        "sAjaxSource": url,//服务器URL
        "aoColumns": columns,
        "language": {//页面显示自定义信息
            "lengthMenu": "每页显示 _MENU_ 条数据",
            "zeroRecords": "没有数据",
            "info": "显示 _START_ 到 _END_ 条数据，总共 _TOTAL_ 条数据",
            //"infoEmpty": "没有数据",
            "infoFiltered": "(从 _MAX_ 条数据中检索)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            },
            "sSearch": "搜索",
            "sProcessing": "数据加载中..."
        },
        "searching": false,//是否显示右上角的搜索框
        "ordering": false//是否可以排序
    });
//    table.find('tbody').on('click', 'tr', function () {//点击一行时显示蓝色
//        if ($(this).hasClass('selected')) {
//            $(this).removeClass('selected');
//        }
//        else {
//            table.dataTable().$('tr.selected').removeClass('selected');
//            $(this).addClass('selected');
//        }
//    });
};


var Course = new CourseClass();


