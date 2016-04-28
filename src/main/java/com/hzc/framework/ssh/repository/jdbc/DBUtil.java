package com.hzc.framework.ssh.repository.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yinbin <br/>
 *         DBUtil，数据库访问工具类<br/>
 * @preserve all
 */
public class DBUtil {

    public static Connection openConnection(String driverClass, String url, String username, String password) throws JdbcException {
        try {
            Class.forName(driverClass);
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
    }

    public static void closeConnection(Connection con) throws JdbcException {
        try {
            if (null != con) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
                    throw new JdbcException(ex.getMessage(), ex.getCause());
                }
            }
        } finally {
            con = null;
            System.gc();
        }
    }

    public static List<Map<String, Object>> queryMapList(Connection con, String sql) throws JdbcException {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        try {
            Statement preStmt = null;
            ResultSet rs = null;
            try {
                preStmt = con.createStatement();
                rs = preStmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (null != rs && rs.next()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (int i = 0; i < columnCount; i++) {
                        String name = rsmd.getColumnName(i + 1);
                        Object value = rs.getObject(name);
                        map.put(name, value);
                    }
                    lists.add(map);
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != preStmt) {
                    preStmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static List<Map<String, Object>> queryMapList(Connection con, String sql, Object... params)
            throws JdbcException {

        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        try {
            PreparedStatement preStmt = null;
            ResultSet rs = null;
            try {
                preStmt = con.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    preStmt.setObject(i + 1, params[i]);// 下标从1开始
                }
                rs = preStmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (null != rs && rs.next()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (int i = 0; i < columnCount; i++) {
                        String name = rsmd.getColumnName(i + 1);
                        Object value = rs.getObject(name);
                        map.put(name, value);
                    }
                    lists.add(map);
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != preStmt) {
                    preStmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static <T> List<T> queryBeanList(Connection con, String sql, Class<T> beanClass) throws JdbcException {

        List<T> lists = new ArrayList<T>();
        try {
            Statement stmt = null;
            ResultSet rs = null;
            Field[] fields = null;
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                fields = beanClass.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                }
                while (null != rs && rs.next()) {
                    T t = beanClass.newInstance();
                    for (Field f : fields) {
                        String name = f.getName();
                        try {
                            Object value = rs.getObject(name);
                            setValue(t, f, value);
                        } catch (Exception e) {
                        }
                    }
                    lists.add(t);
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != stmt) {
                    stmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static <T> List<T> queryBeanList(Connection con, String sql, Class<T> beanClass, Object... params)
            throws JdbcException {

        List<T> lists = new ArrayList<T>();
        try {
            PreparedStatement preStmt = null;
            ResultSet rs = null;
            Field[] fields = null;
            try {
                preStmt = con.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    preStmt.setObject(i + 1, params[i]);// 下标从1开始
                }
                rs = preStmt.executeQuery();
                fields = beanClass.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                }
                while (null != rs && rs.next()) {
                    T t = beanClass.newInstance();
                    for (Field f : fields) {
                        String name = f.getName();
                        try {
                            Object value = rs.getObject(name);
                            setValue(t, f, value);
                        } catch (Exception e) {
                        }
                    }
                    lists.add(t);
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != preStmt) {
                    preStmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static <T> List<T> queryBeanList(Connection con, String sql, IResultSetCall<T> qdi) throws JdbcException {

        List<T> lists = new ArrayList<T>();
        try {
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                while (null != rs && rs.next()) {
                    lists.add(qdi.invoke(rs));
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != stmt) {
                    stmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static <T> List<T> queryBeanList(Connection con, String sql, IResultSetCall<T> qdi, Object... params)
            throws JdbcException {

        List<T> lists = new ArrayList<T>();
        try {
            PreparedStatement preStmt = null;
            ResultSet rs = null;
            try {
                preStmt = con.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    preStmt.setObject(i + 1, params[i]);
                }
                rs = preStmt.executeQuery();
                while (null != rs && rs.next()) {
                    lists.add(qdi.invoke(rs));
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != preStmt) {
                    preStmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static <T> T queryBean(Connection con, String sql, Class<T> beanClass) throws JdbcException {

        List<T> lists = queryBeanList(con, sql, beanClass);
        return beanError(lists);
    }

    public static <T> T queryBean(Connection con, String sql, Class<T> beanClass, Object... params)
            throws JdbcException {

        List<T> lists = queryBeanList(con, sql, beanClass, params);
        return beanError(lists);
    }

    public static <T> List<T> queryObjectList(Connection con, String sql, Class<T> objClass) throws JdbcException {

        List<T> lists = new ArrayList<T>();
        try {
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                label:
                while (null != rs && rs.next()) {
                    Constructor<?>[] constor = objClass.getConstructors();
                    for (Constructor<?> c : constor) {
                        Object value = rs.getObject(1);
                        try {
                            lists.add((T) c.newInstance(value));
                            continue label;
                        } catch (Exception e) {
                        }
                    }
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != stmt) {
                    stmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static <T> List<T> queryObjectList(Connection con, String sql, Class<T> objClass, Object... params)
            throws JdbcException {

        List<T> lists = new ArrayList<T>();
        try {
            PreparedStatement preStmt = null;
            ResultSet rs = null;
            try {
                preStmt = con.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    preStmt.setObject(i + 1, params[i]);
                }
                rs = preStmt.executeQuery();
                label:
                while (null != rs && rs.next()) {
                    Constructor<?>[] constor = objClass.getConstructors();
                    for (Constructor<?> c : constor) {
                        String value = rs.getObject(1).toString();
                        try {
                            T t = (T) c.newInstance(value);
                            lists.add(t);
                            continue label;
                        } catch (Exception e) {
                        }
                    }
                }
            } finally {
                if (null != rs) {
                    rs.close();
                }
                if (null != preStmt) {
                    preStmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
        return lists;
    }

    public static <T> T queryObject(Connection con, String sql, Class<T> objClass) throws JdbcException {

        List<T> lists = queryObjectList(con, sql, objClass);
        return beanError(lists);
    }

    public static <T> T queryObject(Connection con, String sql, Class<T> objClass, Object... params)
            throws JdbcException {

        List<T> lists = queryObjectList(con, sql, objClass, params);
        return beanError(lists);
    }

    public static int execute(Connection con, String sql) throws JdbcException {
        try {
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                return stmt.executeUpdate(sql);
            } finally {
                if (null != stmt) {
                    stmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
    }

    public static int execute(Connection con, String sql, Object... params) throws JdbcException {
        try {
            PreparedStatement preStmt = null;
            try {
                System.out.println("SQL:\n" + sql);
                preStmt = con.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    preStmt.setObject(i + 1, params[i]);// 下标从1开始
                    System.out.println("index," + (i + 1) + " : " + params[i]);
                }
                return preStmt.executeUpdate();
            } finally {
                if (null != preStmt) {
                    preStmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
    }

    public static int[] executeAsBatch(Connection con, List<String> sqlList) throws JdbcException {
        return executeAsBatch(con, sqlList.toArray(new String[]{}));
    }

    public static int[] executeAsBatch(Connection con, String[] sqlArray) throws JdbcException {
        try {
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                for (String sql : sqlArray) {
                    stmt.addBatch(sql);
                }
                return stmt.executeBatch();
            } finally {
                if (null != stmt) {
                    stmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
    }

    public static int[] executeAsBatch(Connection con, String sql, Object[][] params) throws JdbcException {
        try {
            PreparedStatement preStmt = null;
            try {
                preStmt = con.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    Object[] rowParams = params[i];
                    for (int k = 0; k < rowParams.length; k++) {
                        Object obj = rowParams[k];
                        preStmt.setObject(k + 1, obj);
                    }
                    preStmt.addBatch();
                }
                return preStmt.executeBatch();
            } finally {
                if (null != preStmt) {
                    preStmt.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
    }

    private static <T> void setValue(T t, Field f, Object v) throws IllegalArgumentException, IllegalAccessException {
        // TODO 以数据库类型为准绳，还是以java数据类型为准绳？还是混合两种方式？
        if (null == v) {
            return;
        } else {
            f.set(t, v);
            return;
        }
//        String v = value.toString();
//        String n = f.getType().getName();
//        Class<?> type = f.getType();
//        if (type == String.class) {
//            f.set(t, v);
//        } else if (type == Byte.class) {
//            f.setByte(t, (Byte) v);
//        } else if (type == Short.class) {
//            f.setShort(t, (Short) v);
//        } else if (type == Integer.class) {
//            f.setInt(t, (Integer) v);
//        } else if (type == Long.class) {
//            f.setLong(t, (Long) v);
//        } else if (type == Float.class) {
//            f.setFloat(t, (Float) v);
//        } else if (type == Double.class) {
//            f.setDouble(t, (Double) v);
//        } else if (type == Character.class) {
//            f.setChar(t, (Character) v);
//        } else if (type == Boolean.class) {
//            f.setBoolean(t, (Boolean) v);
//        } else {
//            System.out.println("v:" + v);
//            f.set(t, v);
//        }
    }

    public static void executeProcedure(Connection con, String procedureName, Object... params) throws JdbcException {
        try {
            CallableStatement proc = null;
            try {
                proc = con.prepareCall(procedureName);
                for (int i = 0; i < params.length; i++) {
                    proc.setObject(i + 1, params[i]);
                }
                proc.execute();
            } finally {
                if (null != proc) {
                    proc.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcException(ex.getMessage(), ex.getCause());
        }
    }

    public static <T> List<List<T>> listLimit(List<T> lists, int pageSize) {
        List<List<T>> llists = new ArrayList<List<T>>();
        for (int i = 0; i < lists.size(); i = i + pageSize) {
            try {
                List<T> list = lists.subList(i, i + pageSize);
                llists.add(list);
            } catch (IndexOutOfBoundsException e) {
                List<T> list = lists.subList(i, i + (lists.size() % pageSize));
                llists.add(list);
            }
        }
        return llists;
    }

    /*
     * public static void executeProcedure(Connection con, String sql) {
     * CallableStatement callStmt = null;
     * try {
     * callStmt = con.prepareCall(sql);
     * callStmt.exe
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * 
     * }
     */

    /**
     * 去除javabean后缀的方法
     *
     * @param simpleClassName
     * @return
     */
    public static String unWarpTableName(String simpleClassName) {
        return simpleClassName.replace("Po", "").replace("Entity", "");
    }

    public static Long getNextVal(Connection con, String req) throws JdbcException {
        return queryObject(con, "SELECT nextval(?) FROM DUAL", Long.class, req);
    }

    public static Map<String, Object> queryMap(Connection conn, String sql) throws JdbcException {
        List<Map<String, Object>> list = queryMapList(conn, sql);
        return beanError(list);
    }

    public static Map<String, Object> queryMap(Connection conn, String sql, Object... params) throws JdbcException {
        List<Map<String, Object>> list = queryMapList(conn, sql, params);
        return beanError(list);
    }

    /**
     * 通用的异常处理方法
     *
     * @param list
     * @return
     * @throws java.sql.SQLException
     */
    public static <T> T beanError(List<T> list) throws JdbcException {
        if (list.size() > 1) {
            throw new JdbcException("期待一行，却返回了" + list.size() + "行！");
        } else if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

}
