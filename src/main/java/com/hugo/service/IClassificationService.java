package com.hugo.service;

import com.hugo.entity.TBClassification;
import com.hugo.service.base.IBaseService;

import java.util.List;

/**
 * Created by ohj on 2016/12/8.
 */
public interface IClassificationService extends IBaseService {
    List<TBClassification> getList();
}
