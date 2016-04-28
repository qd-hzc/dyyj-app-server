package com.hzc.top.service.app;

import com.hzc.top.model.AppVerifyCode;
import com.hzc.top.model.HsIntegral;
import com.hzc.top.model.HsSetting;
import com.hzc.top.model.SysUser;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.utils.MySecurity;
import com.hzc.top.util.utils.SecurityConstants;
import com.hzc.top.vo.ResultVO;
import com.hzc.framework.ssh.repository.mybatis.DataTablePager;
import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.vo.SystemEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by paulliu88 on 2015/3/4.
 */
@Transaction
public class SysUserService {

    /**
     * 插入一条新的验证码信息
     *
     * @param record
     * @return
     */
    public int saveVerifyCode(AppVerifyCode record) {
        return D.appVerifyCodeMapper().insertSelective(record);
    }

    /**
     * 根据手机号查询短信验证码信息
     *
     * @param phone
     * @return
     */
    public AppVerifyCode loadVerifyCodeByPhone(String phone) {
        return D.appVerifyCodeMapper().selectByPhone(phone);
    }

    /**
     * 手机端用户登录
     * 传入登录用户名（手机号）和密码（明文）
     * 判断用户名和密码是否匹配
     * 返回登录结果：1、登录成功返回加密算法的key；2、用户名不存在；3、账号密码错误
     *
     * @param phone
     * @param password
     * @return
     */
    public ResultVO login(String phone, String password) {
        ResultVO resultVO;
        SysUser user = getUserByPhone(phone);
        if (null != user) {//此用户存在
            password = MySecurity.encode_sda(SecurityConstants.LOGIN_PASSKEY, password);
            if (user.getPasswd().equals(password)) {
                if (user.getStatus() == 1 && user.getDeleted() == 1) {
//                    if (user.getOnline() == 1) {//登录成功
                    Random random = new Random();
                    int i = 1;
                    String key = "";
                    while (i < 4) {
                        key = String.valueOf(random.nextInt(10)) + "," + key;
                        i++;
                    }
                    user.setKey(key);
                    user.setOnline(1);
                    updateSysUser(user);
//                        SysUser allUser = getUserByPhone(phone);
                    resultVO = new ResultVO(true, key);
//                    } else {//该用户已经登录
//                        resultVO = new ResultVO(false, "用户已经登录");
//                    }
                } else {
                    resultVO = new ResultVO(false, "此用户已被锁定");
                }
            } else {
                resultVO = new ResultVO(false, "用户名密码错误");
            }
        } else {
            resultVO = new ResultVO(false, "用户名不存在");
        }
        return resultVO;
    }

    /**
     * 查询用户是否已经注册
     * 传入查询用户名（手机号）
     * 匹配数据库是否存在此账号
     * 返回查询结果：1、已存在；2、不存在
     *
     * @param phone
     * @return
     */
    public ResultVO checkUserByPhone(String phone) {
        ResultVO resultVO;
        SysUser user = getUserByPhone(phone);
        if (null != user) {
            resultVO = new ResultVO(true, "此账号已存在");
        } else {
            resultVO = new ResultVO(false, "此帐号未被注册");
        }
        return resultVO;
    }

    /**
     * 根据用户名（手机）获取用户信息
     * 返回用户信息
     *
     * @param phone
     * @return
     */
    public SysUser getUserByPhone(String phone) {
        return D.sysUserMapper().selectByPhone(phone);
    }

    /**
     * 手机端用户注册
     * 传入注册用户名（手机号）、密码（明文）、验证码（明文）
     * 返回注册结果：1、注册成功；2、验证码错误；
     *
     * @param phone
     * @param password
     * @param verifycode
     * @param app        哪个app的用户注册
     * @return
     */
    public ResultVO register(String phone, String password, String verifycode, String app, int system) {
        ResultVO resultVO;
        AppVerifyCode appVerifyCode = loadVerifyCodeByPhone(phone);
        if (appVerifyCode.getVerifyCode().equals(verifycode) && appVerifyCode.getPhone().equals(phone)) {
            SysUser user = getUserByPhone(phone);
            if (null == user) {
                password = MySecurity.encode_sda(SecurityConstants.LOGIN_PASSKEY, password);
                SysUser sysUser = new SysUser();
                sysUser.setPhone(phone);
                sysUser.setPasswd(password);
                sysUser.setSystem(system);

                //托普助学app用户注册
                if (StringUtils.isNotBlank(app) && app.equals("hs")) {
                    HsSetting hsSetting = S.hsSettingService().load();
                    saveSysUser(sysUser);
                    Integer aSwitch = hsSetting.getItglSignSwitch();
                    if (aSwitch == 1) {//开启注册、签到送积分
                        Integer itglSignVal = hsSetting.getItglSignVal();
                        user = getUserByPhone(phone);
                        Integer userId = user.getId();
                        HsIntegral integral = new HsIntegral();
                        integral.setUserId(userId);
                        integral.setSourceType(5);
                        integral.setIntegralVal(itglSignVal);
                        S.hsIntegralService().saveUserIntegral(integral);
                    }

                }else {
                    saveSysUser(sysUser);
                }
                resultVO = new ResultVO(true, "注册成功");
            } else {
                resultVO = new ResultVO(false, "用户已存在");
            }
        } else {
            resultVO = new ResultVO(false, "验证码错误");
        }
        return resultVO;
    }

    /**
     * 修改手机端用户的密码
     * 传入修改用户的信息，用户名（手机号）、新密码（明文）、验证码（明文）
     * 返回修改结果：1、修改密码成功；2、验证码错误；
     *
     * @param phone
     * @param password
     * @param code
     * @return
     */
    public ResultVO updatePassword(String phone, String password, String code) {
        ResultVO resultVo;
        AppVerifyCode appVerifyCode = loadVerifyCodeByPhone(phone);
        if (appVerifyCode.getVerifyCode().equals(code) && appVerifyCode.getPhone().equals(phone)) {
            SysUser user = getUserByPhone(phone);
            if (null != user) {
                if (user.getDeleted() == 1 && user.getStatus() == 1) {
                    password = MySecurity.encode_sda(SecurityConstants.LOGIN_PASSKEY, password);
                    user.setPasswd(password);
                    updateSysUser(user);
                    resultVo = new ResultVO(true, "更新密码成功");
                } else {
                    resultVo = new ResultVO(false, "用户已被锁定");
                }
            } else {
                resultVo = new ResultVO(false, "用户不存在");
            }
        } else {
            resultVo = new ResultVO(false, "验证码错误");
        }
        return resultVo;
    }

    /**
     * 更新 用户的可以使用的时间，单位为分钟
     *
     * @param id
     * @param time
     * @return
     */
    public boolean updateLearnTime(Integer id, Integer time) {
//        SysUser sysUser = D.sysUserMapper().selectByPrimaryKey(id);
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setAvailableTime(time);
        sysUser.setUpdatetime(new Date());
        int i = D.sysUserMapper().updateByPrimaryKeySelective(sysUser);
        return i == 1;
    }

    /**
     * 更新 用户的可以使用的时间，单位为分钟
     *
     * @param phone
     * @param time
     * @return
     */
    public boolean updateLearnTime(String phone, Integer time) {
        SysUser sysUser = D.sysUserMapper().selectByPhone(phone);
        sysUser.setRemainTime(time);
        int i = D.sysUserMapper().updateByPrimaryKeySelective(sysUser);
        return i == 1;
    }

    /**
     * 根据id更新用户（SysUser）
     *
     * @param user
     */
    public boolean updateSysUser(SysUser user) {
        return D.sysUserMapper().updateByPrimaryKeySelective(user) == 1;
    }

    /**
     * 用户退出
     *
     * @param phone
     * @return
     */
    public ResultVO logout(String phone) {
        SysUser user = getUserByPhone(phone);
        user.setOnline(0);
        updateSysUser(user);
        return new ResultVO(true, "退出成功");
    }

    /**
     * 李沧司法局干部普法app，后台收集、管理用户
     * 分页管理列表
     *
     * @param user
     * @return
     */
    @DataTablePager
    public List<SysUser> searchEmpowerList(SysUser user) {
        return D.sysUserMapper().selectList(user, SystemEnum.HELP_STUDY.getValue());
    }

    /**
     * 李沧干部普法app，后台收集、管理用户
     * 分页管理列表
     *
     * @param user
     * @return
     */
    @DataTablePager
    public List<SysUser> searchPufaLexueList(SysUser user) {
        return D.sysUserMapper().selectList(user, SystemEnum.PUFA_LEXU.getValue());
    }

    @DataTablePager
    public List<SysUser> searchBaoMingList(SysUser user) {
        return D.sysUserMapper().selectList(user, SystemEnum.BAO_MING.getValue());
    }

    /**
     * 青岛司法局 普法教育APP
     * 根据用户身份证号查询用户，如果用户存在，则返回userId；
     * 用户不存在，则注册此用户，返回userId
     *
     * @param account（身份证号）
     * @return
     */
    public int loginForPufa(String account) {
        //2：青岛李沧司法局普法app
        SysUser user = getUserByIdCard(account, 2);
        int userId = 0;
        if (null == user) {//此用户不存在，则注册此用户
            user = new SysUser();
            user.setIdcard(account);
            user.setSystem(2);
            user.setCreateTime(new Date());
            user.setAvailableTime(6000);
            saveSysUser(user);
            user = getUserByIdCard(account, 2);
        }
        userId = user.getId();

        if (userId == 0) {
            throw new RuntimeException("something was wrong");
        }
        return userId;
    }

    /**
     * 根据用户的身份证号获取用户
     *
     * @param idCard：身份证号
     * @param system：属于哪个系统：1、英语app，2、青岛司法局app，3、其他向后扩展
     * @return
     */
    public SysUser getUserByIdCard(String idCard, int system) {
        return D.sysUserMapper().selectByIdCard(idCard, system);
    }

    /**
     * 保存用户信息
     * 返回userId
     *
     * @param sysUser
     * @return
     */
    public int saveSysUser(SysUser sysUser) {
        return D.sysUserMapper().insertSelective(sysUser);
    }

}
