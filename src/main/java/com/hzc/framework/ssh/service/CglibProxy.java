package com.hzc.framework.ssh.service;

import com.hzc.framework.ssh.controller.WebUtil;
import com.hzc.framework.ssh.repository.mybatis.DataTablePager;
import com.hzc.framework.ssh.repository.mybatis.MybatisSessionFactory;
import com.hzc.framework.util.MemcachedUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * @author yinbin
 */
public class CglibProxy implements MethodInterceptor {
    private static Logger log = Logger.getLogger(CglibProxy.class);
    private Enhancer enhancer = new Enhancer();

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    // 实现MethodInterceptor接口方法
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;

        boolean isOk = true;

        Transaction transactionAnno = null;
        Memcached memcachedAnno = null;
        DataTablePager annotation = null;
        try {
            transactionAnno = method.getAnnotation(Transaction.class);
            memcachedAnno = method.getAnnotation(Memcached.class);
            annotation = method.getAnnotation(DataTablePager.class);
        } catch (Exception e) {
            log.error("error:", e);
        }
        if (null == transactionAnno) {
            transactionAnno = method.getDeclaringClass().getAnnotation(Transaction.class);
        }

        if (null != memcachedAnno) {
            MemcachedType type = memcachedAnno.type();
            if (type == MemcachedType.SEL) {
                String key = memcachedAnno.key();
                try {
                    if (null != args && args.length > 0) {
                        Object param1 = args[memcachedAnno.keyIdx()];
                        key += param1.toString();
                    }
                } catch (Exception e) {
                }
                result = MemcachedUtil.get(key);
                if (null != result) {
                    return result;
                }
            }
        }

        if (null != annotation) {
            WebUtil.prePageForDataTable();
        }

        if (null != transactionAnno) {
            TrancationType jdbcTrancationType = transactionAnno.jdbc();

            SqlSession sqlSession = Context.getSqlSession();

            // 通过代理类调用父类中的方法
            try {

                // jdbc 开启事务时的处理
                if (jdbcTrancationType != TrancationType.NONE) {
                    if (null == sqlSession) {
                        if (jdbcTrancationType == TrancationType.OPEN) {
                            sqlSession = MybatisSessionFactory.getSqlSession(false);
                        } else {
                            sqlSession = MybatisSessionFactory.getSqlSession(true);
                        }
                        Context.setSqlSession(sqlSession);
                    } else {
                        isOk = false;
                    }

                } else {
                    isOk = false;
                }
                // invoke 被代理方法
                result = proxy.invokeSuper(obj, args);

                memcachedLogic(result, memcachedAnno, args);

                if (isOk) {
                    // 提交
                    if (jdbcTrancationType == TrancationType.OPEN) {
                        if (null != sqlSession) {
                            sqlSession.commit();
                        }
                    }
                }
            } catch (Exception e) {
                log.error("error:", e);
                if (isOk) {
                    if (jdbcTrancationType == TrancationType.OPEN) {
                        if (null != sqlSession) {
                            sqlSession.rollback();
                        }
                    }
                }
                throw e;
            } finally {
                if (isOk) {
                    if (null != sqlSession) {// 继承jdbc事务支持
                        sqlSession.close();
                        sqlSession = null;
                        Context.setSqlSession(sqlSession);
                    }
                }
            }// finally end

        }// if end
        else {
            // 没有注解便不能使用Context，便也不错任何处理，此类将作为一个普通的service
            result = proxy.invokeSuper(obj, args);
            memcachedLogic(result, memcachedAnno, args);
        }

        return result;
    }

    private void memcachedLogic(Object result, Memcached memcachedAnno, Object[] args) {
        if (null != memcachedAnno) {
            MemcachedType type = memcachedAnno.type();
            String key = memcachedAnno.key();
            try {
                if (null != args && args.length > 0) {
                    Object param1 = args[memcachedAnno.keyIdx()];
                    key += param1.toString();
                }
            } catch (Exception e) {
            }
            if (type == MemcachedType.SEL) {
                MemcachedUtil.put(key, result);
            }
            if (type == MemcachedType.UPD) {
                MemcachedUtil.replace(key, result);
            }
            if (type == MemcachedType.DEL) {
                MemcachedUtil.remove(key);
            }
        }
    }
}
