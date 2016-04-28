/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hzc.framework.ssh.repository.jdbc;

/**
 * @author YinBin
 */
public class JdbcException extends Exception {

    public JdbcException(String msg) {
        super("fy jdbc exception : " + msg);
    }

    public JdbcException(String message, Throwable cause) {
        super("fy jdbc exception : " + message, cause);
    }

}
