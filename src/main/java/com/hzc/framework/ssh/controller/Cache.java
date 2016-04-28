package com.hzc.framework.ssh.controller;

import java.lang.reflect.Method;

/**
 * 缓存对象
 */
public class Cache {
    private Object object;
    private Method method;

    public Cache() {
    }

    public Cache(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
