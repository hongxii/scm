package com.scm.model;

import java.util.List;
import com.scm.dao.SOMainDao;

//用来分页的
public class Page {
	private int currentPage;//当前页
	private int allPage;//总页数
	private int start;//起始的条数
	private int count=4;//每页显示的条数
	private int allCount;//数据库中数据总条数
	private List pageMessage; //页面上显示的信息
	
	public Page(){}
	
	public Page(String tableName){
		//计算总的页数（总页数=总条数/每页显示的数据条数    余数不为0 那么+1）
		//（1）获得总的条数
		allCount= SOMainDao.getCount(tableName);
		//(2)获得总页数
		if(allCount%4==0){
			allPage=allCount/count;
		}else{
			allPage=allCount/count+1;
		}
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public List getPageMessage() {
		return pageMessage;
	}

	public void setPageMessage(List pageMessage) {
		this.pageMessage = pageMessage;
	}

	@Override
	public String toString() {
		return "Page{" +
				"currentPage=" + currentPage +
				", allPage=" + allPage +
				", start=" + start +
				", count=" + count +
				", allCount=" + allCount +
				", pageMessage=" + pageMessage +
				'}';
	}
}
