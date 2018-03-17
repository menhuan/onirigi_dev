package com.test.demo.service.imooc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.imooc.AdBean;
import com.test.demo.dao.imooc.AdDao;
import com.test.demo.dto.imooc.AdDto;
import com.test.demo.service.imooc.AdService;

/**
 * 广告service 实现
 * @author ASUS
 * 创建时间  2017年12月7日 下午9:58:44
 *
 */
@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdDao  adDao;
	
	@Override
	public int insert(AdDto bean) throws Exception {
		
		AdBean adBean = new AdBean() ;
		BeanUtils.copyProperties(bean, adBean);
		int result = adDao.insert(adBean);
		
		return result;
	}

	@Override
	public List<AdDto> selectByPage(AdDto bean) throws Exception {
		AdBean adBean = new AdBean() ;
		BeanUtils.copyProperties(bean, adBean);
		List<AdBean>  beans = adDao.selectByPage(adBean);
		
		List<AdDto>  adDtos = new ArrayList<AdDto>();
		for(int index = 0 ; index < beans.size() ; index ++) {
			AdDto adDto = new AdDto();
			BeanUtils.copyProperties(beans.get(index), adDto);
			adDtos.add(adDto);
		}
		
		return adDtos;
	}

	@Override
	public int update(AdDto bean) throws Exception {
		AdBean adBean = new AdBean() ;
		BeanUtils.copyProperties(bean, adBean);
		int result = adDao.update(adBean);
		return result;
	}

	@Override
	public int delete(Long id) throws Exception {
		int result =adDao.delete(id);
		return result;
	}

	
	
}
