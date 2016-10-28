package com.hugo.dao;

import com.hugo.dao.base.IBaseDao;
import com.hugo.entity.SysRole;

/**
 * Created by Administrator on 2016/10/6.
 */
public interface IRoleDao extends IBaseDao {

    SysRole findRoleKey(String roleKey);
}
