package com.test.demo.bean.imooc;

/**
 * 分页bean
 * @author ASUS
 * 创建时间  2017年10月14日 下午2:48:45
 *
 */
public class PageBean  {

	/**
	 * 当前页码数
	 */
	public Integer pageNum = 1 ;
	
	/**
	 * 分页大小
	 */
	public Integer pageSize = 10;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
	
}
