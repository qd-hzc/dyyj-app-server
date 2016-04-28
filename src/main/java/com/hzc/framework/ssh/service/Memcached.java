package com.hzc.framework.ssh.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by FelixYin on 2014/10/15.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Memcached {

    /**
     * 作为可以的前缀
     * 必填，所谓多种缓存业务情况的区分
     *
     * @return
     */
    String key();

    /**
     * sel，表示走缓存
     * del，表示清除指定key的缓存
     * upd，表示将方法返回结果作为指定key的值
     *
     * @return
     */
    MemcachedType type() default MemcachedType.SEL;

    /**
     * 指定第几个参数作为key的一部分，默认为-1，表示不需要任何参数作为key的一部分
     *
     * @return
     */
    int keyIdx() default -1;

}
