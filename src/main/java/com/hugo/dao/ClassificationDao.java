package com.hugo.dao;

import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.TBClassification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ohj on 2016/12/8.
 */
@Repository
public class ClassificationDao extends BaseDaoImpl implements IClassificationDao {
    @Override
    public List<TBClassification> getList() {
        String hql = "from TBClassification order by orderNum";
        return getListByHql(hql);
    }
}
