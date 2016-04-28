package com.hzc.framework.ssh.repository.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YinBin on 14-4-18.
 */
public class RegistorDynamicDb {


    private static final String KEY = "KEY";

    @SuppressWarnings("rawtypes")
    private static ThreadLocal threadLocal = new ThreadLocal();

    @SuppressWarnings("unused")
    private Map<String, String> sqlSessionMap;

    public static String getKey() {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        if (null != map) {
            String sqlSession = (String) map.get(KEY);
            if (null != sqlSession) {
                return sqlSession;
            }
        }
        return null;
    }

    public static void setKey(String key) {
        Map<String, String> map = (Map<String, String>) threadLocal.get();
        if (null == map) {
            map = new HashMap<String, String>();
            threadLocal.set(map);
        }
        map.put(KEY, key);
    }
}
