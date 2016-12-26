package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.entity.SysUser;
import com.hugo.service.base.IBaseService;

import java.util.Set;

/**
 * Created by Administrator on 2016/10/4.
 */
public interface ISysUserService extends IBaseService {

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
    /**
     * 检查用户角色
     * @param userId
     * @param roleId
     * @return
     */
    boolean checkUserRole(long userId,long roleId);
    SysUser validateUsername(String username);

    SysUser validateEmail(String email);

    SysUser validatePhone(String phone);
    Pager getUserPager(Pager pager);
}
