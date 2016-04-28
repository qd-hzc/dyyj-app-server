/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package com.hzc.framework.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author YinBin
 */
public class PropertiesUtil {
    private static Logger log = Logger.getLogger(Logger.class);
    // field
    private static Properties p = new Properties();

    // test
    public static void main(String args[]) throws IOException {
        // init("/memcached.properties");
        // String servers = getProperties("servers");
        // System.out.println(servers);
        //
        // init("/log4j.properties");
        // String get = getProperties("log4j.logger.org.mybatis");
        // System.out.println(get);
        //
        // init("/memcached.properties");
        // String maintSleep = getProperties("maintSleep");
        // System.out.println(maintSleep);
    }

    public static void init(String configPath) {
        Properties p1 = new Properties();
        InputStream is = PropertiesUtil.class.getResourceAsStream(configPath);
        try {
            p1.load(is);
            p.putAll(p1);
        } catch (IOException ex) {
            log.error(ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    log.error(ex);
                }
                is = null;
            }
        }
    }

    public static void initExtiact(String extiactConfigPath) {
        Properties p1 = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(new File(extiactConfigPath));
            p1.load(is);
            p.putAll(p1);
        } catch (IOException ex) {
            log.error(ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    log.error(ex);
                }
                is = null;
            }
        }
    }

    public static String getProperties(String key) {
        String result = null;
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(p.getProperty(key))) {
            result = p.getProperty(key).trim();
        }
        return result;
    }

    public static Integer getNumber(String key) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(getProperties(key))) {
            return 0;
        }
        return Integer.parseInt(getProperties(key));
    }

    public static boolean getBoolean(String key) {
        boolean result = false;
        if (StringUtils.isBlank(key) || StringUtils.isBlank(getProperties(key))) {
            result = false;
        } else {
            result = Boolean.parseBoolean(getProperties(key));
        }
        return result;
    }

}
