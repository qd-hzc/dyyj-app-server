package com.hzc.top.ctrl.app;

import com.hzc.top.model.*;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by LiuJY on 2015/3/26.
 */
public class EnSyncDataCtrl {


    /**
     * 获取 App的大纲数据是否有更新
     */
    public void getAppVersion() {
//         0 ，表示app整个的版本信息所在的那条大纲记录的主键
        EnCategory enCategory = S.syncDataService().loadCategoryById(0);
        W.writeJsonObject(enCategory);
    }

    /**
     * 获取课程下的章或者章下的节
     * 获取code的值：code可以是课程的code，或者是章的code
     */
    public void listChapterOrSection() {
        String code = W.getString("code");
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code is null");
        }
        List<EnCategory> list = S.syncDataService().getCategoryByCode(code);
        W.writeJsonArray(list);
    }

    /**
     * 根据节code获取所有试题
     */
    public void listQuestion() {
        String sectionId = W.getString("sectionId");
//        String phone = W.getString("phone");
        String phone = "15335320821";
        if (StringUtils.isBlank(sectionId) || StringUtils.isBlank(phone) || phone.length() != 11) {
            throw new IllegalArgumentException("code or phone is null");
        }
        List<EnQuestion> list = S.syncDataService().getQuestions(sectionId, phone);
        W.writeJsonArray(list);
    }

    /**
     * 查询所有category
     */
    public void listAllCategory() {
        List<EnCategory> list = S.syncDataService().getAllCategory();
        W.writeJsonArray(list);
    }

    /**
     * 根据节Id获取节下所有试题的material
     */
    public void listMaterialBySectionId() {
        String sectionId = W.getString("sectionId");
        String phone = W.getString("phone");
        if (StringUtils.isBlank(sectionId) || StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("sectionId or phone is null");
        }
        List<EnMaterialWithBLOBs> list = S.syncDataService().getEnMaterialsBySectionId(sectionId, phone);
        W.writeJsonArray(list);
    }

    /**
     * 根据节id获取节下的所有试题的option
     */
    public void listOptionsBySectionId() {
        String sectionId = W.getString("sectionId");
        String phone = W.getString("phone");
        if (StringUtils.isBlank(sectionId) || StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("sectionId or phone is null");
        }
        List<EnOption> list = S.syncDataService().getEnOptionsBySectionId(sectionId, phone);
        W.writeJsonArray(list);
    }

    /**
     * 根据节id获取节下的所有试题的resolution
     */
    public void listResolutionsBySectionId() {
        String sectionId = W.getString("sectionId");
        String phone = W.getString("phone");
        if (StringUtils.isBlank(sectionId) || StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("sectionId or phone is null");
        }
        List<EnResolution> list = S.syncDataService().getEnResolutionsBySectionId(sectionId, phone);
        W.writeJsonArray(list);
    }

}
