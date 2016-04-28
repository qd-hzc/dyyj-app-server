package com.hzc.top.ctrl.server;

import com.hzc.top.model.SysUser;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import com.hzc.framework.ssh.controller.WebUtil;
import com.hzc.top.vo.ResultVO;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LiuJY on 2015/3/20.
 */
public class UserCtrl {

    /**
     * 服务端登录后台
     */
    public void login() {
        String userName = WebUtil.getString("userName");
        String password = WebUtil.getString("password");
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("arguments are null");
        }
        if (userName.trim().equals("sunlaoshi") && password.trim().equals("111111")) { // 登录成功
//            WebUtil.forward("/WEB-INF/enrollment/enroll_manage.jsp");
            WebUtil.forward("/WEB-INF/pages/main.jsp");
        } else {
            HttpServletRequest req = WebUtil.getReq();
            req.setAttribute("message", "用户名密码错误");
            WebUtil.forward("/index.jsp");
        }
    }

    /**
     * 使用授权管理 列表
     */
    public void searchUserForEmpower() {
        SysUser sysUser = W.packBean(SysUser.class);
        List<SysUser> sysUsers = S.appUserService().searchEmpowerList(sysUser);
        W.writeJsonArray(sysUsers);
    }

    /**
     * 青岛司法局 App 用户管理列表
     */
    public void searchUserForPufaLexue() {
        SysUser sysUser = W.packBean(SysUser.class);
        List<SysUser> sysUsers = S.appUserService().searchPufaLexueList(sysUser);
        W.writeJsonArray(sysUsers);
    }

    /**
     * 托普报名 APP 用户管理列表
     */
    public void searchUserForBaoMing() {
        SysUser sysUser = W.packBean(SysUser.class);
        List<SysUser> sysUsers = S.appUserService().searchBaoMingList(sysUser);
        W.writeJsonArray(sysUsers);
    }

    /**
     * 更新用户可以使用app的时长
     */
    public void updateTime() {
        Integer id = W.getInteger("id");
        Integer time = W.getInteger("time");
        boolean b = S.appUserService().updateLearnTime(id, time);
        W.writeJson(b, "");
    }

    /**
     * 青岛司法局 普法教育APP
     */
    public void lcpf() {
        String account = W.getString("username", "账号必填");
        int userId = S.appUserService().loginForPufa(account);
        W.getSession().setAttribute("userId",userId);
        W.getSession().setAttribute("username",account);
//        W.forward("/WEB-INF/system/pufa/study.jsp");
        W.redirect("CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/aboutus.jsp");
    }
    /**
     * 青岛司法局 普法教育APP
     */
    public void lcpfForExam() {
        String account = W.getString("username", "账号必填");
        String password = W.getString("password", "密码必填");
        int userId = S.appUserService().loginForPufa(account);
        W.getSession().setAttribute("userId",userId);
        W.getSession().setAttribute("username",account);
//        W.forward("/WEB-INF/system/pufa/study.jsp");
        W.redirect("CommonCtrl.goTo.do?path=/WEB-INF/system/pufa/exam-pre.jsp");
    }

    /**
     * 李沧司法局的退出登录
     */
    public void logoutForLp(){
        W.getSession().removeAttribute("userId");
        W.forward("login_lp.jsp");
    }
}
