package com.hzc.framework.util;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * view+model 产出  html的工具类
 *
 * @author felix yin
 */
public class TemplateUtil {

    private static GroupTemplate gt;

    static {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gt = new GroupTemplate(resourceLoader, cfg);
    }

    /**
     * 制造html方法
     *
     * @param template 模板字符串，mvc中的v
     * @param root     模型对象，mvc中的m
     * @return
     */
    public static String makeHtml(String template, Map root) {
        Template t = gt.getTemplate(template);
        t.binding(root);
        return t.render();
    }

    /**
     * 制造html方法
     *
     * @param template 模板字符串，mvc中的v
     * @param list     模型对象，mvc中的m
     * @return
     */
    public static String makeHtml(String template, List<Map> list) {
        Template t = gt.getTemplate(template);
        Map m = new HashMap();
        m.put("list", list);
        t.binding(m);
        return t.render();
    }
}
