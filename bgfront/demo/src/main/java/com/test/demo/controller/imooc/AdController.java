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
import com.test.demo.service.imooc.AdService;
import com.github.pagehelper.PageInfo;
/**
 * 广告 
 * @author ASUS
 * 创建时间  2017年12月7日 下午9:33:39
 *
 */
@RestController
@RequestMapping(value = "ad")
public class AdController extends BaseJson {

	private static final Logger logger = LoggerFactory.getLogger(AdController.class);
	
	@Autowired
	private AdService  adService;
	
	
	/**
	 * 新增广告
	 * @author ASUS
	 * 创建时间  2017年12月7日 下午11:14:59
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Map addAd(@RequestBody AdDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", adService.insert(bean));
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----新增置顶广告-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	/**
	 * 根据条件查询
	 * @author ASUS
	 * 创建时间  2017年12月9日 下午3:52:58
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "select", method = RequestMethod.POST)
	public Map selectByPage(@RequestBody AdDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result",new PageInfo<AdDto>(adService.selectByPage(bean)) );
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----新增置顶广告-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
	/**
	 * 修改 对象
	 * @author ASUS
	 * 创建时间  2017年12月9日 下午3:52:32
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public Map update(@RequestBody AdDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", adService.update(bean));
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----修改置顶广告-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
	
	
	/**
	 * 删除
	 * @author ASUS
	 * 创建时间  2017年12月9日 下午3:51:26
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public Map delete(@RequestBody Long  id) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result", adService.delete(id));
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----删除置顶广告-----" + JSON.toJSONString(id), e);
			return super.returnFailtrueInfo(e);
		}
	}
}
