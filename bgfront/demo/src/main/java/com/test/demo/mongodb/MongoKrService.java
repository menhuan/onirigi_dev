package com.test.demo.mongodb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.demo.base.BaseCofig;
import com.test.demo.bean.reptile.BossJobBean;
import com.test.demo.bean.reptile.DetailAriticleBean;
import com.test.demo.bean.reptile.HotPostsBean;
import com.test.demo.bean.reptile.abTestValue;

/**
 * 根据不同的入库到mongo 中
 * @author ASUS
 * 创建时间  2017年10月22日 下午1:41:00
 *
 */
@Service
public class MongoKrService {

	/**
	 * 日志
	 */
	private static final Logger LOGGER  =LoggerFactory.getLogger(MongoKrService.class);
	
	@Autowired
	private MongoDao  mongoDao;
	
	/**
	 * 保存list 里面数据到mongo中
	 * @author ASUS
	 * 创建时间  2017年10月22日 下午2:16:24
	 * @param list
	 */
	public void saveKrObject(List<Object> list) throws Exception {
		
		DetailAriticleBean  detailBean = (DetailAriticleBean) list.get(0);
		List<abTestValue>  abTest= (List<abTestValue>) list.get(1) ; 
		List<BossJobBean> bossJob = (List<BossJobBean>) list.get(2);
		List<HotPostsBean> hots = (List<HotPostsBean>) list.get(3);
		
		mongoDao.save(detailBean, MongoKey.LOG_KR_DETAIL_ARITICLE);
//		mongoDao.saveList(abTest, MongoKey.LOG_KR_ABTEST);
//		mongoDao.saveList(bossJob, MongoKey.LOG_KR_BOSSJOBS);
//		mongoDao.saveList(hots, MongoKey.LOG_KR_HOTS_POSTS);
		for(int index =0 ; index < abTest.size() ; index++) {
			mongoDao.save(abTest.get(index), MongoKey.LOG_KR_ABTEST);
			
		}
		
		for(int index =0 ; index < bossJob.size() ; index++) {
			mongoDao.save(bossJob.get(index), MongoKey.LOG_KR_BOSSJOBS);
			
		}
		
		for(int index =0 ; index < hots.size() ; index++) {
			mongoDao.save(hots.get(index), MongoKey.LOG_KR_HOTS_POSTS);
			
		}
	
		
	}
	
	
}
