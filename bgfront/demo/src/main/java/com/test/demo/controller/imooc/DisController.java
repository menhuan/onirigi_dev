package com.test.demo.controller.imooc;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.test.demo.base.BaseJson;
import com.test.demo.dto.imooc.BusinessDto;
import com.test.demo.dto.imooc.DicDto;
import com.test.demo.service.imooc.DicService;

@RestController("dis")
public class DisController extends BaseJson{

	/**
	 * 日志文件
	 */
	private static final Logger logger = LoggerFactory.getLogger(DisController.class);
	
	@Autowired
	private DicService   dicService;
	
	/**
	 * dis字典
	 * @author ASUS
	 * 创建时间  2017年12月31日 下午3:30:10
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "select", method = RequestMethod.POST)
	public Map addAd(@RequestBody DicDto  bean) {
		try {
			LinkedHashMap linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("result",dicService.selectDics(bean));
			return super.returnSuccessInfo(linkedHashMap);
		} catch (Exception e) {
			logger.error("-----查询字典列表-----" + JSON.toJSONString(bean), e);
			return super.returnFailtrueInfo(e);
		}
	}
}
