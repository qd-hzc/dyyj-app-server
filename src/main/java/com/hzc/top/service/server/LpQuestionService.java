package com.hzc.top.service.server;

import com.hzc.framework.ssh.service.TrancationType;
import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.LpAnswer;
import com.hzc.top.model.LpCollection;
import com.hzc.top.model.LpOption;
import com.hzc.top.model.LpQuestion;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.vo.AnswerCardVO;
import com.hzc.top.vo.QuestionVO;
import com.hzc.top.vo.UserAnswerVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by LiuJY on 2015/4/29.
 * <p/>
 * 李沧普法系统
 * <p/>
 * 试题service
 */
@Transaction(jdbc = TrancationType.CLOSE)
public class LpQuestionService {

    /**
     * 根据userId
     * 获取学习页答题卡内容
     * 包括：questionId，答题次数，错误次数，试题类型，是否收藏
     *
     * @param userId
     * @return
     */
    public List<AnswerCardVO> getAnserCardList(int userId) {
        return D.lpQuestionMapper().selectForAnswerCardList(userId);
    }

    /**
     * 根据userId
     * 获取模拟页答题卡内容
     * 包括：questionId，答题次数，错误次数，试题类型，是否收藏
     *
     * @param userId
     * @return
     */
    public List<AnswerCardVO> getAnserCardListForTest(int userId) {
        //    模拟题中答题卡数据的缓存
        List<AnswerCardVO> test_panduan_card = new ArrayList<AnswerCardVO>();
        List<AnswerCardVO> test_danxuan_card = new ArrayList<AnswerCardVO>();
        List<AnswerCardVO> test_duoxuan_card = new ArrayList<AnswerCardVO>();

        List<AnswerCardVO> acvList = D.lpQuestionMapper().selectForAnswerCardList(userId);
        for (AnswerCardVO vo : acvList) {
            if ("判断题".equals(vo.getType())) {
                test_panduan_card.add(vo);
            } else if ("单选题".equals(vo.getType())) {
                test_danxuan_card.add(vo);
            } else if ("多选题".equals(vo.getType())) {
                test_duoxuan_card.add(vo);
            }
        }

        Collections.shuffle(test_panduan_card);
        Collections.shuffle(test_danxuan_card);
        Collections.shuffle(test_duoxuan_card);

        List<AnswerCardVO> acVos = new ArrayList<AnswerCardVO>();
        acVos.addAll(test_panduan_card.subList(0, 30));
        acVos.addAll(test_danxuan_card.subList(0, 30));
        acVos.addAll(test_duoxuan_card.subList(0, 20));

        return acVos;
    }

    /**
     * 根据questionId查询用户的试题
     * 包括：questionId，答题次数，错误次数，试题类型，试题信息
     *
     * @param questionId
     * @param userId
     * @return
     */
    public QuestionVO getQuestion(int questionId, int userId) {
        QuestionVO questionVO = D.lpQuestionMapper().selectByPrimaryKeyForUser(questionId, userId);
        List<LpOption> options = getOptionByQuestionId(questionId);
        questionVO.setOptions(options);
        return questionVO;
    }

    /**
     * 根据questionId获取试题选项
     *
     * @param questionId
     * @return
     */
    public List<LpOption> getOptionByQuestionId(int questionId) {
        List<LpOption> lpOptions = D.lpOptionMapper().selectByQuestionId(questionId);
        String[] labels = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        int i = 0;
        for (LpOption lpOption : lpOptions) {
            lpOption.setLabel(labels[i]);
            i++;
        }
        return lpOptions;
    }

    /**
     * 根据questionId更新用户的答题信息
     * 用户答错次数：如果答错，则answer.collectTime = 1，否则为0
     * 用户答题次数：answer.answerTime = 1
     *
     * @param answer
     */
    @Transaction(jdbc = TrancationType.OPEN)
    public void updateUserAnserInfo(LpAnswer answer) {
        D.lpAnswerMapper().updateAnswerInfoByUserIdAndQuestionId(answer);
    }

    /**
     * 根据userId 和 questionId 获取用户的答题记录
     *
     * @param userId
     * @param questionId
     * @return
     */
    public LpAnswer selectLpAnswerByUserIdAndQuestionId(int userId, int questionId) {
        return D.lpAnswerMapper().selectByUserIdAndQuestionId(userId, questionId);
    }

    /**
     * 保存用户收藏试题、错题
     * type：1、收藏，2、错题
     *
     * @param collection
     */
    @Transaction(jdbc = TrancationType.OPEN)
    public void saveCollection(LpCollection collection) {
        D.lpCollectionMapper().insertSelective(collection);
    }

    /**
     * 根据userId、questionId、type删除收藏、错题
     * type：1、收藏，2、错题
     *
     * @param collection
     */
    @Transaction(jdbc = TrancationType.OPEN)
    public void unCollectQuestion(LpCollection collection) {
        D.lpCollectionMapper().deleteByUserIdAndQuestionId(collection);
    }

    /**
     * 保存、删除用户错题
     * 用户做题次数 +1
     * 如果做错，则错题次数 +1
     *
     * @param userAnswerVO
     */
    public void saveQuestionError(UserAnswerVO userAnswerVO) {
        String userResult = userAnswerVO.getUserResult();
        Integer questionId = userAnswerVO.getQuestionId();
        int userId = userAnswerVO.getUserId();
        String userAnswer = userAnswerVO.getUserAnswer();
        LpAnswer lpAnswer = selectLpAnswerByUserIdAndQuestionId(userId, questionId);
        if (null == lpAnswer) {//第一次做此题
            lpAnswer = new LpAnswer();
        }
        lpAnswer.setCollectTime(0);
        lpAnswer.setQuestionId(questionId);
        lpAnswer.setUserId(userId);
        LpCollection collection = getCollectQuestion(questionId, userId, 2);
        //用户做对了此题
        if (userResult.equals("true")) {
            if (null != collection) {//上一次此题做错了，则删除此题
                deleteCollectById(collection.getId());
            }
        } else {
            //用户做错了
            lpAnswer.setCollectTime(1);
            LpQuestion lpQuestion = D.lpQuestionMapper().selectByPrimaryKey(questionId);
            if (null == collection) {//上一次此题没做错，则保存此题
                collection = new LpCollection();
                collection.setUserId(userId);
                collection.setType(2);
                collection.setCategoryCode(lpQuestion.getCategoryCode());
                collection.setQuestionId(questionId);
                collection.setAnswer(userAnswer);
                collection.setUpdateTime(new Date());
                saveCollection(collection);
            }
        }
        saveAndUpdateUserAnswer(lpAnswer);
    }

    /**
     * 根据id删除collection
     *
     * @param id
     */
    @Transaction(jdbc = TrancationType.OPEN)
    public void deleteCollectById(Integer id) {
        D.lpCollectionMapper().deleteByPrimaryKey(id);
    }

    /**
     * 查询用户收藏、错题
     *
     * @param questionId
     * @param userId
     * @param type       ：类型，1、收藏，2、错题
     * @return
     */
    public LpCollection getCollectQuestion(Integer questionId, int userId, int type) {
        return D.lpCollectionMapper().selectByQIdUIdAndType(questionId, userId, type);
    }

    /**
     * 保存用户答题信息
     * 新建数据
     *
     * @param answer
     */
    @Transaction(jdbc = TrancationType.OPEN)
    public void saveUserAnswer(LpAnswer answer) {
        D.lpAnswerMapper().insertSelective(answer);
    }

    /**
     * 保存用户答题信息
     * 用户已答过此题，则更新此题
     * 没有答过，则添加新题
     *
     * @param answer
     */
    public void saveAndUpdateUserAnswer(LpAnswer answer) {
        Integer userId = answer.getUserId();
        Integer questionId = answer.getQuestionId();
        LpAnswer lpAnswer = selectLpAnswerByUserIdAndQuestionId(userId, questionId);
        if (null != lpAnswer) {
            updateUserAnserInfo(answer);
        } else {
            answer.setCollectTime(1);
            answer.setAnswerTime(1);
            answer.setCreateTime(new Date());
            answer.setDeleted(1);
            saveUserAnswer(answer);
        }
    }

    /**
     * 保存、删除用户收藏题
     *
     * @param collection
     */
    public void saveOrDeleteCollect(LpCollection collection) {
        LpQuestion lpQuestion = D.lpQuestionMapper().selectByPrimaryKey(collection.getQuestionId());
        collection.setCategoryCode(lpQuestion.getCategoryCode());

        if (collection.isDeleted()) {
            saveCollection(collection);
        } else {
            unCollectQuestion(collection);
        }
    }

    /**
     * 根据userId获取收藏试题
     * 不带options（选项）
     *
     * @param userId
     * @return
     */
    public List<AnswerCardVO> getCollectQuestions(int userId) {
        return getCollections(userId, 1);
    }

    /**
     * 根据userId获取错题
     * 不带options（选项）
     *
     * @param userId
     * @return
     */
    public List<AnswerCardVO> getErrorQuestions(int userId) {
        return getErrorCollections(userId);
    }

    /**
     * 根据userId，type获取用户收藏题或错题内容
     * 返回的answerCardVo
     * 生成答题卡
     *
     * @param userId
     * @param type
     * @return
     */
    public List<AnswerCardVO> getCollections(int userId, int type) {
        return D.lpCollectionMapper().selectCollectionsByUserIdForCard(userId, type);
    }

    /**
     * 获取用户的错题
     * 带此题是否被收藏过
     *
     * @param userId
     * @return
     */
    public List<AnswerCardVO> getErrorCollections(int userId) {
        return D.lpCollectionMapper().selectErrorCollections(userId);
    }

    /**
     * 分页查询所有试题
     *
     * @param currentNum
     * @param pageSize
     * @return
     */
    public List<QuestionVO> getAllQuestionsForLimit(Integer currentNum, Integer pageSize) {
        List<QuestionVO> list = D.lpQuestionMapper().selectAllQuestionForLimit(currentNum, pageSize);
        ArrayList<QuestionVO> vos = new ArrayList<QuestionVO>();
        for (int i = 0; i < list.size(); i++) {
            QuestionVO questionVO = list.get(i);
            List<LpOption> options = getOptionByQuestionId(questionVO.getQuestionId());
            questionVO.setOptions(options);
            vos.add(questionVO);
        }
        return vos;
    }

    /**
     * 根据userId清空用户的错题本、收藏本
     *
     * @param userId
     * @param type      ：1、收藏本，2、错题本
     * @param sectionId
     */
    @Transaction(jdbc = TrancationType.OPEN)
    public void clearCollectionByUserId(Integer userId, Integer type, String sectionId) {
        System.out.println(type);
        int i = D.lpCollectionMapper().deleteByUserIdAndType(userId, type, sectionId);
    }

    /**
     * 根据userId清空用户的错题本、收藏本
     *
     * @param userId
     * @param type   ：1、收藏本，2、错题本
     */
    @Transaction(jdbc = TrancationType.OPEN)
    public void clearCollectionByUserId(Integer userId, Integer type) {
        D.lpCollectionMapper().deleteByUserIdAndType(userId, type, null);
    }

    /**
     * 根据试题类型，获取试题
     * 试题不包括option（选项）
     *
     * @param type
     * @return
     */
    public List<AnswerCardVO> getQuestionByType(int userId, String type) {
        return D.lpQuestionMapper().selectByType(userId, type);
    }

    /**
     * 根据试题类型，获取试题 优化版本的
     * 试题不包括option（选项）
     *
     * @param type
     * @return
     */
    public Object[][] getQuestionByTypeOptimize(int userId, String type) {
        List<Map> list = D.lpQuestionMapper().selectByTypeOptimize(userId, type);
        Object[][] ret = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Object questionId = map.get("questionId");
            Object collectTime = map.get("collectTime");
            Object answerTime = map.get("answerTime");
            Object seq = map.get("seq");
            ret[i] = new Object[]{questionId, collectTime, answerTime, seq};
        }
        return ret;
    }

    /**
     * 根据用户Id获取收藏题或错题
     * type：1、收藏，2、错题
     * 带options
     *
     * @param userId
     * @param type
     * @return
     */
    public List<QuestionVO> getQuestionsByTypeForPrint(int userId, int type) {
        List<AnswerCardVO> list = getCollectionsForPrint(userId, type);
        ArrayList<QuestionVO> vos = new ArrayList<QuestionVO>();
        for (int i = 0; i < list.size(); i++) {
            AnswerCardVO vo = list.get(i);
            QuestionVO questionVO = new QuestionVO();
            questionVO.setQuestionId(vo.getQuestionId());
            questionVO.setName(vo.getName());
            questionVO.setType(vo.getType());
            questionVO.setSeq(vo.getSeq());
            questionVO.setCollectTime(vo.getCollectTime());
            questionVO.setAnswerTime(vo.getAnswerTime());
            questionVO.setCategoryCode(vo.getCategoryCode());
            List<LpOption> options = getOptionByQuestionId(vo.getQuestionId());
            questionVO.setOptions(options);
            vos.add(questionVO);
        }
        return vos;
    }

    /**
     * 根据userId和type获取用户的错题、收藏题
     * 获取的结果用于打印
     *
     * @param userId
     * @param type：1、收藏题，2、错题
     * @return
     */
    private List<AnswerCardVO> getCollectionsForPrint(int userId, int type) {
        return D.lpCollectionMapper().selectCollectionsByUserIdForPrint(userId, type);
    }

    /**
     * 保存用户错题
     * @param data
     * @param userId
     */
    public void saveErrors(String data, int userId) {
        JSONArray jsonArray = JSONArray.fromObject(data);
        Object[] array = jsonArray.toArray();
        UserAnswerVO userAnswerVO = new UserAnswerVO();
        for (int i = 0; i < array.length; i++) {
            Object o = array[i];
            JSONObject jsonObject = JSONObject.fromObject(o);
            String qid = jsonObject.getString("name");
            JSONObject val = jsonObject.getJSONObject("val");
            if(val.size()>0 && StringUtils.isNotBlank(val.getString("ua"))) {
                String ua = val.getString("ua");
                String ur = val.getString("ur");
                userAnswerVO.setUserId(userId);
                userAnswerVO.setQuestionId(Integer.parseInt(qid));
                userAnswerVO.setUserAnswer(ua);
                userAnswerVO.setUserResult(ur);
                saveQuestionError(userAnswerVO);
            }
        }

    }
}
