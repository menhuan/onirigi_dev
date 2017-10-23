package com.test.demo.dao.kr;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.demo.bean.kr.HtmlBean;

/**
 * 
 * @author ASUS
 * 创建时间  2017年10月21日 下午8:11:06
 *
 */
public interface HtmlDao {
	
	/**
	 * 增加html 链接爬虫
	 * @author ASUS
	 * 创建时间  2017年10月21日 下午8:11:34
	 * @throws Exception
	 */
	public void addHtml(@Param("beans") List<HtmlBean> beans) throws Exception ;
	
	
}
