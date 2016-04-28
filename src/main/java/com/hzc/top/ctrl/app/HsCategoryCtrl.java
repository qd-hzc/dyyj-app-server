package com.hzc.top.ctrl.app;

import com.hzc.top.model.HsCategory;
import com.hzc.top.model.HsConsume;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinbin on 2015/4/3.
 */
public class HsCategoryCtrl {

    /**
     * 根据父分类查询旗下子分类
     */
    public void childrenByPid() {
        Integer parentId = W.getInteger("parentId");
        List<HsCategory> hsCategories = S.hsCategoryService().listByPid(parentId);
        W.writeJsonArray(hsCategories);
    }

    /**
     * 根据id查询
     */
    public void loadOrderById() {
        Integer id = W.getInteger("id");
        HsCategory hsCategory = S.hsCategoryService().loadById(id);
        W.writeJsonObject(hsCategory);
    }

    /**
     * 保存用户缴费课程信息
     */
    public void savePayInfo() {
        Integer categoryId = W.getInteger("categoryId");
        Integer userId = W.getInteger("userId");
        //支付总金额
        Double sumAccount = W.getDouble("sumAccount");
        //消费的积分
        Integer integral = W.getInteger("integral");
        if (userId < 1 || sumAccount < 1 ||categoryId<1) {
            throw new IllegalArgumentException("arguments are wrong");
        }
        S.hsConsumeService().saveConsume(categoryId,userId,sumAccount,integral);
    }

    /**
     * 返回一个分类的大对象（是一个属性的内存结构）
     */
   /* public void all() {
        String callback = W.getString("callback");
        //用户的手机号（账号）
        String phone = W.getString("phone");
        Map<String, Object> root = new HashMap<String, Object>();
        HsCategory hsCategory = S.hsCategoryService().listAll(phone);
        root.put("items", hsCategory.getItems());
        root.put("success", true);
        String jsonString = W.getJsonArrayString(hsCategory.getItems());
//        System.out.println(jsonString);
        W.writeJson(callback+"(" + jsonString + ")");
    }*/


}
