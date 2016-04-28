package com.hzc.framework.ssh.repository.jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param <T>
 * @author yinbin
 */
public interface IResultSetCall<T> {

    public T invoke(ResultSet rs) throws SQLException;

}
