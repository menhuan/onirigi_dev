package com.test.demo.service.imooc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.bean.imooc.BusinessBean;
import com.test.demo.dao.imooc.BusinessDao;
import com.test.demo.dto.imooc.BusinessDto;
import com.test.demo.service.imooc.BusinessService;

import static com.test.demo.constant.NumConstant.*;

/**
 * 商店的实现
 * @author ASUS
 * 创建时间  2017年12月31日 下午5:09:16
 *
 */
@Service
public class BusinessServiceImpl implements BusinessService {

	
	@Autowired
	private BusinessDao businessDao ;
	
	/**
	 * 插入
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午1:42:15
	 * @param bean
	 * @return
	 * @throws Execution
	 */
	@Override
	public int  insert(BusinessDto  bean) throws Exception {
		BusinessBean businessBean = new BusinessBean();
		BeanUtils.copyProperties(bean, businessBean);

		int result = 0;
		if(bean.getImgFile() != null &&  bean.getImgFile().getSize() > 0 ) {
			// 跟慕课里面讲的不一样的原因是 在thy 那里已经处理好 已经有名字了
			businessBean.setNumber(BUSSINES_SALLED);
			businessBean.setCommentTotalNum(COMMENT_TOTAL_NUM);
			businessBean.setStarTotalNum(START_TOTAL_NUM);
			result = businessDao.insert(businessBean);
		}
		
		return result;
		
	}
	
	
	/**
	 * 根据主键id查询 商店
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午1:56:53
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Override
	public BusinessDto selectBeanById(BusinessDto bean) throws Exception {
		BusinessDto  resultDto = new BusinessDto();
		BusinessBean businessBean = businessDao.selectBeanById(bean);
		
		BeanUtils.copyProperties(businessBean, resultDto);
		resultDto.setImg(businessBean.getImgFileName());
		resultDto.setStar(this.getStar(resultDto));
		
		return resultDto;
		
	}
	
	/**
	 * 根据条件查询 商户列表
	 *  标题、副标题、描述 三个过滤条件为模糊查询
	 *  并且三个过滤条件之间为或者的关系  用or链接
	 *  这三个过滤条件与其他过滤条件依然是并且关系 用and链接
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午1:57:47
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BusinessDto> selectLikeByPage(BusinessDto bean) throws Exception {
		List<BusinessDto>  resultDtos = new ArrayList<>();
		return resultDtos;
	}
	
	/**
	 * 根据条件分页查询商户列表
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午2:15:03
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BusinessDto> selectByPage(BusinessDto bean) throws Exception {
		
		List<BusinessDto> resultList = new ArrayList<BusinessDto>();
		BusinessBean  businessBean = new BusinessBean();
		BeanUtils.copyProperties(bean, businessBean);
		
		List<BusinessBean> list = businessDao.selectByPage(businessBean);
		for(BusinessBean business : list) {
			BusinessDto dto = new BusinessDto() ;
			resultList.add(dto);
			BeanUtils.copyProperties(business, dto);
			dto.setImg(business.getImgFileName());
			dto.setStar(this.getStar(dto));
		}
		
		return resultList;
	}
	
	/**
	 * 更新商户的【统计评论星星总数】、【统计评论总次数】、商户的【星级】用这两个字段数据计算得出
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午2:00:00
	 * @param map 传入的数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateStar(Map<String,Date> map) throws Exception {
		int result = 0 ;
		return result ;
	}
	
	
	/**
	 * 查询评论星星的数量
	 * @author ASUS
	 * 创建时间  2017年12月23日 下午9:24:36
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int getStar(BusinessDto  dto) throws Exception {
		
		int result = 0;
		if(dto.getStarTotalNum() != null && dto.getCommentTotalNum() != null && dto.getCommentTotalNum() != 0 ) {
			result = (int) (dto.getStarTotalNum() / dto.getCommentTotalNum()) ;
		}
		
		return result ;
	}
	
	
}
