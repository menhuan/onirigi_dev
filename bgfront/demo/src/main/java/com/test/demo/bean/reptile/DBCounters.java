package com.test.demo.bean.reptile;

import java.util.Date;

public class DBCounters {
	
	public Integer key_id;

	public String id ;
	 
	public String entity_type ;
	 
	public String entity_id ;
	 
	public String count_type ;
	 
	public String value ;
	
	public Date  create_date;
	

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getCount_type() {
		return count_type;
	}

	public void setCount_type(String count_type) {
		this.count_type = count_type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	 
	
}
