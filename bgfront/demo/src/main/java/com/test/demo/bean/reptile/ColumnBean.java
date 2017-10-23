package com.test.demo.bean.reptile;

import java.util.Date;

public class ColumnBean {


	public String  id ; 
	
	public String name ;
	
	public String bg_color;
	
	public String type ;
	
	public String introduction ;
	
	public Integer key_id ;
	
	public Date create_date;
	
	
	public Integer getKey_id() {
		return key_id;
	}

	public void setKey_id(Integer key_id) {
		this.key_id = key_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getBg_color() {
		return bg_color;
	}

	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
}
