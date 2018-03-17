package manage.thy.service.imooc.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manage.thy.dto.imooc.AdDto;
import manage.thy.model.imooc.AdBean;
import manage.thy.service.imooc.AdService;
import manage.thy.util.BaseUtil;
import manage.thy.util.QiniuService;
import manage.thy.util.RestRequestClient;
import manage.thy.util.ResultUtil;

import static manage.thy.util.BaseUtil.*;

/**
 * 广告service
 * @author ASUS
 * 创建时间  2017年12月2日 下午7:21:41
 *
 */
@Service
public class AdServiceImpl implements AdService  {

	@Autowired
	private QiniuService  qiniuService;
	
	
	
	@Override
	public boolean addAd(AdDto adDto) throws Exception {
//		AdBean adBean = new AdBean() ;
//		BeanUtils.copyProperties(adDto, adBean);
		String resultUrl = qiniuService.saveImageCloud(adDto.getImgFile());
		if(StringUtils.isNotEmpty(resultUrl) ) {
			adDto.setImgFileName(resultUrl);
			AdBean  bean = new AdBean() ;
			BeanUtils.copyProperties(adDto, bean);
			RestRequestClient client = new RestRequestClient() ;
			Map  map= client.restSubmitBean(IMOOC_AD_ADD, bean);
			Boolean isSuccess =ResultUtil.isSuccess(map);
			if(isSuccess) {
				return true ;
			}
		}
		
		return false;
	}



	@Override
	public List<AdDto> select(AdDto adDto) throws Exception {
		RestRequestClient client = new RestRequestClient() ;
		LinkedHashMap linkedHashMap = client.restSubmitBean(BaseUtil.IMOOC_AD_SELECT,adDto);
		if(ResultUtil.isSuccess(linkedHashMap)) {
			return ResultUtil.getResultListPage(linkedHashMap);
		}
		
		return new ArrayList<AdDto>();
	}



	@Override
	public boolean remove(Long id) throws Exception {
		RestRequestClient client = new RestRequestClient();
		LinkedHashMap<String ,Object>  linkedHashMap = client.restSubmitBean(BaseUtil.IMOOC_AD_REMOVE,id);
		if(ResultUtil.isSuccess(linkedHashMap)) {
			return true;
		}
		return false;
	}



	@Override
	public boolean modify(AdDto adDto) throws Exception {
		RestRequestClient client = new RestRequestClient();
		LinkedHashMap<String ,Object>  linkedHashMap = client.restSubmitBean(BaseUtil.IMOOC_AD_MODITY,adDto);
		if(ResultUtil.isSuccess(linkedHashMap)) {
			return true;
		}
		return false;
	}
	
	
	

	
	
}
