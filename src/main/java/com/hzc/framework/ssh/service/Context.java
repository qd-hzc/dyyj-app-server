package com.hzc.framework.ssh.service;

import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yinbin
 */
public class Context implements Serializable {

    private static final String SQL_SESSION = "SQL_SESSION";
    /**
     * 是否显示班组
     */
    private static final String ISSHOWBANZU = "is_preview";
//	private static final String CLASS_METHOD_NAME = "CLASS_METHOD_NAME";

    @SuppressWarnings("rawtypes")
    private static ThreadLocal threadLocal = new ThreadLocal();

    @SuppressWarnings("unused")
    private Map<String, Object> sqlSessionMap;

    public static SqlSession getSqlSession() {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        if (null != map) {
            SqlSession sqlSession = (SqlSession) map.get(SQL_SESSION);
            if (null != sqlSession) {
                return sqlSession;
            }
        }
        return null;
    }

    public static void setSqlSession(SqlSession sqlSession) {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(SQL_SESSION, sqlSession);
    }

    public static String getShowBanzu() {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        if (null != map) {
            String isShowBanzu = (String) map.get(ISSHOWBANZU);
            if (null != isShowBanzu) {
                return isShowBanzu;
            }
        }
        return null;
    }

    public static void setShowBanzu(String val) {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(ISSHOWBANZU, val);
    }
}
