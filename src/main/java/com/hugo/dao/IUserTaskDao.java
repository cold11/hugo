package com.hugo.dao;

import com.hugo.common.page.Pager;
import com.hugo.dao.base.IBaseDao;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.TaskVO;

/**
 * Created by ohj on 2016/11/11.
 */
public interface IUserTaskDao extends IBaseDao {
    public TBUserTask getUserTask(String taskId,Long userId);
    public Pager getUserTaskPager(Pager pager);
}
