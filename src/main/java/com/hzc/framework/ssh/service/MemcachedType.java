package com.hzc.framework.ssh.service;

/**
 * @author Felixyin
 */
public enum MemcachedType {

    /**
     * 开启缓存，切回自动添加方法的返回结果到缓存
     */
    SEL,
    /**
     * 删除缓存
     */
    DEL,
    /**
     * 将当前方法的返回结果，更新到对应的key
     */
    UPD

}
