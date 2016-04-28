package com.hzc.top.dao;

import com.hzc.top.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名(手机号）查询用户
     * 返回用户
     *
     * @param phone
     * @return
     */
    SysUser selectByPhone(String phone);

    List<SysUser> selectList(@Param("user") SysUser user, @Param("system") int system);

    List<SysUser> selectList(@Param("user") SysUser user, @Param("system") int system, RowBounds rowBounds);

    /**
     * 根据用户的身份证号获取用户
     * @param idCard：身份证号
     * @param system：属于哪个系统：1、英语app，2、青岛司法局app，3、其他向后扩展
     * @return
     */
    SysUser selectByIdCard(@Param("idCard")String idCard, @Param("system")int system);

}