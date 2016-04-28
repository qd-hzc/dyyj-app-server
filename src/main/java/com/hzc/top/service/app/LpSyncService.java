package com.hzc.top.service.app;

import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.LpCollection;
import com.hzc.top.model.SysUser;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.vo.SystemEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by yinbin on 2015/4/29.
 */
@Transaction
public class LpSyncService {


    /**
     * 直接存储dataStr，根据userId。如果dataStr没有数据则不进行存储。
     */
    public Date saveLocalData(String idCard, LpCollection[] lpCollections) {
        Date date = new Date(); // 存储的时间，统一使用服务器的时间
        SysUser userByPhone = S.appUserService().getUserByIdCard(idCard, SystemEnum.PUFA_LEXU.getValue());
        List<Integer> qIds = D.lpCollectionMapper().selectAllCollectQid(userByPhone.getId());
        for (LpCollection lpCollection : lpCollections) {
            lpCollection.setUserId(userByPhone.getId());
            lpCollection.setUpdateTime(date);
            if (qIds.contains(lpCollection.getQuestionId())) {
                D.lpCollectionMapper().updateByPrimaryKeySelective(lpCollection);
            } else {
                D.lpCollectionMapper().insert(lpCollection);
            }
        }
        return date;
    }


    /**
     * 根据userId查询时间大于currDate的数据。如果currDate没有的话查询所有数据。
     */
    public List<LpCollection> loadRemoteData(String idCard, Date updateTime) {
        SysUser userByPhone = S.appUserService().getUserByIdCard(idCard, SystemEnum.PUFA_LEXU.getValue());
        Integer userId = userByPhone.getId();
        return D.lpCollectionMapper().selectListGtCurrDate(userId, updateTime);
    }

    public boolean saveCollection(LpCollection lpCollection) {
        int i = D.lpCollectionMapper().insertSelective(lpCollection);
        return i == 1;
    }

    public boolean deleteCollection(LpCollection lpCollection) {
        int i = D.lpCollectionMapper().deleteByUserIdAndQuestionId(lpCollection);
        return i == 1;
    }

    public List<LpCollection> loadCollection(LpCollection lpCollection) {
        return D.lpCollectionMapper().selectAllByUserIdAndType(lpCollection);
    }
}
