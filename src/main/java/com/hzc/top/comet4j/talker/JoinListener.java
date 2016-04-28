/*
 * Comet4J Copyright(c) 2011, http://code.google.com/p/comet4j/ This code is
 * licensed under BSD license. Use it as you wish, but keep this copyright
 * intact.
 */
package com.hzc.top.comet4j.talker;

import org.comet4j.core.CometConnection;
import org.comet4j.core.event.ConnectEvent;
import org.comet4j.core.listener.ConnectListener;
import com.hzc.top.comet4j.talker.dto.JoinDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 上线侦听
 *
 * @author jinghai.xiao@gmail.com
 * @date 2011-3-3
 */

public class JoinListener extends ConnectListener {

    /*
     * (non-Jsdoc)
     * @see org.comet4j.event.Listener#handleEvent(org.comet4j.event.Event)
     */
    @Override
    public boolean handleEvent(ConnectEvent anEvent) {
        CometConnection conn = anEvent.getConn();
        HttpServletRequest request = conn.getRequest();
        String userName2 = getCookieValue(request.getCookies(), "userName", "");
        String userName = request.getParameter("userName");
        if (null != userName2 && !"".equals(userName2)) {
            userName = userName2;
        }
        try {
            userName = URLDecoder.decode(userName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JoinDTO dto = new JoinDTO(conn.getId(), userName);
        AppStore.getInstance().put(conn.getId(), userName);
        anEvent.getTarget().sendToAll(Constant.APP_CHANNEL, dto);
        return true;
    }

    public String getCookieValue(Cookie[] cookies, String cookieName, String defaultValue) {
        String result = defaultValue;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return result;
    }

}
