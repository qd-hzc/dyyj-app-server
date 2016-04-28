package com.hzc.framework.ssh.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author yinbin
 */
public class SetCharacterEncodingFilter implements Filter {

    private String encoding;
    private String contentType;

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getServletContext().getInitParameter("encoding");
        contentType = filterConfig.getServletContext().getInitParameter("contentType");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}