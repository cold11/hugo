package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.base.IBaseService;

/**
 * Created by ohj on 2016/11/11.
 */
public interface IUserTaskService extends IBaseService {

    public TBUserTask getUserTask(String taskId,Long userId);
    public Pager getUserTaskPager(Pager pager);
}
