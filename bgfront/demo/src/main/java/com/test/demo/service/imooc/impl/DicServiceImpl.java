package com.test.demo.service.imooc.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.imooc.DicBean;
import com.test.demo.dao.imooc.DicDao;
import com.test.demo.dto.imooc.DicDto;
import com.test.demo.service.imooc.DicService;

@Service
public class DicServiceImpl implements DicService {

	@Autowired
	DicDao  dicDao ;
	
	@Override
	public List<DicDto> selectDics(DicDto dto) {
		
		DicBean  bean   =  new DicBean() ;
		BeanUtils.copyProperties(dto, bean);
		List<DicBean>	dicBeans = dicDao.selectDics(bean);
		List<DicDto>  dicDtos = new ArrayList<DicDto> ();
		for(int index =0, size = dicBeans.size() ; index < size ; index ++) {
			DicDto  dicDto = new DicDto() ;
			BeanUtils.copyProperties(dicBeans.get(index), dicDto);
			dicDtos.add(dicDto);
		}
		return dicDtos;
	}

}
