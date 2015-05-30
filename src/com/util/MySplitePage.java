package com.util;

public class MySplitePage {
	private int totalPage=0;//总页数
	private int pageSize=10;//每页的显示记录数
	private int currentPage=1;//当前页
	private long totalRecords=0;
	public int getTotalPage() {
		return totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		totalPage=(int) (totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1);
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	//当总数发生变化时，要更新当前页
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
		totalPage=(int) (totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1);
	}
	public void setPage(int flag){
		if(flag>totalPage)
			currentPage=totalPage;
		else if(flag<=0)
			currentPage=1;
		else
			currentPage=flag;
	}
}
