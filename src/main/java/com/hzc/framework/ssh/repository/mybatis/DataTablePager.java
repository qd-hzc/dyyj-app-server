package com.hzc.framework.ssh.repository.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2014/12/10 0010.
 * 注意：
 * 使用此注解方法中的第一个查询语句，被启用分页。
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataTablePager {

}
