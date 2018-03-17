package com.test.demo.bean.imooc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 字典bean
 * @author ASUS
 * 创建时间  2017年12月23日 上午10:22:04
 *
 */
@JsonInclude(Include.NON_NULL)
public class DicBean {

	/** 
	 * 类型   type与code 构成 唯一标识
	 */
	private String type;
	
	/**
	 * 编码
	 */
	private String code;
	
	/**
	 * 名字 
	 */
	private String name ;
	
	/**
	 * 权重
	 */
	private Integer weight ;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
