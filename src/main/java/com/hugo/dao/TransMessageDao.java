package com.hugo.dao;

import com.google.common.collect.Maps;
import com.hugo.common.CommonConstants;
import com.hugo.common.page.Pager;
import com.hugo.common.util.BeanUtilsEx;
import com.hugo.dao.base.BaseDaoImpl;
import com.hugo.entity.SysUser;
import com.hugo.entity.TBTransMessage;
import com.hugo.model.vo.TransMessageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ohj on 2016/12/9.
 */
@Repository
public class TransMessageDao extends BaseDaoImpl implements ITransMessageDao {
    @Override
    public TBTransMessage findTransMessage(Long userId, Long inviteId) {
        String hql = " from TBTransMessage where sysUser.userId=:userId and inviteUser.userId=:inviteId";
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("userId",userId);
        paramMap.put("inviteId",inviteId);
        List<TBTransMessage> list = getListByHqlParamMap(hql,paramMap);
        if(list!=null&&!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void save(TransMessageVO transMessageVO) {
        Long inviteId = transMessageVO.getInviteId();
        Long userId = transMessageVO.getUserId();
        TBTransMessage transMessage = new TBTransMessage();
        transMessage.setCreateTime(new Date());
        transMessage.setStatus(CommonConstants.TASK_STATUS_0);
        SysUser sysUser = findEntityById(SysUser.class,userId);
        SysUser inviteUser = findEntityById(SysUser.class,inviteId);
        transMessage.setSysUser(sysUser);
        transMessage.setInviteUser(inviteUser);
        BeanUtilsEx.copyNotNullProperties(transMessage,transMessageVO);
        save(transMessage);

    }

    @Override
    public Pager getTransMessagePager(Pager pager) {
        TransMessageVO transMessageVO = (TransMessageVO) pager.getCondition();
        if(transMessageVO!=null){
            Map<String,Object> paramMap = Maps.newHashMap();
            String hql = "from TBTransMessage";
            if(transMessageVO.getInviteId()!=null){
                paramMap.put("inviteId",transMessageVO.getInviteId());
                hql+=" and inviteUser.userId=:inviteId";
            }
            if(transMessageVO.getUserId()!=null){
                paramMap.put("userId",transMessageVO.getUserId());
                hql+=" and sysUser.userId=:userId";
            }
            if(transMessageVO.getStatus()!=null){
                paramMap.put("status",transMessageVO.getStatus());
                hql+=" and status=:status";
            }
            if(!paramMap.isEmpty()){
                hql = hql.replaceFirst("and","where");
            }
            String hqlCount = "select count(1) "+hql;
            if (StringUtils.isNotBlank(transMessageVO.getSort())) {
                hql += " order by " + transMessageVO.getSort();
                if (StringUtils.isNotBlank(transMessageVO.getOrder())) {
                    hql += " " + transMessageVO.getOrder();
                }
            } else {
                hql += " order by createTime desc";
            }
            int total = getCountByHqlParamMap(hqlCount, paramMap);
            List<TBTransMessage> list = getPageListByParamMap(hql,paramMap,pager.getPageNo(),pager.getPageSize());
            pager.setResult(list);
            pager.setTotalRows(total);
        }
        return pager;
    }
}
