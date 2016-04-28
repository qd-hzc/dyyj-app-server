package com.hzc.framework.ssh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ActionContext<T> {

    private static final String KEY_REQUEST = "request";
    private static final String KEY_RESPONSE = "response";
    private static final String KEY_SESSION = "session";
    private static final String KEY_FOR_PAGER = "datatable_pager";
    private static final String KEY_SERVLET_CONTEXT = "servlet_context";

    @SuppressWarnings("rawtypes")
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    @SuppressWarnings("unused")
    private Map<String, Object> sqlSessionMap;

    public static HttpSession getSession() {
        Map<String, Object> map = threadLocal.get();
        if (null != map) {
            HttpSession s = (HttpSession) map.get(KEY_SESSION);
            if (null != s) {
                return s;
            }
        }
        return null;
    }

    public static void setSession(HttpSession s) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(KEY_SESSION, s);
    }



    public static HttpServletRequest getReq() {
        Map<String, Object> map = threadLocal.get();
        if (null != map) {
            HttpServletRequest req = (HttpServletRequest) map.get(KEY_REQUEST);
            if (null != req) {
                return req;
            }
        }
        return null;
    }

    public static void setReq(HttpServletRequest req) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(KEY_REQUEST, req);
    }

    public static HttpServletResponse getResp() {
        Map<String, Object> map = threadLocal.get();
        if (null != map) {
            HttpServletResponse resp = (HttpServletResponse) map.get(KEY_RESPONSE);
            if (null != resp) {
                return resp;
            }
        }
        return null;
    }

    public static void setResp(HttpServletResponse resp) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(KEY_RESPONSE, resp);
    }

    public static boolean isForDataTable() {
        Map<String, Object> map = threadLocal.get();
        if (null != map) {
            Object resp = map.get(KEY_FOR_PAGER);
            if (null != resp) {
                return (Boolean) resp;
            }
        }
        return false;
    }

    public static void setForDataTable(boolean value) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(KEY_FOR_PAGER, value);
    }

    public static<T> T get(String key,T tt) {
        Map<String, Object> map = threadLocal.get();
        if (null != map) {
            T t = (T) map.get(key);
            if (null != t) {
                return t;
            }
        }
        return null;
    }

    public static<T> void set(String key,T t) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, t);
    }
}
