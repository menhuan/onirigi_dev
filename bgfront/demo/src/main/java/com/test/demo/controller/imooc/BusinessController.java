package com.test.demo.controller.imooc;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.test.demo.base.BaseJson;
import com.test.demo.bean.CommentBean;
import com.test.demo.dto.imooc.AdDto;
import com.test.demo.dto.imooc.BusinessDto;
import com.test.demo.service.imooc.AdService;
import com.test.demo.service.imooc.BusinessService;
import com.github.pagehelper.PageInfo;
/**
 * 广告 
 * @author ASUS
 * 创建时间  2017年12月7日 下午9:33:39
 *
 */
@RestController
@RequestMapping(value = "business")
public class BusinessController extends BaseJson {

	private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
	
	@Autowired
	private BusinessService  businessService;
	
	
	/**
	 * 新增商户列表
	 * @author ASUS
	 * 创建时间  2017年12月7日 下午11:14:59
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Map addAd(@RequestBody BusinessDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", businessService.insert(bean));
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----新增商户列表-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 删除商户列表
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午8:24:31
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public Map removeBusiness(@RequestBody BusinessDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", "");
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----删除商户列表-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 查询 商业信息
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午8:44:01
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "select", method = RequestMethod.POST)
	public Map selectBusiness(@RequestBody BusinessDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", new PageInfo<>(businessService.selectByPage(bean))  );
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----查询商户列表-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
	/**
	 * 更新 商户列表信息
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午8:44:22
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Map updateBusiness(@RequestBody BusinessDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", "");
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----更新商户列表-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
	/**
	 * 根据主键id查询
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午8:53:35
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "selectById", method = RequestMethod.POST)
	public Map selectById(@RequestBody BusinessDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", businessService.selectBeanById(bean));
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----根据主键d商户列表-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
//	/**
//	 * 提供接口的 查询  这里需要修改
//	 * @author ASUS
//	 * 创建时间  2017年12月24日 下午8:59:50
//	 * @param bean
//	 * @return
//	 */
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "selectById", method = RequestMethod.POST)
//	public Map selectByPageForApi(@RequestBody BusinessDto  bean) {
//		try {
//			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
//			linkedHashMap.put("result", businessService.selectBeanById(bean));
//			return super.returnSuccessInfo(linkedHashMap);
//		} catch (Exception e) {
//			logger.error("-----提供给接口的查询列表-----" + JSON.toJSONString(bean), e);
//			return super.returnFailtrueInfo(e);
//		}
//	}
	
	

	
}
