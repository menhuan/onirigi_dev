package com.test.demo.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ASUS
 * 创建时间  2017年8月27日 下午3:55:12
 *
 */
public class MarketRealInfoBean {
		
	/**主键id*/
	private Integer id;
	
	/**交易数量*/
	private BigDecimal tradeCount;
	
	/**
	 * 交易时间
	 */
	private String tradeTime ;

	/***
	 * 实时交易价格
	 */
	private BigDecimal  price;
	
	/**
	 * 交易时间
	 */
	private Date   time;

	/**
	 *创建时间
	 */
	private Integer createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(BigDecimal tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	
	
}
