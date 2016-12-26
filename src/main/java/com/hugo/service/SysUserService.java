package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.dao.ISysUserDao;
import com.hugo.entity.SysUser;
import com.hugo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Administrator on 2016/10/4.
 */
@Service
public class SysUserService extends BaseService implements ISysUserService {
    @Autowired
    private ISysUserDao sysUserDao;

    @Override
    public void saveUser(SysUser sysUser) throws Exception {
        sysUserDao.saveUser(sysUser);
    }

    @Override
    public SysUser loginSysUser(String username) {
        return sysUserDao.loginSysUser(username);
    }

    @Override
    public SysUser loginSysUser(String username, String password) {
        return sysUserDao.loginSysUser(username,password);
    }

    @Override
    public void updatePassword(Long userId, String password) {
            sysUserDao.updatePassword(userId,password);
    }

    @Override
    public void saveNewPassword(String username, String password) {
        sysUserDao.saveNewPassword(username, password);
    }

    @Override
    public Set<String> findRoles(String username) {
        return sysUserDao.findRoles(username);
    }

    @Override
    public boolean checkUserRole(long userId, long roleId) {
        return sysUserDao.checkUserRole(userId,roleId);
    }

    @Override
    public SysUser validateUsername(String username) {
        return sysUserDao.validateUsername(username);
    }

    @Override
    public SysUser validateEmail(String email) {
        return sysUserDao.validateEmail(email);
    }

    @Override
    public SysUser validatePhone(String phone) {
        return sysUserDao.validatePhone(phone);
    }

    @Override
    public Pager getUserPager(Pager pager) {
        return sysUserDao.getUserPager(pager);
    }
}
