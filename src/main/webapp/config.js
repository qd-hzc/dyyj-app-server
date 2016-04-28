/**
 * Created by yinbin on 2015/4/1.
 */

var Routers = {
    user: {},
    // 托普统考英语APP
    topEnglish: {

        //使用授权管理 模块
        empower: {
            searchList: 'UserCtrl.searchUserForEmpower.do',
            updateAvailableTime: 'UserCtrl.updateTime.do'
        }
    },
    // 李沧普法APP
    pufa: {
        user: {
            searchList: 'UserCtrl.searchUserForPufaLexue.do'
        },
        // 学习页
        study: {
            //获取学习页试题答题卡的questionList
            getQuestionCardList: 'LpQuestionCtrl.getCardQuestionList.do',
            //获取用户的question的详细信息
            getQuestionDetail: 'LpQuestionCtrl.getQuestionDetail.do',
            //保存、删除用户错题
            saveQuestionError: 'LpQuestionCtrl.saveQuestionError.do',
            //保存、删除用户收藏
            saveQuestionCollect: 'LpQuestionCtrl.saveQuestionCollect.do',
            //获取模拟页试题答题卡的questionList （针对模拟测试）
            getCardQuestionListForTest: 'LpQuestionCtrl.getCardQuestionListForTest.do',
            //根据试题类型获取试题
            getQuestions: 'LpQuestionCtrl.listQuestions.do',
            //优化答题卡的查询
            listQuestionsOptimize: 'LpQuestionCtrl.listQuestionsOptimize.do'
        },
        //模拟页
        test: {},
        //收藏本
        collect: {
            //获取收藏薄试题
            getCollectQuestions: 'LpQuestionCtrl.getCollectQuestions.do',
            //清空收藏题库、错题题库
            clearCollect: 'LpQuestionCtrl.clearCollection.do',
            //获取用户需要打印的所有的收藏题或错题
            getQuestionsForPrint:'LpQuestionCtrl.getQuestionsForPrint.do'
        },
        //错题本
        error: {
            //获取用户的错题
            getErrorQuestions: 'LpQuestionCtrl.getErrorQuestions.do',
            //删除用户错题
            deleteErrorQuestion: 'LpQuestionCtrl.deleteQuestionError.do',
            //保存用户错题
            saveErrors:'LpQuestionCtrl.saveErrors.do'
        },
        //所有题
        all: {
            //分页加载试题
            getAllQuestionForLimit: 'LpQuestionCtrl.getAllQuestionForLimit.do'
        }
    },
    // 托普掌上课堂APP
    accounting: {
        enrollManage: {
            // 报名接口
            ajaxEnroll: 'KjAccountingEnrollCtrl.ajaxEnroll.do',
            searchList: 'KjAccountingEnrollCtrl.search.do'
        }
    },
    //托普报名服务系统
    baoming: {
        userManage: {
            list: 'UserCtrl.searchUserForBaoMing.do'
        },
        integralMange: {
            list: ''
        }
    }
    //,
//    导出excel
//    export: {
//        excel: {
//            ztk: 'LpExportExcelCtrl.exportForAllQuestion.do'
//        }
//    }
};

