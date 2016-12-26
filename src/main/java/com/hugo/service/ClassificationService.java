package com.hugo.service;

import com.hugo.dao.IClassificationDao;
import com.hugo.entity.TBClassification;
import com.hugo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ohj on 2016/12/8.
 */
@Service
public class ClassificationService extends BaseService implements IClassificationService {
    @Autowired
    private IClassificationDao classificationDao;
    @Override
    public List<TBClassification> getList() {
        return classificationDao.getList();
    }
}
