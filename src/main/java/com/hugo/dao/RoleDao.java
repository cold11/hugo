package com.hugo.dao;

import com.google.common.collect.Maps;
import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/6.
 */
@Repository
public class RoleDao extends BaseDaoImpl implements IRoleDao {
    @Override
    public SysRole findRoleKey(String roleKey) {
        String hql = "from SysRole where roleKey=:roleKey";
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("roleKey",roleKey);
        return (SysRole)getUniqueResult(hql,paramMap);
    }
}
