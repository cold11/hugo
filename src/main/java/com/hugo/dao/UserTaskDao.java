package com.hugo.dao;

import com.google.common.collect.Maps;
import com.hugo.common.page.Pager;
import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.TBUserTask;
import com.hugo.model.vo.TaskVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ohj on 2016/11/11.
 */
@Repository
public class UserTaskDao extends BaseDaoImpl implements IUserTaskDao {
    @Override
    public TBUserTask getUserTask(String taskId, Long userId) {
        String hql = "from TBUserTask where sysUser.userId=:userId and tbTask.taskId=:taskId";
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("userId",userId);
        paramMap.put("taskId",taskId);
        TBUserTask tbUserTask = (TBUserTask) getUniqueResult(hql, paramMap);
        return tbUserTask;
    }

    @Override
    public Pager getUserTaskPager(Pager pager) {
        TaskVO taskVO = (TaskVO)pager.getCondition();
        if(taskVO!=null){
            String hql = "from TBUserTask";
            Map<String,Object> paramMap = Maps.newHashMap();
            if(taskVO.getUser()!=null&&taskVO.getUser().getUserId()!=null){
                hql+=" and sysUser.userId=:userId";
                paramMap.put("userId",taskVO.getUser().getUserId());
            }
            if(taskVO.getTaskStatus()!=null){
                hql+=" and status=:status";
                paramMap.put("status",taskVO.getTaskStatus());
            }
            if(!paramMap.isEmpty()){
                hql = hql.replaceFirst("and","where");
            }
            String hqlCount = "select count(1) "+hql;
            if (StringUtils.isNotBlank(taskVO.getSort())) {
                hql += " order by " + taskVO.getSort();
                if (StringUtils.isNotBlank(taskVO.getOrder())) {
                    hql += " " + taskVO.getOrder();
                }
            } else {
                hql += " order by createTime desc";
            }
            int total = getCountByHqlParamMap(hqlCount, paramMap);
            List<TBUserTask> list = getPageListByParamMap(hql,paramMap,pager.getPageNo(),pager.getPageSize());
            pager.setResult(list);
            pager.setTotalRows(total);
            return pager;
        }
        return null;
    }
}
