package com.hzc.framework.ssh.repository.mybatis;

import com.hzc.top.service.app.KjAccountingEnrollService;
import com.hzc.framework.ssh.service.Context;

/**
 * Created by YinBin on 14-5-7.
 */
public class DaoFactory {

    public static <T> T get(Class<T> clazz) {
        return Context.getSqlSession().getMapper(clazz);
    }

    public static KjAccountingEnrollService cmnAppMapper() {
        return get(KjAccountingEnrollService.class);
    }

}
