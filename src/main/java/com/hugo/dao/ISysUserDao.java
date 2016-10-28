package com.hugo.dao;

import com.hugo.dao.base.IBaseDao;
import com.hugo.entity.SysUser;

import java.util.Set;

/**
 * Created by Administrator on 2016/10/4.
 */
public interface ISysUserDao extends IBaseDao{

    void saveUser(SysUser sysUser) throws Exception;
    /**
     * 登录用户
     * @param username
     * @return
     */
    SysUser loginSysUser(String username);

    /**
     * 验证用户名，密码
     * @param username
     * @param password
     * @return
     */
    SysUser loginSysUser(String username, String password);

    /**
     * 修改密码
     * @param userId
     * @param password
     */
    void updatePassword(Long userId, String password);

    /**
     * 修改新密码
     * @param username
     * @param password
     */
    void saveNewPassword(String username, String password);

    Set<String> findRoles(String username);

    SysUser validateUsername(String username);

    SysUser validateEmail(String email);

    SysUser validatePhone(String phone);
}