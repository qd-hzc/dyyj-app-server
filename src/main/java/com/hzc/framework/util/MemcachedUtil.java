package com.hzc.framework.util;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;


/**
 * Created by FelixYin on 2014/10/15.
 */
public class MemcachedUtil {

    /**
     * memcached客户端单例
     */
    private static MemCachedClient cachedClient = new MemCachedClient();

    /**
     * 初始化连接池
     */
    static {
//        初始化properties对象，则可以通过get方法获取所需树形了
        PropertiesUtil.initExtiact("/usr/local/etc/lcsf/memcached.cfg");

//        init("/memcached.properties");


        //获取连接池的实例
        SockIOPool pool = SockIOPool.getInstance();

        //服务器列表及其权重
        String[] servers = {PropertiesUtil.getProperties("servers")};
        Integer[] weights = {PropertiesUtil.getNumber("weights")};

        //设置服务器信息
        pool.setServers(servers);
        pool.setWeights(weights);

        //设置初始连接数、最小连接数、最大连接数、最大处理时间
        pool.setInitConn(PropertiesUtil.getNumber("initConn"));
        pool.setMinConn(PropertiesUtil.getNumber("minConn"));
        pool.setMaxConn(PropertiesUtil.getNumber("maxConn"));
//        pool.setMaxIdle(1000 * 60 * 60);
        pool.setMaxIdle(PropertiesUtil.getNumber("maxIdle"));

        //设置连接池守护线程的睡眠时间
        pool.setMaintSleep(PropertiesUtil.getNumber("maintSleep"));

        //设置TCP参数，连接超时
        pool.setNagle(PropertiesUtil.getBoolean("nagle"));
        pool.setSocketTO(PropertiesUtil.getNumber("socketTO"));
        pool.setSocketConnectTO(PropertiesUtil.getNumber("socketConnectTO"));

        pool.setFailback(PropertiesUtil.getBoolean("failback"));
        pool.setFailover(PropertiesUtil.getBoolean("failover"));

        pool.setAliveCheck(PropertiesUtil.getBoolean("aliveCheck"));

        //初始化并启动连接池
        pool.initialize();

        //压缩设置，超过指定大小的都压缩
//      cachedClient.setCompressEnable(true);
//      cachedClient.setCompressThreshold(1024*1024);
    }

    private MemcachedUtil() {
    }

    public static boolean add(String key, Object value) {
        return cachedClient.add(key, value);
    }

    public static boolean add(String key, Object value, Integer expire) {
        return cachedClient.add(key, value, expire);
    }

    public static boolean put(String key, Object value) {
        return cachedClient.set(key, value);
    }

    public static boolean put(String key, Object value, Integer expire) {
        return cachedClient.set(key, value, expire);
    }

    public static boolean replace(String key, Object value) {
        return cachedClient.replace(key, value);
    }

    public static boolean replace(String key, Object value, Integer expire) {
        return cachedClient.replace(key, value, expire);
    }

    public static Object get(String key) {
        return cachedClient.get(key);
    }

    public static boolean remove(String key) {
        return cachedClient.delete(key);
    }
}
