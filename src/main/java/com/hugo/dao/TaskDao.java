package com.hugo.dao;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hugo.common.page.Pager;
import com.hugo.common.util.DateUtil;
import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.EditorViewHistory;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBTask;
import com.hugo.model.vo.SysUserVO;
import com.hugo.model.vo.TaskVO;
import com.hugo.util.StringUtil;
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
            SysUserVO sysUserVO = taskVO.getUser();
            Map<String,Object> paramMap = Maps.newHashMap();
            String hql = "from TBTask";
            if(taskVO.getTaskType()!=null){
                paramMap.put("taskType",taskVO.getTaskType());
                hql+=" and taskType=:taskType";
            }
            if(taskVO.getTransExpirationDate()!=null){
                hql+=" and transExpirationDate>=now()";
            }
            if(StringUtils.isNotBlank(pager.getToUrl())){
                hql+=" and transExpirationDate<=now()";
            }
            if(taskVO.getTaskStatus()!=null){
                paramMap.put("taskStatus",taskVO.getTaskStatus());
                hql+=" and taskStatus=:taskStatus";
            }
            if(sysUserVO!=null&&sysUserVO.getUserId()!=null){
                paramMap.put("userId",sysUserVO.getUserId());
                hql+=" and sysUser.userId=:userId";
            }
            if(StringUtils.isNotBlank(taskVO.getCopyrightType())){
                paramMap.put("copyrightType",taskVO.getCopyrightType());
                hql+=" and copyrightType=:copyrightType";
            }
            if(StringUtils.isNotBlank(taskVO.getClassId())){
                paramMap.put("classid",taskVO.getClassId());
                hql+=" and classification.id=:classid";
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
                String bookIntroduction = task.getBookIntroduction();
                bookIntroduction = StringUtil.getSubString(bookIntroduction,200);
                TaskVO taskVO1 = new TaskVO();
                try {
                    BeanUtils.copyProperties(taskVO1,task);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                taskVO1.setBookIntroduction(bookIntroduction);
                SysUser sysUser = task.getSysUser();
                SysUserVO userVO = new SysUserVO();
                if(sysUser!=null){
                    userVO.setUserId(sysUser.getUserId());
                    userVO.setUsername(sysUser.getUsername());
                    userVO.setReleaseAgency(sysUser.getReleaseAgency());
                    userVO.setName(sysUser.getName());
                    userVO.setPhone(sysUser.getPhone());
                    userVO.setEmail(sysUser.getEmail());
                }
                taskVO1.setUser(userVO);

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

    @Override
    public void saveEditorHistory(TaskVO taskVO) {
        if(taskVO!=null){
            TBTask tbTask = this.findEntityById(TBTask.class,taskVO.getTaskId());
            Integer viewCount = tbTask.getViewCount();
            if(viewCount==null)
                tbTask.setViewCount(1);
            else tbTask.setViewCount(viewCount+1);
            EditorViewHistory editorViewHistory = new EditorViewHistory();
            SysUser sysUser = new SysUser();
            sysUser.setUserId(taskVO.getUser().getUserId());
            sysUser = findEntityById(SysUser.class,sysUser.getUserId());
            editorViewHistory.setTbTask(tbTask);
            editorViewHistory.setSysUser(sysUser);
            editorViewHistory.setCreateTime(new Date());
            this.save(editorViewHistory);
        }
    }
}
