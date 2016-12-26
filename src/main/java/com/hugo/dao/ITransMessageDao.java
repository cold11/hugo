package com.hugo.dao;

import com.hugo.common.page.Pager;
import com.hugo.dao.base.IBaseDao;
import com.hugo.entity.TBTransMessage;
import com.hugo.model.vo.TransMessageVO;

/**
 * Created by ohj on 2016/12/9.
 */
public interface ITransMessageDao extends IBaseDao {
    TBTransMessage findTransMessage(Long userId, Long inviteId);
    void save(TransMessageVO transMessageVO);
    Pager getTransMessagePager(Pager pager);
}
