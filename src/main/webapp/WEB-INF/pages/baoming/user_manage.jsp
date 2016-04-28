<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../common_jstl.jsp" %>
<style type="text/css">
    .subject {
        color: #0092ef;
        font-weight: 600;
        font-size: 13px;
    }
</style>


<form id="enrollSearch" style="display: none;">
    <table class="searchTable">
        <tr>
            <td><span>姓名：</span></td>
            <td><input name="name" type="text"/></td>
            <td><span>状态：</span></td>
            <td>
                <select id="status" name="status">
                    <option value="">请选择</option>
                    <option value="1">缴费成功</option>
                    <option value="2">缴费失败或未缴费</option>
                </select>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><span>开始时间：</span></td>
            <td>
                <input type="text" id="startTime" name="startTime" onchange="javascript:count_paper_minute()"
                       readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                       style="width: 180px;margin-top: 5px;"/>
            </td>
            <td><span>结束时间：</span></td>
            <td>
                <input type="text" id="endTime" name="endTime" onchange="javascript:count_paper_minute()"
                       readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                       style="width: 180px;margin-top: 5px;"/>
            </td>
            <td style="width:227px">
                <button type="submit" class="btn btn-purple btn-sm" id="searchs"
                        name="searchs"><i class="icon-search icon-on-left bigger-110"></i>&nbsp;查询
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button type="reset" class="btn btn-sm btn-light" id="cleanSearch"
                        name="cleanSearch">
                    <i class="icon-on-left  icon-refresh  bigger-110"></i>&nbsp;重置
                </button>
            </td>
            <td></td>
        </tr>
    </table>
</form>
<table id="example" style="font-size: 14px;">
</table>

<script type="text/javascript">
    /*
     ajax提交form返回结果
     var __ret = ajaxFormStep($, form1Serialize, ajaxUrl);
     var succ = __ret.succ;成功、失败（true/false）
     var mess = __ret.mess;成功、失败信息
     */
    function ajaxFormStep($, formData, ajaxUrl) {
        var succ = false;
        var mess = '';
        $.ajax({
            url: ajaxUrl,
            method: "post",
            async: false,
            data: formData,
            success: function (data) {
                succ = data.success;
                mess = data.message;
            },
            error: function () {
                mess = '程序错误';
            }
        });
        return {succ: succ, mess: mess};
    }
    var enrollmentManagement = {
        daibaoming: function () {
            $(document.body).undelegate('click').delegate(".skip-validation", "click", function () {
                var $yes = this.checked;
                var sid = $(this).attr("data-value");
                var sstatus = 4;
                if ($yes) {
                    sstatus = 3;
                }
                var result = ajaxFormStep($, {status: sstatus, id: sid}, Routers.accounting.enrollManage.ajaxEnroll);
            })
        }
    }
    var columns = [//列定义

        {
            "sTitle": "姓名",
            "mData": 'name' //对于不需要额外处理的直接，写bean中对应的属性名称即可，不用写render一个废话函数。
        },
        {
            "sTitle": "身份证号",
            "mData": 'code'
        },
        {
            "sTitle": "性别",
            "render": function (data, type, row) {
                if (row.sex == 1) {
                    return "男";
                } else {
                    return "女";
                }
            }
        },
        {
            "sTitle": "民族",
            "render": function (data, type, row) {
                if (row.nation == 1) {
                    return "汉";
                } else {
                    return "其他";
                }
            }
        },
        {
            "sTitle": "电话",
            "mData": 'phone'
        },
        {
            "sTitle": "报名时间",
            "mData": "createTime"

        },
        {
            "sTitle": "考试科目",
            "render": function (data, type, row) {
                var subjects = row.examSubject;
                var subjectList = subjects.split(",");
                var subjectString = '';
                for (var i = 0; i < subjectList.length; i++) {
                    if (subjectList[i] == 1) {
                        subjectString += '[<span class="subject">财经法规与会计职业道德</span>]';
                    } else if (subjectList[i] == 2) {
                        subjectString += '[<span class="subject">会计基础</span>]';
                    } else {
                        subjectString += '[<span class="subject">初级会计电算化</span>]';
                    }
                }
                return subjectString;
            }
        },
        {
            "sTitle": "状态",
            "render": function (data, type, row) {
                if (row.paymentOk == 1) {
                    return "缴费成功";
                } else {
                    return "<span style='color: red'>缴费失败或未缴费</span>";
                }
            }
        }
    ];

    // 核心在这里，会自动拥有，表单回车查询特性。
    //datatable的js和样式不再需要引入，此方法会自动判断和引入。
    //仅仅需要指定url、列的显示样式、表格id，form id即可。
    new DefaultDataTable(Routers.baoming.userManage.list, columns, '#example', '#enrollSearch');
    enrollmentManagement.daibaoming();
</script>
