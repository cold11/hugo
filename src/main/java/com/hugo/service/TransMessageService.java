package com.hugo.service;

import com.hugo.common.page.Pager;
import com.hugo.dao.ITransMessageDao;
import com.hugo.entity.TBTransMessage;
import com.hugo.model.vo.TransMessageVO;
import com.hugo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ohj on 2016/12/9.
 */
@Service
public class TransMessageService extends BaseService implements ITransMessageService {
    @Autowired
    private ITransMessageDao transMessageDao;
    @Override
    public TBTransMessage findTransMessage(Long userId, Long inviteId){
        return transMessageDao.findTransMessage(userId,inviteId);
    }
    @Override
    public void save(TransMessageVO transMessageVO) {
        transMessageDao.save(transMessageVO);
    }

    @Override
    public Pager getTransMessagePager(Pager pager) {
        return transMessageDao.getTransMessagePager(pager);
    }
}
