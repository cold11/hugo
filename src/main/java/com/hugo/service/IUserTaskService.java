package com.hugo.service;

import com.hugo.entity.TBUserTask;
import com.hugo.service.base.IBaseService;

/**
 * Created by ohj on 2016/11/11.
 */
public interface IUserTaskService extends IBaseService {

    public TBUserTask getUserTask(String taskId,Long userId);
}
