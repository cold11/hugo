package com.hugo.dao;

import com.hugo.dao.base.IBaseDao;
import com.hugo.entity.TBUserTask;

/**
 * Created by ohj on 2016/11/11.
 */
public interface IUserTaskDao extends IBaseDao {
    public TBUserTask getUserTask(String taskId,Long userId);
}
