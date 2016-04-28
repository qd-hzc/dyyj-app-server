package com.hzc.top.ctrl.app;

import com.hzc.top.Const;
import com.hzc.top.model.AppVerifyCode;
import com.hzc.top.model.HsIntegral;
import com.hzc.top.model.SysMessage;
import com.hzc.top.model.SysUser;
import com.hzc.top.service.app.EnMqSendVerifyCodeService;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import com.hzc.top.util.utils.ChuanglanUtil;
import com.hzc.top.util.utils.SmsUtil;
import com.hzc.top.vo.ResultVO;
import com.hzc.framework.util.DesUtil;
import org.apache.commons.lang.StringUtils;

import javax.jms.JMSException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by FelixYin on 2015/2/27.
 */
public class SysUserCtrl {

    /**
     * 用户手机登录
     */
    public void login() throws Exception {
        String phone = W.getString("username");
        String password = W.getString("password");
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)
                || phone.equals("null") || password.equals("null")) {
            throw new IllegalArgumentException("phone or password is null");
        }

        ResultVO result = S.appUserService().login(phone, password);

//        记录加密解密数据用的key到session中
        if (result.isCode()) {
            String key = result.getMessage();
            String[] keys = key.replaceAll("，", ",").split(",");
            W.getSession().setAttribute(Const.DES_DATA_KEY, keys);
        }
        W.writeJson(result.isCode(), result.getMessage());
//        W.writeJsonObject(result);
    }

    /**
     * 青岛司法局 普法教育APP
     */
    public void loginForPufa() {
        String account = W.getString("username", "账号必填");
        int userId = S.appUserService().loginForPufa(account);
        W.writeJson(true, "登录成功");
    }

    /**
     * 用户手机注册
     */
    public void register() {
        String phone = W.getString("username");
        String verifycode = W.getString("verifycode");
        String password = W.getString("password1");
        String app = W.getString("app");
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(verifycode)
                || StringUtils.isBlank(password) || phone.equals("null")
                || verifycode.equals("null") || password.equals("null")) {
            throw new IllegalArgumentException("username , verifycode or password is null");
        }
        ResultVO result = S.appUserService().register(phone, password, verifycode, app, 1); //1 表示 英语系统
        W.writeJson(result.isCode(), result.getMessage());
    }

    /**
     * 获取某个用户的全部信息，根据phone
     */
    public void getUserInfo() {
        String phone = W.getString("phone");
        if (StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("phone is null");
        }
        SysUser userByPhone = S.appUserService().getUserByPhone(phone);
        W.writeJsonObject(userByPhone);
    }

    /**
     * 记录用户已经使用了多少小时
     */
    public void updateUserRemainTime() {
        String phone = W.getString("phone");
        Integer userTime = W.getInteger("userTime");
        boolean b = S.appUserService().updateLearnTime(phone, userTime);
        W.writeJson(b, "");
    }

    /**
     * 用户退出登录
     */
    public void logout() {
        String phone = W.getString("username");
        if (StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("phone is null");
        }
        ResultVO result = S.appUserService().logout(phone);
        W.writeJson(result.isCode(), result.getMessage());
    }

    /**
     * 查询手机号是否已存在（是否已注册）
     * 已注册返回true，未注册返回false
     */
    public void checkPhone() {
        String phone = W.getString("username");
        if (StringUtils.isBlank(phone) || phone.equals("null")) {
            throw new IllegalArgumentException("phone is null");
        }
        ResultVO result = S.appUserService().checkUserByPhone(phone);
        W.writeJson(result.isCode(), result.getMessage());
    }


    /**
     * 发送验证码到手机
     */
    public void sendVerifyCode() throws Exception {
        String phone = W.getString("username");
        String decCode = W.getString("code");
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(decCode)
                || phone.equals("null") || decCode.equals("null")) {
            throw new IllegalArgumentException("phone or decCode is null");
        }
        HttpSession session = W.getSession();
        String code = "";
        boolean yes = false;
        DesUtil desUtil = new DesUtil();
        String phoneDec = desUtil.strDec(phone, "q", "w", "e");
        String codeDec = desUtil.strDec(decCode, "2", "3", "4");
        String name = codeDec.substring(11);
        codeDec = codeDec.substring(0, 11);
        if (codeDec.equals(phoneDec) && name.equals("liu")) {
            Date now = new Date();
            AppVerifyCode appCode = (AppVerifyCode) session.getAttribute(phoneDec);
            if (appCode == null) {//第一次
                appCode = new AppVerifyCode();
                appCode.setPhone(phoneDec);
                appCode.setCreateTime(now);
                appCode.setTimes(1);
                session.setAttribute(phoneDec, appCode);
                yes = true;
            } else {
                Date createTime = appCode.getCreateTime();
                long nowTime = now.getTime();
                long createTimeTime = createTime.getTime();
                long cha = nowTime - createTimeTime;
                long minute = cha / (1000 * 60);
                int times = appCode.getTimes();
                if (minute >= 1) {//可以发送验证码
                    times++;
                    appCode.setCreateTime(now);
                    yes = true;
                } else {//验证是第几次发送
                    if (times < 20) {//可以发送
                        times++;
                        yes = true;
                    } else {//超过20次，不再发送
                        yes = false;
                    }
                }
                appCode.setTimes(times);
                session.setAttribute(phoneDec, appCode);
            }
            if (yes) {
                session.removeAttribute(phoneDec);
                String templateId = "3853";//短信模板id
                code = SmsUtil.sendMsg(phoneDec, templateId);
//                code = EnMqSendVerifyCodeService.sendMessage(phoneDec);
                AppVerifyCode verifyCodeBean = new AppVerifyCode();
                verifyCodeBean.setPhone(phoneDec);
                verifyCodeBean.setVerifyCode(code);
                S.appUserService().saveVerifyCode(verifyCodeBean);
                if (StringUtils.isBlank(code)) {
                    W.writeJson(false, "发送验证码失败，请重试");
                } else {
                    String result = code + phoneDec;
                    W.writeJson(true, result);
                }
            } else {
                W.writeJson(false, "发送验证码请求次数频繁，请稍候再试");
            }
        } else {
            W.writeJson(false, "系统繁忙，请稍候再试");
        }
    }

    /**
     * 发送验证码到手机
     */
    public void sendVerifyCodeChuanglan() throws Exception {
        String phone = W.getString("username");
        String decCode = W.getString("code");
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(decCode)
                || phone.equals("null") || decCode.equals("null")) {
            throw new IllegalArgumentException("phone or decCode is null");
        }
        HttpSession session = W.getSession();
        String code = "";
        boolean yes = false;
        DesUtil desUtil = new DesUtil();
        String phoneDec = desUtil.strDec(phone, "q", "w", "e");
        String codeDec = desUtil.strDec(decCode, "2", "3", "4");
        String name = codeDec.substring(11);
        codeDec = codeDec.substring(0, 11);
        if (codeDec.equals(phoneDec) && name.equals("liu")) {
            Date now = new Date();
            AppVerifyCode appCode = (AppVerifyCode) session.getAttribute(phoneDec);
            if (appCode == null) {//第一次
                appCode = new AppVerifyCode();
                appCode.setPhone(phoneDec);
                appCode.setCreateTime(now);
                appCode.setTimes(1);
                session.setAttribute(phoneDec, appCode);
                yes = true;
            } else {
                Date createTime = appCode.getCreateTime();
                long nowTime = now.getTime();
                long createTimeTime = createTime.getTime();
                long cha = nowTime - createTimeTime;
                long minute = cha / (1000 * 60);
                int times = appCode.getTimes();
                if (minute >= 1) {//可以发送验证码
                    times++;
                    appCode.setCreateTime(now);
                    yes = true;
                } else {//验证是第几次发送
                    if (times < 20) {//可以发送
                        times++;
                        yes = true;
                    } else {//超过20次，不再发送
                        yes = false;
                    }
                }
                appCode.setTimes(times);
                session.setAttribute(phoneDec, appCode);
            }
            if (yes) {
                session.removeAttribute(phoneDec);
//                String templateId = "3853";//短信模板id
//                code = SmsUtil.sendMsg(phoneDec, templateId);
//                code = EnMqSendVerifyCodeService.sendMessage(phoneDec);
                code = ChuanglanUtil.sentSms(phoneDec);
                AppVerifyCode verifyCodeBean = new AppVerifyCode();
                verifyCodeBean.setPhone(phoneDec);
                verifyCodeBean.setVerifyCode(code);
                S.appUserService().saveVerifyCode(verifyCodeBean);
                if (StringUtils.isBlank(code)) {
                    W.writeJson(false, "发送验证码失败，请重试");
                } else {
                    String result = code + phoneDec;
                    W.writeJson(true, result);
                }
            } else {
                W.writeJson(false, "发送验证码请求次数频繁，请稍候再试");
            }
        } else {
            W.writeJson(false, "系统繁忙，请稍候再试");
        }
    }

    /**
     * 修改用户密码
     */
    public void updatePassword() {
        String phone = W.getString("username");
        String code = W.getString("verifycode");
        String password = W.getString("password1");
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)
                || StringUtils.isBlank(password) || phone.equals("null")
                || code.equals("null") || password.equals("null")) {
            throw new IllegalArgumentException("phone , code or password is null");
        }
        ResultVO result = S.appUserService().updatePassword(phone, password, code);
        W.writeJson(result.isCode(), result.getMessage());
    }

    /**
     * 用户信息完善
     */
    public void completeUserInfo() {
        SysUser sysUser = W.packBean(SysUser.class);
        boolean isUpdate = S.appUserService().updateSysUser(sysUser);
        W.writeJson(isUpdate, "");

    }

    /**
     * 获取用户的所有的积分信息
     */
    public void getIntegral() {
        String userId = W.getString("userId");
        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId is null");
        }
        List<HsIntegral> list = S.hsIntegralService().getUserInfo(Integer.parseInt(userId));
        W.writeJsonArray(list);
    }

    /**
     * 更新用户的积分信息
     */
    public void saveIntegralForUser() {
        HsIntegral hsIntegral = W.packBean(HsIntegral.class);
        ResultVO resultVO = S.hsIntegralService().saveUserIntegral(hsIntegral);
        W.writeJson(resultVO.isCode(), resultVO.getMessage());
    }

    /**
     * 保存用户接受到的推送信息
     */
    public void savaSysMessage() {
        SysMessage sysMessage = W.packBean(SysMessage.class);
        ResultVO vo = S.sysMessageService().saveSysMessage(sysMessage);
    }

    /**
     * 托普助学系统发送验证码
     *
     * @throws Exception
     */
    public void sendCode() throws Exception {
        String phone = W.getString("phone");
        if (StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("phone is null");
        }
        String code = EnMqSendVerifyCodeService.sendMessage(phone);
        AppVerifyCode verifyCodeBean = new AppVerifyCode();
        verifyCodeBean.setPhone(phone);
        verifyCodeBean.setVerifyCode(code);
        S.appUserService().saveVerifyCode(verifyCodeBean);
//        String code = "789456";
        if (StringUtils.isBlank(code)) {
            W.writeJson(false, "发送验证码失败，请重试");
        } else {
            String result = code + "," + phone;
            W.writeJson(true, result);
        }
    }

    /**
     * 获取用户的可用积分
     */
    public void getUserAvailableIntegral() {
        String userId = W.getString("userId");
        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId is null");
        }
        int userAvailableIntegral = S.hsIntegralService().getUserAvailableIntegral(Integer.parseInt(userId));
        W.writeJson(true, String.valueOf(userAvailableIntegral));
    }
}
