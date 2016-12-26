package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.dao.IUserTaskDao;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ohj on 2016/11/11.
 */
@Service
public class UserTaskService extends BaseService implements IUserTaskService {
    @Autowired
    private IUserTaskDao userTaskDao;
    @Override
    public TBUserTask getUserTask(String taskId, Long userId) {
        return userTaskDao.getUserTask(taskId,userId);
    }

    @Override
    public List<TBUserTask> getUstasks(String taskId, Integer status) {
        return userTaskDao.getUstasks(taskId,status);
    }

    @Override
    public Pager getUserTaskPager(Pager pager) {
        return userTaskDao.getUserTaskPager(pager);
    }
}
