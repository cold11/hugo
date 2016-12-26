package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.base.IBaseService;

import java.util.List;

/**
 * Created by ohj on 2016/11/11.
 */
public interface IUserTaskService extends IBaseService {

    TBUserTask getUserTask(String taskId,Long userId);
    List<TBUserTask> getUstasks(String taskId,Integer status);
    Pager getUserTaskPager(Pager pager);
}
