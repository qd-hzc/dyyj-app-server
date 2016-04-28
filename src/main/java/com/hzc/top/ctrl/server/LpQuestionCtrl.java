package com.hzc.top.ctrl.server;

import com.hzc.top.model.LpCollection;
import com.hzc.top.model.SysUser;
import com.hzc.top.util.SessionUtil;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import com.hzc.top.vo.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuJY on 2015/4/29.
 * <p/>
 * 李沧干部法律法规学习系统
 * <p/>
 * 试题controller
 */
public class LpQuestionCtrl {

    /**
     * 学习页，答题卡列表
     * 返回questionList
     */
    public void getCardQuestionList() {
        int userId = SessionUtil.getUserId();
        List<AnswerCardVO> anserCardList = S.lpQuestionService().getAnserCardList(userId);
        W.writeJsonArray(anserCardList);
    }

    /**
     * 学习页，答题卡列表 --> 针对模拟测试
     * 返回questionList
     */
    public void getCardQuestionListForTest() {
        int userId = SessionUtil.getUserId();
        List<AnswerCardVO> anserCardList = S.lpQuestionService().getAnserCardListForTest(userId);
        W.writeJsonArray(anserCardList);
    }


    /**
     * 获取用户的question的详息
     * 包括：questionId，答题次数，错误次数，试题类型
     */
    public void getQuestionDetail() {
        Integer questionId = W.getInteger("questionId");
        if (questionId < 1) {
            throw new IllegalArgumentException("questionId is null");
        }
        QuestionVO question = S.lpQuestionService().getQuestion(questionId, SessionUtil.getUserId());
        W.writeJsonObject(question);
    }

    /**
     * 保存、删除用户错题
     */
    public void saveQuestionError() {
        UserAnswerVO userAnswerVO = W.packBean(UserAnswerVO.class);
        int userId = SessionUtil.getUserId();
        userAnswerVO.setUserId(userId);
        S.lpQuestionService().saveQuestionError(userAnswerVO);
        W.writeJson(true, "");
    }

    /**
     * 删除错题
     */
    public void deleteQuestionError() {
        Integer questionId = W.getInteger("questionId");
        int userId = SessionUtil.getUserId();
        LpCollection collection = new LpCollection();
        collection.setQuestionId(questionId);
        collection.setUserId(userId);
        collection.setType(2);
        S.lpQuestionService().unCollectQuestion(collection);
        W.writeJson(true, "");
    }

    /**
     * 保存、删除用户收藏
     */
    public void saveQuestionCollect() {
        Integer questionId = W.getInteger("questionId");
        Boolean deleted = W.getBoolean("deleted");//是否删除收藏，true 保存，false 删除
        if (questionId < 1) {
            throw new IllegalArgumentException("questionId is null");
        }
        int userId = SessionUtil.getUserId();
        LpCollection collection = new LpCollection();
        collection.setUserId(userId);
        collection.setQuestionId(questionId);
        collection.setType(1);
        collection.setDeleted(deleted);
        S.lpQuestionService().saveOrDeleteCollect(collection);
        W.writeJson(true, "");
    }

    /**
     * 获取用户收藏试题
     */
    public void getCollectQuestions() {
        int userId = SessionUtil.getUserId();
        List<AnswerCardVO> anserCardList = S.lpQuestionService().getCollectQuestions(userId);
        W.writeJsonArray(anserCardList);
    }

    /**
     * 获取用户的错题
     */
    public void getErrorQuestions() {
        int userId = SessionUtil.getUserId();
        List<AnswerCardVO> errorQuestions = S.lpQuestionService().getErrorQuestions(userId);
        W.writeJsonArray(errorQuestions);
    }

    /**
     * 查看所有试题
     * 分页加载试题
     */
    public void getAllQuestionForLimit() {
        Integer currentNum = W.getInteger("currentNum");
        Integer pageSize = W.getInteger("pageSize");
        List<QuestionVO> list = S.lpQuestionService().getAllQuestionsForLimit(currentNum, pageSize);
        W.writeJsonArray(list);
    }

    /**
     * app清空错题题库、收藏题库
     */
    public void clearCollectionApp() {
        Integer type = W.getInteger("type");
        String idCard = W.getString("idCard");
        if (StringUtils.isBlank(idCard) || type < 1) {
            throw new IllegalArgumentException("type or idCard is wrong");
        }
        SysUser userByPhone = S.appUserService().getUserByIdCard(idCard, SystemEnum.PUFA_LEXU.getValue());
        if (null == userByPhone) {
            throw new IllegalArgumentException("SysUser is null");
        }
        S.lpQuestionService().clearCollectionByUserId(userByPhone.getId(), type);
        W.writeJson(true, "");
    }

    /**
     * 清空所有单选题或者多选题或者判断题的错题或者收藏
     */
    public void clearCollectionAppForSection() {
        Integer type = W.getInteger("type");
        String idCard = W.getString("idCard");
        String sectionId = W.getString("sectionId");
        if (StringUtils.isBlank(idCard) || type < 1) {
            throw new IllegalArgumentException("type or idCard is wrong");
        }
        SysUser userByPhone = S.appUserService().getUserByIdCard(idCard, SystemEnum.PUFA_LEXU.getValue());
        if (null == userByPhone) {
            throw new IllegalArgumentException("SysUser is null");
        }
        S.lpQuestionService().clearCollectionByUserId(userByPhone.getId(), type, sectionId);
        W.writeJson(true, "");

    }

    /**
     * web清空错题题库、收藏题库
     */
    public void clearCollection() {
        Integer type = W.getInteger("type");
        if (type < 1) {
            throw new IllegalArgumentException("type is wrong");
        }
        int userId = SessionUtil.getUserId();
        S.lpQuestionService().clearCollectionByUserId(userId, type);
        W.writeJson(true, "");
    }

    /**
     * 根据试题类型获取试题
     */
    public void listQuestions() {
        String type = W.getString("type");
        if (StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("type is null");
        }
        int userId = SessionUtil.getUserId();
        List<AnswerCardVO> list = S.lpQuestionService().getQuestionByType(userId, type);
        W.writeJsonArray(list);
    }


    /**
     * 优化版本的查询答题卡题号
     */
    public void listQuestionsOptimize() {
        String type = W.getString("type");
        int userId = SessionUtil.getUserId();

        Object[][] cardNumbers = S.lpQuestionService().getQuestionByTypeOptimize(userId, type);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("userId", userId);
        map.put("categoryCode", LpQuestionTypeEnum.get(type));
        map.put("cardNumbers", cardNumbers);

        W.writeJsonObject(map);
    }

    /**
     * 返回用户需要打印的收藏题、错题
     */
    public void getQuestionsForPrint() {
        Integer type = W.getInteger("type");
        if (type < 1) {
            throw new IllegalArgumentException("type is wrong");
        }
        int userId = SessionUtil.getUserId();
        List<QuestionVO> questionsByType = S.lpQuestionService().getQuestionsByTypeForPrint(userId, type);
        W.writeJsonArray(questionsByType);
    }

    /**
     * 保存用户错题
     */
    public void saveErrors(){
        String data = W.getString("data");
        if (StringUtils.isBlank(data)) {
            throw new IllegalArgumentException("data is null");
        }
        int userId = SessionUtil.getUserId();
        S.lpQuestionService().saveErrors(data,userId);
        W.writeJson(true,"");
    }
}
