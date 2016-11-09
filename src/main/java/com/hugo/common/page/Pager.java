package com.hugo.common.page;


import com.hugo.common.util.Config;

import java.util.List;


public class Pager<T> {

	/**每页显示*/
	private int pageSize = Integer.valueOf(Config.getConfig("page.pageSize","20")); // 页面大小.
	/**页码*/
	private int pageNo = 1;
	private int totalPages;//总页数
	/**开始数*/
	private int start = 0;
	/**调整URL*/
	private String toUrl = "";
	
	private List<T> result;
	/**总条数*/
	private long totalRows = 0;
	
	private T condition;

    /**求和*/
    private Long[] sumTotal;
	public Pager(){
		
	}
	
	public Pager(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }
	
    /**
     * @param pageSize
     *          每页条数
     * @param pageNo
     *          页码（单前页码）
     * @param totalRows
     *          总条数
     * @param start
     *          开始条数
     * @param toUrl
     *          跳转的URL
     * @param result
     *          返回数据list
     */
	public Pager(int pageSize, int pageNo, long totalRows, int start, String toUrl, List<T> result){
		
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.totalRows = totalRows;
		this.start = start;
		this.toUrl = toUrl;
		this.result = result;
	}
	
    /**
     * <p>
     * 每一页的条数，默认10条
     * <p/>
     * @author LH
     */
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getStart() {
		this.start = (pageNo - 1) * pageSize;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public long getPageCount(){
		return getTotalRows() % getPageSize() == 0 ? getTotalRows() / getPageSize() : getTotalRows() / getPageSize() + 1;
	}


	/**
	 * 是否进行总数统计
	 * @return this.totalRows==-1
	 */
	public boolean isNotCount() {
		return this.totalRows==-1;
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult(){
		int firstResult = (getPageNo() - 1) * getPageSize();
		if (firstResult >= getTotalRows()) {
			firstResult = 0;
		}
		return firstResult;
	}

	public int getLastResult(){
		int lastResult = getFirstResult()+getMaxResults();
		if(lastResult>getTotalRows()) {
			lastResult =(int) getTotalRows();
		}
		return lastResult;
	}
	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults(){
		return getPageSize();
	}


	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}

	public int getTotalPages() {
		if(totalRows>0){
			totalPages  = (int)Math.ceil((double)totalRows/pageSize);
		}
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

    public Long[] getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(Long[] sumTotal) {
        this.sumTotal = sumTotal;
    }
}
