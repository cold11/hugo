package com.hugo.dao;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hugo.common.page.Pager;
import com.hugo.common.util.DateUtil;
import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.TBTask;
import com.hugo.model.vo.TaskVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ohj on 2016/10/26.
 */
@Repository
public class TaskDao extends BaseDaoImpl implements ITaskDao {

    @Override
    public Pager getTaskPager(Pager<TaskVO> pager) {
        TaskVO taskVO = pager.getCondition();
        if(taskVO!=null){
            Map<String,Object> paramMap = Maps.newHashMap();
            String hql = "from TBTask";
            if(taskVO.getTaskType()!=null){
                paramMap.put("taskType",taskVO.getTaskType());
                hql+=" and taskType=:taskType";
            }
            if(taskVO.getTaskStatus()!=null){
                paramMap.put("taskStatus",taskVO.getTaskStatus());
                hql+=" and taskStatus=:taskStatus";
            }
            if(taskVO.getUserId()!=null){
                paramMap.put("userId",taskVO.getUserId());
                hql+=" and sysUser.userId=:userId";
            }
            if(StringUtils.isNotBlank(taskVO.getSourceLanguage())){
                paramMap.put("sourceLanguage",taskVO.getSourceLanguage());
                hql+=" and sourceLanguage=:sourceLanguage";
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
            List<TaskVO> taskVOs = Lists.newArrayList();
            List<TBTask> tbTasks = getPageListByParamMap(hql,paramMap,pager.getPageNo(),pager.getPageSize());
            for (TBTask task : tbTasks){
                TaskVO taskVO1 = new TaskVO();
                try {
                    BeanUtils.copyProperties(taskVO1,task);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                taskVO1.setUserId(task.getSysUser().getUserId());
                if(taskVO1.getTransExpirationDate()!=null){
                    taskVO1.setTransExpirationDateStr(DateUtil.toString(taskVO1.getTransExpirationDate(),"yyyy/MM/dd,HH:mm:ss"));
                }
                taskVOs.add(taskVO1);
            }
            pager.setResult(taskVOs);
            pager.setTotalRows(total);
            return pager;
        }
        return null;
    }
}
