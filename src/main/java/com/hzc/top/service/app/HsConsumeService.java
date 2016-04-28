package com.hzc.top.service.app;

import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.HsCategory;
import com.hzc.top.model.HsConsume;
import com.hzc.top.model.HsIntegral;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.util.factory.alias.S;

import java.util.Date;
import java.util.List;

/**
 * Created by yinbin on 2015/4/3.
 */
@Transaction
public class HsConsumeService {

    /**
     * 查询我的消费记录，按照时间倒序
     *
     * @param userId
     * @return
     */
    public List<HsConsume> list(Integer userId) {
        return D.hsConsumeMapper().selectListByUserId(userId);
    }

    /**
     * 保存用户的消费记录到数据库
     *
     * @param record
     * @return
     */
    public int saveHsConsumeBySelective(HsConsume record) {
        return D.hsConsumeMapper().insertSelective(record);
    }

    /**
     * 保存用户购买的专业
     * @param categoryId ：专业Id
     * @param userId ：用户Id
     * @param sumAccount ：支付总金额
     * @param integral ：消费总积分
     * @return
     */

    public boolean saveConsume(Integer categoryId, Integer userId, Double sumAccount, Integer integral) {
        HsCategory hsCategory = S.hsCategoryService().loadById(categoryId);
        if (null == hsCategory) {
            return false;
        } else {
            HsConsume hsConsume = new HsConsume();
            hsConsume.setUserId(userId);
            hsConsume.setName(hsCategory.getName());
            hsConsume.setType(hsCategory.getType());
            hsConsume.setStudyHours(hsCategory.getStudyHours());
            hsConsume.setStudyUnit(hsCategory.getStudyUnit());
            hsConsume.setPrice(hsCategory.getPrice());
            hsConsume.setClassDescr(hsCategory.getClassDescr());
            hsConsume.setMajorDescr(hsCategory.getMajorDescr());
            hsConsume.setExamFee(hsCategory.getExamFee());
            hsConsume.setTuitionFee(hsCategory.getTuitionFee());
            hsConsume.setBookFee(hsCategory.getBookFee());
            hsConsume.setPayMethod(hsCategory.getPayMethod());
            hsConsume.setStartDate(hsCategory.getStartDate());
            hsConsume.setEndDate(hsCategory.getEndDate());
            Date time = new Date();
            hsConsume.setCreateTime(time);
            hsConsume.setUpdateTime(time);
            hsConsume.setDeleted(1);
            hsConsume.setPayMoney(sumAccount.intValue());

            saveHsConsumeBySelective(hsConsume);

            Integer integralSwitch = hsCategory.getIntegralSwitch();
            //判断是否开启购买用户可以获得积分
            if (integralSwitch == 1) {
                HsIntegral hIntegral = new HsIntegral();
                hIntegral.setUserId(userId);
                hIntegral.setSourceType(2);
                hIntegral.setSourceId(hsCategory.getId());
                hIntegral.setIntegralVal(hsCategory.getIntegralVal());
                hIntegral.setType(1);
                hIntegral.setCreateTime(time);
                hIntegral.setUpdateTime(time);
                S.hsIntegralService().saveUserIntegral(hIntegral);
            }
            //用户消费了积分
            if(integral >0){
                HsIntegral hIntegral = new HsIntegral();
                hIntegral.setUserId(userId);
                hIntegral.setTargetType(2);
                hIntegral.setTargetId(hsCategory.getId());
                hIntegral.setIntegralVal(integral);
                hIntegral.setType(1);
                Date date = new Date();
                hIntegral.setCreateTime(date);
                hIntegral.setUpdateTime(date);
                S.hsIntegralService().saveUserIntegral(hIntegral);
            }
            return true;
        }
    }

}
