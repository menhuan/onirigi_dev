package com.test.demo.bean.reptile;

import java.util.Date;

/**
 * 猜测 角色
 * @author dell
 *
 */
public class Role {

	public String id ;
	
	public String name ;
	
	public String description;
	
	public String user_id_server;
	
	//这里可能是 数组 或者是别的
	public String available_domain_ids;
	
	public String server ;

	public Date create_date;
	
	public Integer key_id ;
	
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getKey_id() {
		return key_id;
	}

	public void setKey_id(Integer key_id) {
		this.key_id = key_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser_id_server() {
		return user_id_server;
	}

	public void setUser_id_server(String user_id_server) {
		this.user_id_server = user_id_server;
	}

	public String getAvailable_domain_ids() {
		return available_domain_ids;
	}

	public void setAvailable_domain_ids(String available_domain_ids) {
		this.available_domain_ids = available_domain_ids;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
}
