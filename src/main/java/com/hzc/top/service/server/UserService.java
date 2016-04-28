package com.hzc.top.service.server;

import com.hzc.framework.ssh.repository.mybatis.DataTablePager;
import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.AppVerifyCode;
import com.hzc.top.model.SysUser;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.util.utils.MySecurity;
import com.hzc.top.util.utils.SecurityConstants;
import com.hzc.top.vo.ResultVO;
import com.hzc.top.vo.SystemEnum;

import java.util.List;
import java.util.Random;

/**
 * Created by paulliu88 on 2015/3/4.
 */
@Transaction
public class UserService {

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
     * @return
     */
    public ResultVO register(String phone, String password, String verifycode) {
        ResultVO resultVO;
        AppVerifyCode appVerifyCode = loadVerifyCodeByPhone(phone);
        if (appVerifyCode.getVerifyCode().equals(verifycode) && appVerifyCode.getPhone().equals(phone)) {
            SysUser user = getUserByPhone(phone);
            if (null == user) {
                password = MySecurity.encode_sda(SecurityConstants.LOGIN_PASSKEY, password);
                SysUser sysUser = new SysUser();
                sysUser.setPhone(phone);
                sysUser.setPasswd(password);
                D.sysUserMapper().insertSelective(sysUser);
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
                if (user.getDeleted() == 0 && user.getStatus() == 1) {
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
    private void updateSysUser(SysUser user) {
        D.sysUserMapper().updateByPrimaryKeySelective(user);
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

    @DataTablePager
    public List<SysUser> searchList(SysUser user) {
        return D.sysUserMapper().selectList(user, SystemEnum.HELP_STUDY.ordinal());
    }

    public int saveSysUser(SysUser user) {
        return D.sysUserMapper().insertSelective(user);
    }


}
