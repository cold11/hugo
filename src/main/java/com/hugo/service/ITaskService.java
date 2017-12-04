package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.model.vo.TaskVO;
import com.hugo.service.base.IBaseService;

/**
 * Created by ohj on 2016/10/26.
 */
public interface ITaskService extends IBaseService {
    Pager getTaskPager(Pager<TaskVO> pager);
    void saveEditorHistory(TaskVO taskVO);
}
