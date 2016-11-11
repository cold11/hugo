package com.hugo.service;

import com.hugo.entity.TBUserTask;
import com.hugo.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created by ohj on 2016/11/11.
 */
@Service
public class UserTaskService extends BaseService implements IUserTaskService {
    @Override
    public TBUserTask getUserTask(String taskId, Long userId) {
        return null;
    }
}
