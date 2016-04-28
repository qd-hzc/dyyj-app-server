package com.hzc.framework.ssh.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YinBin on 14-5-7.
 */
public class ServiceFactory {
    /**
     * 在应用声明周期内，只有一个实例
     */
    private static Map serviceMap = new HashMap();

    public static <T> T getInstance(Class<T> clazz) {
        Object o = serviceMap.get(clazz);
        if (null == o) {
            o = new CglibProxy().get(clazz);
            serviceMap.put(clazz, o);
        }
        return (T) o;
    }
}
