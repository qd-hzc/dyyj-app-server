<%--
  Created by IntelliJ IDEA.
  Date: 2015/4/29
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include_header.jsp" %>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->

            <div class="row">
                <%--style="border: 2px solid red;"--%>
                <div class="col-sm-3 col-md-3">
                    <div class="widget-box">

                        <div class="widget-header">
                            <h4 class="smaller">
                                答题卡
                            </h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main my-content-height my-content-bgcolor"
                                 style="background:none!important;min-height: 510px !important;">

                                <div class="tabbable" style="margin-top: 3px;">
                                    <ul class="nav nav-tabs my-nav-tabs">

                                        <li class="active">
                                            <a data-toggle="tab" href="#study-panduan-tab">
                                                判断题
                                            </a>
                                        </li>

                                        <li style="display: none;">
                                            <a data-toggle="tab" href="#study-danxuan-tab">
                                                单选题
                                            </a>
                                        </li>


                                        <li style="display: none;">
                                            <a data-toggle="tab" href="#study-duoxuan-tab">
                                                多选题
                                            </a>
                                        </li>
                                    </ul>

                                    <div class="tab-content" id="my-xuexi-tab-content" style="padding: 0px 0px;">
                                        <div id="study-panduan-tab" class="tab-pane in active">
                                            <div id="xuexi-panduanti-id" style="text-align: left;">
                                                <%--************************* CONTENT ******************************--%>
                                            </div>
                                            <hr>
                                            <div class="my-btn-xuexi-top">
                                                <button class="btn btn-xs btn-info my-btn-xuexi-fenye-pre" style="min-width: 60px;">
                                                    <%--<i class="icon-arrow-left icon-on-left"></i>--%>
                                                    上50题
                                                </button>
                                                    <span style="margin:0px 10px !important;font-size: 12px !important">
                                                        第&nbsp;<input type="text" style="width: 24px;"
                                                                      onkeyup="PANDUANTI_QUESTION.changePage(this);"/>&nbsp;页，共<span></span>&nbsp;页
                                                    </span>
                                                <button class="btn btn-xs btn-info  my-btn-xuexi-fenye-next" style="min-width: 60px;">
                                                    下50题
                                                    <%--<i class="icon-arrow-right icon-on-right"></i>--%>
                                                </button>
                                            </div>
                                        </div>
                                        <div id="study-danxuan-tab" class="tab-pane">
                                            <div id="xuexi-danxuanti-id">
                                                <%--************************* CONTENT ******************************--%>
                                            </div>
                                            <hr>
                                            <div class="my-btn-xuexi-top">
                                                <ul style="float: left;">
                                                    <li>
                                                        <button class="btn btn-xs btn-info  my-btn-xuexi-fenye-pre"
                                                                style="text-align: left;">
                                                            <i class="icon-arrow-left icon-on-left"></i>
                                                            上50题
                                                        </button>
                                                    </li>
                                                    <li><span
                                                            style="margin:0px 10px !important;font-size: 15px !important;text-align: center;!important;">
                                                    第<span></span>页，共<span></span>页
                                                </span></li>
                                                    <li>
                                                        <button class="btn btn-xs btn-info  my-btn-xuexi-fenye-next"
                                                                style="text-align: right;">
                                                            下50题
                                                            <i class="icon-arrow-right icon-on-right"></i>
                                                        </button>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>

                                        <div id="study-duoxuan-tab" class="tab-pane">
                                            <div id="xuexi-duoxuanti-id">
                                                <%--************************* CONTENT ******************************--%>
                                            </div>
                                            <hr>
                                            <div class="my-btn-xuexi-top">
                                                <button class="btn btn-xs btn-info  my-btn-xuexi-fenye-pre">
                                                    <i class="icon-arrow-left icon-on-left"></i>
                                                    上50题
                                                </button>
                                                <span style="margin:0px 10px !important;font-size: 15px !important">
                                                    第<span></span>页，共<span></span>页
                                                </span>
                                                <button class="btn btn-xs btn-info  my-btn-xuexi-fenye-next">
                                                    下50题
                                                    <i class="icon-arrow-right icon-on-right"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="; font-size: 13px; position: absolute; bottom: 2px; margin: 0px; padding: 4px;width: 90%;">
                                        <hr style="margin-bottom: 0px;padding-bottom: 0px;">
                                        <a class="btn no-radius my-btn-xuexi-tihao my-btn-tihao-answer my-current-question my-btn-tihao-cu"
                                           style="width: 12px!important;border: 1px solid #FCC584 !important;height: 12px;cursor: default;"></a>&nbsp;当前题&nbsp;&nbsp;
                                        <a class="btn no-radius my-btn-xuexi-tihao my-btn-tihao-answer my-btn-tihao-do"
                                           style="width: 12px!important;height: 12px;cursor: default;"></a>&nbsp;已作答&nbsp;&nbsp;
                                        <a class="btn btn-app no-radius my-btn-tihao my-btn-xuexi-tihao my-btn-tihao-un"
                                           style="width: 12px!important;height: 12px;cursor: default;">
                                        </a>&nbsp;未作答&nbsp;&nbsp;&nbsp;&nbsp;
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-sm-7 col-md-7" style="padding-left: 0px;padding-right: 0px;">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="smaller">
                                答题区
                            </h4>
                        </div>

                        <div id="xuexi-question-detail-id" class="widget-body"
                             style="padding-bottom: 30px !important;background: #d0f6e3;">
                            <%--***************************** CONTENT ******************************--%>
                        </div>
                    </div>

                </div>

                <div class="col-sm-2 col-md-2">
                    <div class="widget-box" style="background:none!important;">
                        <div class="widget-header">
                            <h4 class="smaller">
                                说明
                            </h4>
                        </div>

                        <div class="widget-body my-content-bgcolor"
                             style="padding-bottom: 30px !important;background: none!important;">
                            <div class="my-question-shuoming " style="margin-left: -10px;">
                                <ul>
                                    <li>右侧为答题区，点击“上一题”“下一题”查看题目，点击“查看答案”显示正确答案。</li>
                                    <br/>
                                    <li>点击“收藏本题”可以将题目放置到“收藏题库”中，以方便查看。
                                    </li>
                                    <br/>
                                    <li>同一题目连续答错3次，系统将自动添加到“收藏题库”中。</li>
                                    <br/>
                                    <li>答题时，系统会自动将做错的题目放入到“错题题库”中。</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /row -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

        <script id="template-xuexi-question-card-id" type="text/html">
            <a id="xuexi-card-{questionId}" href="javascript:;" data-question-id="{questionId}"
               class="btn btn-app no-radius my-btn-tihao my-btn-xuexi-tihao {lightClass}">
                {seq}
            </a>
        </script>
        <script id="template-xuexi-question-detail-empty-id" type="text/html">
            <div id="xuexi-{questionId}" data-collection-type="{collectionType}" data-question-id="{questionId}"
                 class="widget-main my-content-height" style="padding: 20px;display: none;">
            </div>
        </script>
        <script id="template-xuexi-question-detail-up-id" type="text/html">
            <div data-collection-type="{collectionType}">
        <span class="my-span-question-title">
            <span class="my-question" data-question-id="{questionId}" style="display: none;">
                第{seq}题
            </span>
            <span class="my-title-shuoming">判断题：请选择对或错</span>
        </span>
        <span id="xuexi-question-time-{questionId}" class="label label-lg arrowed-in pull-right my-label-info"
              style="display: none;">已做<span>{answerTime}</span>次
            错了<span>{collectTime}</span>次
        </span>
            </div>
            <div>

                <p class="my-p-question">
           <span>
             <span style="font-weight: 600;">{seq}、</span>&nbsp;{name}
            </span>
                </p>
            </div>
            <div class="control-group">

        </script>
        <script id="template-xuexi-question-detail-down-id" type="text/html">
            </div>
            <div class="row" style=" bottom: 30px;float: right;position: absolute;right: 41px;">
                <div class="col-xs-1 my-col-xs">
                    <button class="btn my-btn-qiehuanti  btn-info my-btn-shoucang-benti"
                            data-collection-type="{collectionType}"
                            data-question-id="{questionId}">
                        {collectionText}
                    </button>
                </div>
                <div class="col-xs-1 my-col-xs">
                    <button class="btn btn-info my-btn-qiehuanti my-btn-xuexi-question-prev "
                            data-question-id="{questionId}">
                        上一题
                    </button>
                </div>
                <div class="col-xs-2 my-col-xs" style="display: {isDisplay};">
                    <button class="btn  btn-info my-btn-qiehuanti my-btn-xuexi-daan "
                            data-question-id="{questionId}" title="请先答题">
                        <%--onclick="eventShowNextPrev(this);">--%>
                        查看答案
                    </button>
                </div>
                <div class="col-xs-1 my-col-xs">
                    <button class="btn  btn-info my-btn-qiehuanti my-btn-xuexi-question-next"
                            data-question-id="{questionId}">
                        下一题
                    </button>
                </div>
            </div>
        </script>
        <script id="template-xuexi-option-id" type="text/html">
            <label class="radio my-radio" data-key="{key}" data-label="{label}">
                <input type="{type}" class="ace" name="xuexi-option-{questionId}" onclick="eventBtnChange(this);">
            <span class="lbl">
                <span>
                    {label}、
                </span>
                <span style="font-size: 18px !important;">
                    {name}
                </span>
            </span>
            </label>
        </script>
    </div>
    <script src="${basePath}assets/system/pufa/js/panduanti.js"></script>
    <%@include file="include_footer.jsp" %>
