package com.test.demo.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * mongodb 操作
 * @author ASUS
 * 创建时间  2017年10月21日 下午7:14:03
 *
 */
@Component
public class MongoDao {

	
	@Autowired
	public MongoTemplate  mongoTemplate;
	
	/**
	 * 保存 内容到指定的 库中
	 * @author ASUS
	 * 创建时间  2017年10月21日 下午7:16:29
	 * @param objectToSave  保存的对象
	 * @param collectionName 集合的名称
	 */
	public void save(Object objectToSave ,String  collectionName) {
		mongoTemplate.save(objectToSave, collectionName);
	}

	/**
	 * 批量插入
	 * @author ASUS
	 * 创建时间  2017年10月23日 下午10:00:02
	 * @param <T>
	 * @param batchToSave
	 * @param collectionName
	 */
	public <T> void saveList(List<T> batchToSave ,String collectionName) {
		mongoTemplate.insert(batchToSave, collectionName);
	}
	
	/**
	 * 查询  返回集合
	 * @author ASUS
	 * 创建时间  2017年10月21日 下午7:34:39
	 * @param collectionName  集合的名称
	 * @return
	 */
	public DBCursor  query(String collectionName) {
		DBCollection  collection =mongoTemplate.getCollection(collectionName);
		return collection.find();
	}
	
	/**
	 * 查询  返回集合
	 * @author ASUS
	 * 创建时间  2017年10月21日 下午7:36:01
	 * @param collectionName 集合的名称
	 * @return
	 */
	public List<DBObject>  queryToList(String collectionName) {
		
		List<DBObject>  list = new ArrayList<DBObject>();
		DBCollection  collection = mongoTemplate.getCollection(collectionName);
		DBCursor cursor = collection.find();
		while(cursor.hasNext()) {
			list.add(cursor.getQuery());
		}
		return list;
		
	}
	
	/**
	 * 删除整个集合
	 * @author ASUS
	 * 创建时间  2017年10月21日 下午7:39:14
	 * @param collectionName  集合的名称
	 */
	public void  removeCollection(String collectionName) {
		mongoTemplate.dropCollection(collectionName);
	}
	
	/**
	 * 移除集合中的某个对象
	 * @author ASUS
	 * 创建时间  2017年10月21日 下午7:40:43
	 * @param object
	 * @param collection
	 */
	public void removeValue(Object object ,String collection) {
		mongoTemplate.remove(object, collection);
	}
	
	
}
