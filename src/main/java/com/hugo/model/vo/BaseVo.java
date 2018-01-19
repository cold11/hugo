package com.hugo.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ohj on 2016/11/7.
 */
public class BaseVo implements Serializable {

    //@DateTimeFormat
    protected Date beginTime;
    //@DateTimeFormat
    protected Date endTime;

    private Integer pageNo;
    private Integer pageSize;
    private String sort;
    private String order;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
