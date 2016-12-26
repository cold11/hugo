package com.hugo.dao;

import com.hugo.common.page.Pager;
import com.hugo.dao.base.IBaseDao;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.TaskVO;

import java.util.List;

/**
 * Created by ohj on 2016/11/11.
 */
public interface IUserTaskDao extends IBaseDao {
    TBUserTask getUserTask(String taskId, Long userId);

    List<TBUserTask> getUstasks(String taskId,Integer status);

    Pager getUserTaskPager(Pager pager);
}
