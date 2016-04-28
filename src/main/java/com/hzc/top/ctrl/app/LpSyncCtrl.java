package com.hzc.top.ctrl.app;

import com.hzc.top.model.LpCollection;
import com.hzc.top.model.SysUser;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import com.hzc.top.vo.SystemEnum;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by yinbin on 2015/4/29.
 */
public class LpSyncCtrl {

    /**
     * 保存本地数据到服务器，加载服务器数据到本地
     */
    public void saveAndLoad() {
        String idCard = W.getString("idCard"); // phone

        String updateTimeStr = W.getString("updateTimeStr");
        Date updateTime = null;
        if (StringUtils.isNotBlank(updateTimeStr)) {
            updateTime = new Date(Long.parseLong(updateTimeStr));
        }

        String dataStr = W.getString("dataStr");
        LpCollection[] lpCollections = (LpCollection[]) W.jsonConvertJavaArray(LpCollection.class, dataStr);

//        根据userId查询时间大于currDate的数据。如果currDate没有的话查询所有数据。
        List<LpCollection> list = S.lpSyncService().loadRemoteData(idCard, updateTime);

//        直接存储dataStr，根据userId。如果dataStr没有数据则不进行存储。
        Date date = S.lpSyncService().saveLocalData(idCard, lpCollections);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", date.getTime());
        map.put("data", list);
        W.writeJsonObject(map);
    }

    public void saveCollection() {
        String idCard = W.getString("idCard");
        SysUser userByPhone = S.appUserService().getUserByIdCard(idCard, SystemEnum.PUFA_LEXU.getValue());
        LpCollection lpCollection = W.packBean(LpCollection.class);
        lpCollection.setUpdateTime(new Date());
        lpCollection.setUserId(userByPhone.getId());
        boolean b = S.lpSyncService().saveCollection(lpCollection);
        W.writeJson(b, "");
    }

    public void deleteCollection() {
        String idCard = W.getString("idCard");
        SysUser userByPhone = S.appUserService().getUserByIdCard(idCard, SystemEnum.PUFA_LEXU.getValue());
        LpCollection lpCollection = W.packBean(LpCollection.class);
        lpCollection.setUpdateTime(new Date());
        lpCollection.setUserId(userByPhone.getId());
        boolean b = S.lpSyncService().deleteCollection(lpCollection);
        W.writeJson(b, "");
    }

    public void loadCollection() {
        String idCard = W.getString("idCard");
        SysUser userByPhone = S.appUserService().getUserByIdCard(idCard, SystemEnum.PUFA_LEXU.getValue());
        LpCollection lpCollection = W.packBean(LpCollection.class);
        lpCollection.setUserId(userByPhone.getId());
        List<LpCollection> list = S.lpSyncService().loadCollection(lpCollection);
        W.writeJsonArray(list);
    }

}
