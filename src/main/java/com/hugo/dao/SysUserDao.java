package com.hugo.dao;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hugo.common.CommonConstants;
import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.SysRole;
import com.hugo.entity.SysUser;
import com.hugo.entity.SysUserRole;
import com.hugo.util.ContextUtil;
import com.hugo.util.MD5Util;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Administrator on 2016/10/4.
 */
@Repository
public class SysUserDao extends BaseDaoImpl implements ISysUserDao {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public void saveUser(SysUser sysUser) throws Exception {
        if(null==sysUser.getUserId()||0==sysUser.getUserId()) {
            if (StringUtils.isNoneBlank(sysUser.getPassword())) sysUser.setPassword(MD5Util.MD5(sysUser.getPassword()));
            sysUser.setIsActivate(CommonConstants.IS_ACTIVATE_1);//已激活
            sysUser.setIsCheck(CommonConstants.IS_CHECK_1);//已审核通过
            sysUser.setIsDisable(CommonConstants.IS_DISABLE_0);//未禁用
            sysUser.setIsDelete(CommonConstants.IS_DELETE_0);//未删除
            sysUser.setCreateId(ContextUtil.getUserId());//创建人
            sysUser.setCreateTime(new Date());//创建时间
            sysUser.setModifyId(ContextUtil.getUserId());//修改人
            sysUser.setModifyTime(new Date());//修改时间
            sysUser.setLoginDate(new Date());
            Set<SysUserRole> roles = sysUser.getSysUserRoles();
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setSysUser(sysUser);
            if(null!=sysUser.getReleaseRole()){//编辑/作者
                if(1==sysUser.getReleaseRole()){
                    SysRole role = roleDao.findRoleKey(CommonConstants.ROLE_EDITOR);
                    sysUserRole.setSysRole(role);
                }else if(2==sysUser.getReleaseRole()){
                    SysRole role = roleDao.findRoleKey(CommonConstants.ROLE_AUTHOR);
                    sysUserRole.setSysRole(role);
                }else if(3==sysUser.getReleaseRole()){
                    SysRole role = roleDao.findRoleKey(CommonConstants.ROLE_ANGET);
                    sysUserRole.setSysRole(role);
                }
            }else if(null!=sysUser.getTranslatorType()){//翻译团队/翻译员
                SysRole role = roleDao.findRoleKey(CommonConstants.ROLE_TRANS);
                sysUserRole.setSysRole(role);
            }
            roles.add(sysUserRole);
            System.out.println(sysUser.getEmail()+">>>>>>>>>"+sysUser.getPhone());
            this.save(sysUser);
            System.out.println(sysUser.getUserId());
        }else {
            SysUser orgSysUser = this.findEntityById(SysUser.class, sysUser.getUserId());
            if (null != orgSysUser) {
                BeanUtils.copyProperties(orgSysUser, sysUser);
                orgSysUser.setModifyId(ContextUtil.getUserId());//修改人
                orgSysUser.setModifyTime(new Date());//修改时间
                this.update(orgSysUser);
            }
        }
    }

    @Override
    public SysUser loginSysUser(String username) {
        if (StringUtils.isNotBlank(username)) {
            String hql = "from SysUser where (username=:username or email=:username or phone=:username) and isDelete=:isDelete";
            Map<String,Object> paramMap = Maps.newHashMap();
            paramMap.put("username",username);
            paramMap.put("isDelete",CommonConstants.IS_DELETE_0);
            SysUser sysUser = (SysUser) this.getUniqueResult(hql, paramMap);
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser loginSysUser(String username, String password) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            String hql = "from SysUser where username = ? and password = ? and isDelete = ?";
            return (SysUser) this.getUniqueResult(hql, Arrays.asList(username, MD5Util.MD5(password), CommonConstants.IS_DELETE_0));
        }
        return null;
    }

    @Override
    public void updatePassword(Long userId, String password) {
        if (null != userId && StringUtils.isNotBlank(password)) {
            this.executeSql("update sys_user set password = ? where user_id = ?", Arrays.asList(MD5Util.MD5(password), userId));
        }
    }

    @Override
    public void saveNewPassword(String username, String password) {
        if (StringUtils.isNotBlank(username) &&StringUtils.isNotBlank(password)) {
            this.executeSql("update sys_user set password = ? where username = ?", Arrays.asList(MD5Util.MD5(password), username));

        }
    }

    @Override
    public Set<String> findRoles(String username) {
        SysUser user = this.loginSysUser(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        } else {
            String sql = "select a.role_key from sys_role a,sys_user_role b where a.role_id=b.role_id and b.user_id = ?";
            List<Map<String, Object>> list = this.getListByJdbcTemplate(sql, new Object[]{user.getUserId()});
            Set<String> sets = Sets.newHashSet();
            for (Map<String, Object> map : list) {
                sets.add(map.get("role_key").toString());
            }
            return sets;
        }
    }

    @Override
    public SysUser validateUsername(String username) {
        String hql = "from SysUser where username=:username";
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("username", username);
        return (SysUser)getUniqueResult(hql,paramMap);
    }

    @Override
    public SysUser validateEmail(String email) {
        String hql = "from SysUser where email=:email";
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("email", email);
        return (SysUser)getUniqueResult(hql,paramMap);
    }

    @Override
    public SysUser validatePhone(String phone) {
        String hql = "from SysUser where phone=:phone";
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("phone", phone);
        return (SysUser)getUniqueResult(hql,paramMap);
    }
}