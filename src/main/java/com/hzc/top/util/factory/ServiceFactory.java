package com.hzc.top.util.factory;

import com.hzc.top.model.LpCollection;
import com.hzc.top.model.PufaImport;
import com.hzc.top.service.app.*;
import com.hzc.top.service.server.AlipayService;
import com.hzc.top.service.server.LpQuestionService;
import com.hzc.top.service.server.PufaImportService;
import com.hzc.top.service.server.UserService;

/**
 * @author yinbin
 */

public class ServiceFactory {

    public static <T> T get(Class<T> t) {
        return com.hzc.framework.ssh.service.ServiceFactory.getInstance(t);
    }

    public static SysUserService appUserService() {
        return get(SysUserService.class);
    }

    public static KjAccountingEnrollService accountingEnrollService() {
        return get(KjAccountingEnrollService.class);
    }

    public static EnSyncDataService syncDataService() {
        return get(EnSyncDataService.class);
    }

    public static EnNoteService noteService() {
        return get(EnNoteService.class);
    }

    public static EnStudytimeService studytimeService() {
        return get(EnStudytimeService.class);
    }

    public static EnDiscussService discussService() {
        return get(EnDiscussService.class);
    }

    public static HsCategoryService hsCategoryService() {
        return get(HsCategoryService.class);
    }

    public static HsConsumeService hsConsumeService() {
        return get(HsConsumeService.class);
    }

    public static HsIntegralService hsIntegralService() {
        return get(HsIntegralService.class);
    }

    public static HsSettingService hsSettingService() {
        return get(HsSettingService.class);
    }

    public static SysMessageService sysMessageService() {
        return get(SysMessageService.class);
    }

    public static PufaImportService pufaImportService() {
        return get(PufaImportService.class);
    }

    public static UserService userService() {
        return get(UserService.class);
    }

    public static HsAnnouncementService hsAnnouncementService() {
        return get(HsAnnouncementService.class);
    }

    public static LpQuestionService lpQuestionService() {
        return get(LpQuestionService.class);
    }

    public static LpSyncService lpSyncService() {
        return get(LpSyncService.class);
    }

    public static AlipayService alipayService() {
        return get(AlipayService.class);
    }

}
