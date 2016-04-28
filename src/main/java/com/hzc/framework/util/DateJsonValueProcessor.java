/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hzc.framework.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YinBin
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

    private String format = "yyyy-MM-dd";

    public DateJsonValueProcessor() {
    }

    public DateJsonValueProcessor(String format) {
        this.format = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value != null) {
            if (value instanceof Date[]) {
                SimpleDateFormat sf = new SimpleDateFormat(format);
                Date[] dates = (Date[]) value;
                obj = new String[dates.length];
                for (int i = 0; i < dates.length; i++) {
                    obj[i] = sf.format(dates[i]);
                }
            } else if (value instanceof Timestamp[]) {
                SimpleDateFormat sf = new SimpleDateFormat(format);
                Timestamp[] timestamps = (Timestamp[]) value;
                obj = new String[timestamps.length];
                for (int i = 0; i < timestamps.length; i++) {
                    obj[i] = sf.format(timestamps[i]);
                }
            }
        }
        return obj;
    }

    public Object processObjectValue(String key, Object value,
                                     JsonConfig jsonConfig) {
        if (value != null) {
            if (value instanceof Date) {
                String str = new SimpleDateFormat(format).format((Date) value);
                return str;
            } else if (value instanceof Timestamp) {
                String str = new SimpleDateFormat(format).format((Date) value);
                return str;
            }
        }
        return value;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
