package com.test.demo.bean.kr;

import java.util.Date;

/**
 * 存储 html链接
 * @author ASUS
 * 创建时间  2017年10月21日 下午8:02:41
 *
 */
public class HtmlBean {

	public Integer id ;

	/**
	 * html 链接
	 */
	public String html; 
	
	/**
	 * html id 链接
	 */
	public Integer htmlId;
	
	public Date  createDate ;
	
	public Date  updatedDate ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Integer getHtmlId() {
		return htmlId;
	}

	public void setHtmlId(Integer htmlId) {
		this.htmlId = htmlId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
