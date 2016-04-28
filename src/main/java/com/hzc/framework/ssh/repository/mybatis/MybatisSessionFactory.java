package com.hzc.framework.ssh.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YinBin on 14-4-18.
 */
public class MybatisSessionFactory {

    private static String key = "mybatis_key";
    private static String key_autocommit = "autocommit";
    private static String key_junit = "junit";

    private static Map<String, SqlSessionFactory> factoryMap = new HashMap<String, SqlSessionFactory>();
    private static Map<String, Boolean> autoCommitMap = new HashMap<String, Boolean>();
    private static Map<String, Boolean> junitMap = new HashMap<String, Boolean>();

    public static SqlSessionFactory getFactory() {
        return factoryMap.get(key);
    }

    public static void putFactory(SqlSessionFactory factory) {
        factoryMap.put(key, factory);
    }

    public static boolean isAutoCommit() {
        Boolean b = autoCommitMap.get(key_autocommit);
        if (null != b)
            return b;
        return true;// 默认自动提交
    }

    public static void setAutoCommit(boolean b) {
        autoCommitMap.put(key_autocommit, b);
    }

    public static boolean isJunit() {
        Boolean b = junitMap.get(key_junit);
        if (null != b)
            return b;
        return false;// 默认不是junit
    }

    public static void setJunit(boolean b) {
        junitMap.put(key_junit, b);
    }

    public static SqlSession getSqlSession(boolean b) {
        return getFactory().openSession(b);
    }
}
