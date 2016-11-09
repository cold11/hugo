package com.hugo.dao;

import com.hugo.common.page.Pager;
import com.hugo.dao.base.IBaseDao;
import com.hugo.model.vo.TaskVO;

/**
 * Created by ohj on 2016/10/26.
 */
public interface ITaskDao extends IBaseDao {
    Pager getTaskPager(Pager<TaskVO> pager);
}
