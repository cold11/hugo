package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.dao.ITaskDao;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ohj on 2016/10/26.
 */
@Service
public class TaskService extends BaseService implements ITaskService {
    @Autowired
    private ITaskDao taskDao;
    @Override
    public Pager getTaskPager(Pager<TaskVO> pager) {
        return taskDao.getTaskPager(pager);
    }
}
