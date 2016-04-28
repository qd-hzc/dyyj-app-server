package com.hzc.top.util.factory;

import com.hzc.top.dao.*;
import com.hzc.framework.ssh.service.Context;
import com.hzc.top.model.HsCategory;
import com.hzc.top.model.PufaImport;

/**
 * Created by YinBin on 14-4-21.
 */
public class DaoFactory {

    public static <T> T get(Class<T> t) {
        return Context.getSqlSession().getMapper(t);
    }

    public static BizAccountingEnrollMapper bizAccountingEnrollMapper() {
        return get(BizAccountingEnrollMapper.class);
    }

    public static AppVerifyCodeMapper appVerifyCodeMapper() {
        return get(AppVerifyCodeMapper.class);
    }

    public static SysUserMapper sysUserMapper() {
        return get(SysUserMapper.class);
    }

    public static EnCategoryMapper enCategoryMapper() {
        return get(EnCategoryMapper.class);
    }

    public static EnQuestionMapper enQuestionMapper() {
        return get(EnQuestionMapper.class);
    }

    public static EnMaterialMapper enMaterialMapper() {
        return get(EnMaterialMapper.class);
    }

    public static EnOptionMapper enOptionMapper() {
        return get(EnOptionMapper.class);
    }

    public static EnResolutionMapper enResolutionMapper() {
        return get(EnResolutionMapper.class);
    }

    public static EnNoteMapper enNoteMapper() {
        return get(EnNoteMapper.class);
    }

    public static EnStudytimeMapper enStudytimeMapper() {
        return get(EnStudytimeMapper.class);
    }

    public static EnDiscussMapper enDiscussMapper() {
        return get(EnDiscussMapper.class);
    }

    public static HsCategoryMapper hsCategoryMapper() {
        return get(HsCategoryMapper.class);
    }

    public static HsConsumeMapper hsConsumeMapper() {
        return get(HsConsumeMapper.class);
    }

    public static HsIntegralMapper hsIntegralMapper() {
        return get(HsIntegralMapper.class);
    }

    public static HsSettingMapper hsSettingMapper() {
        return get(HsSettingMapper.class);
    }

    public static SysMessageMapper sysMessageMapper() {
        return get(SysMessageMapper.class);
    }

    public static PufaImportMapper pufaImportMapper() {
        return get(PufaImportMapper.class);
    }

    public static HsAnnouncementMapper hsAnnouncementMapper(){return get(HsAnnouncementMapper.class);}

    public static LpAnswerMapper lpAnswerMapper() {
        return get(LpAnswerMapper.class);
    }

    public static LpCollectionMapper lpCollectionMapper() {
        return get(LpCollectionMapper.class);
    }

    public static LpQuestionMapper lpQuestionMapper() {
        return get(LpQuestionMapper.class);
    }

    public static LpOptionMapper lpOptionMapper() {
        return get(LpOptionMapper.class);
    }
}
