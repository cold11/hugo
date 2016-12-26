package com.hugo.dao;

import com.hugo.dao.base.IBaseDao;
import com.hugo.entity.TBClassification;

import java.util.List;

/**
 * Created by ohj on 2016/12/8.
 */
public interface IClassificationDao extends IBaseDao {
    List<TBClassification> getList();
}
