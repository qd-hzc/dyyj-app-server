package com.hzc.top.service.app;

import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.HsIntegral;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.model.HsIntegral;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.vo.ResultVO;

import java.util.List;
import java.util.Map;

import java.util.List;

/**
 * Created by yinbin on 2015/4/3.
 */
@Transaction
public class HsIntegralService {

    /**
     * 返回用户的所有积分来源
     *
     * @param userId
     * @return
     */
    public List<Map> sourceList(Integer userId) {
        return D.hsIntegralMapper().selectSourceList(userId);
    }

    /**
     * 返回用户的所有积分支出情况
     *
     * @param userId
     * @return
     */
    public List<Map> targetList(Integer userId) {
        return D.hsIntegralMapper().selectTargetList(userId);
    }

    /**
     * 根据userId获取用户的所有积分获取和消费记录
     * @param userId
     * @return
     */
    public List<HsIntegral> getUserInfo(int userId) {
        return D.hsIntegralMapper().selectByUserId(userId);
    }

    /**
     * 保存用户的积分
     * type：1、获得积分，2、消费积分
     * source_type：1设置签到、2专业购买获得、3推荐获得、4金额奖励
     * target_type：1兑换商品、2购买课程
     * @param hsIntegral
     */
    public ResultVO saveUserIntegral(HsIntegral hsIntegral) {
        D.hsIntegralMapper().insertSelective(hsIntegral);
        return new ResultVO(true, "保存成功");
    }

    /**
     * 根据用户ID获取用户可用积分
     *
     * @param userId
     * @return
     */
    public int getUserAvailableIntegral(int userId) {
        List<HsIntegral> userInfo = getUserInfo(userId);
        int all = 0;
        for (int i = 0; i < userInfo.size(); i++) {
            HsIntegral hsIntegral = userInfo.get(i);
            //type :积分类别；1、获得积分；2、消费积分
            Integer type = hsIntegral.getType();
            if (type == 1) {
                all += hsIntegral.getIntegralVal();
            }else if(type==2){
                all -= hsIntegral.getIntegralVal();
            }
        }
        return all;
    }

}
