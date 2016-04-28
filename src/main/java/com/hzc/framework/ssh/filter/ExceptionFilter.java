package com.hzc.framework.ssh.filter;


import com.hzc.framework.ssh.controller.ActionContext;
import com.hzc.framework.ssh.controller.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yinbin on 2015/4/13.
 */
public class ExceptionFilter implements Filter {

    Log log = LogFactory.getLog(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            WebUtil.writeJson(false, ActionContext.get("ValidationError", ""));
        }
    }

    @Override
    public void destroy() {

    }

}
