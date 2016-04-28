package com.hzc.framework.ssh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by YinBin on 14-5-7.
 */
public class RouteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionContext.setReq(req);
        ActionContext.setResp(resp);
        ActionContext.setSession(req.getSession());
        try {
            String classMethod = req.getServletPath().substring(req.getServletPath().lastIndexOf("/") + 1);
            //.replace(".do", "");
            int lastIndexOf = classMethod.lastIndexOf(".do");
            classMethod = classMethod.substring(0, lastIndexOf);
            // restore to attribute
            Enumeration<String> enu = req.getParameterNames();
            while (enu.hasMoreElements()) {
                String key = enu.nextElement();
                String value = req.getParameter(key);
                req.setAttribute(key, value);
            }
            WebUtil.callCtl(classMethod);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
