package com.test.demo.bean;

import java.util.HashMap;
import java.util.Map;

import com.test.demo.async.EventType;

/**
 * 类型bean
 * @author ASUS
 * 创建时间  2017年9月16日 下午3:49:49
 *
 */
public class EventBean {
	
		/**
		 * 枚举事件类型
		 */
	 	private EventType type;
	 	
	 	/**
	 	 * 发给谁的
	 	 */
	    private int actorId;
	    
	    /**
	     * 实体id
	     */
	    private int entityId;
	    
	    /**
	     * 实体类型
	     */
	    private int entityType;
	    
	    /**
	     * 实体拥有者id
	     */
	    private int entityOwnerId;
	    
	    private Map<String, String> exts = new HashMap<>();

	    public Map<String, String> getExts() {
	        return exts;
	    }
	    public EventBean() {

	    }
	    public EventBean(EventType type) {
	        this.type = type;
	    }

	    public String getExt(String name) {
	        return exts.get(name);
	    }

	    public EventBean setExt(String name, String value) {
	        exts.put(name, value);
	        return this;
	    }

	    public EventType getType() {
	        return type;
	    }

	    public EventBean setType(EventType type) {
	        this.type = type;
	        return this;
	    }

	    public int getActorId() {
	        return actorId;
	    }

	    public EventBean setActorId(int actorId) {
	        this.actorId = actorId;
	        return this;
	    }

	    public int getEntityId() {
	        return entityId;
	    }

	    public EventBean setEntityId(int entityId) {
	        this.entityId = entityId;
	        return this;
	    }

	    public int getEntityType() {
	        return entityType;
	    }

	    public EventBean setEntityType(int entityType) {
	        this.entityType = entityType;
	        return this;
	    }

	    public int getEntityOwnerId() {
	        return entityOwnerId;
	    }

	    public EventBean setEntityOwnerId(int entityOwnerId) {
	        this.entityOwnerId = entityOwnerId;
	        return this;
	    }
}
