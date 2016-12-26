package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.entity.TBTransMessage;
import com.hugo.model.vo.TransMessageVO;
import com.hugo.service.base.IBaseService;

/**
 * Created by ohj on 2016/12/9.
 */
public interface ITransMessageService extends IBaseService {

    TBTransMessage findTransMessage(Long userId, Long inviteId);
    void save(TransMessageVO transMessageVO);
    Pager getTransMessagePager(Pager pager);
}
