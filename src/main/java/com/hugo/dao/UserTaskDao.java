package com.hugo.dao;

import com.google.common.collect.Maps;
import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.TBUserTask;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by ohj on 2016/11/11.
 */
@Repository
public class UserTaskDao extends BaseDaoImpl implements IUserTaskDao {
    @Override
    public TBUserTask getUserTask(String taskId, Long userId) {
        String hql = "from TBUserTask where sysUser.userId=:userId and tbTask.taskId=:taskId";
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("userId",userId);
        paramMap.put("taskId",taskId);
        TBUserTask tbUserTask = (TBUserTask) getUniqueResult(hql, paramMap);
        return tbUserTask;
    }
}
