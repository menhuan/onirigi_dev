package manage.thy.service.imooc.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manage.thy.dto.imooc.BusinessDto;
import manage.thy.service.imooc.BusinessService;
import manage.thy.util.QiniuService;
import manage.thy.util.RestRequestClient;
import manage.thy.util.ResultUtil;

import static manage.thy.util.BaseUtil.*;


/**
 * service实现
 * @author ASUS
 * 创建时间  2017年12月31日 下午5:11:52
 *
 */
@Service
public class BusinessServiceImpl implements BusinessService {
	
	@Autowired
	QiniuService  qiniuService;
	
	@Override
	public boolean add(BusinessDto dto) throws Exception {
		
		RestRequestClient  client = new RestRequestClient();
		if(dto.getImgFile() != null && dto.getImgFile().getSize() > 0 ) {
			String  fileUrl = qiniuService.saveImageCloud(dto.getImgFile());
			dto.setImgFileName(fileUrl);
			LinkedHashMap<String, Object> linkedHashMap = client.restSubmitBean(IMOOC_BUSINESS_ADD, dto);
			if(ResultUtil.isSuccess(linkedHashMap)) {
				int result = (int) ResultUtil.getResultObject(linkedHashMap);
				if(result > 0) {
					return  true ;
				}
			}
		}
		
		return false;
	}

	@Override
	public BusinessDto selectById(Long id) throws Exception {
		BusinessDto  dto = new BusinessDto() ;
		dto.setId(id);
		RestRequestClient client = new RestRequestClient();
		LinkedHashMap	linkedHashMap = client.restSubmitBean(IMOOC_BUSINESS_SELECT_BYID,dto);

		BusinessDto  resultDto = null ;
		if(ResultUtil.isSuccess(linkedHashMap)){
			resultDto = (BusinessDto) ResultUtil.getResultObject(linkedHashMap);
		}

		return resultDto;
	}

	@Override
	public List<BusinessDto> searchByPage(BusinessDto dto) throws Exception {
		RestRequestClient client = new RestRequestClient() ;
		LinkedHashMap  linkedHashMap = client.restSubmitBean(IMOOC_BUSINESS_SELECT,dto);

		List<BusinessDto> resultList = null ;
		if(ResultUtil.isSuccess(linkedHashMap)){
			resultList = ResultUtil.getResultListDto(linkedHashMap , BusinessDto.class);
		}

		return resultList;
	}

	@Override
	public boolean remove(Long id) throws Exception {


		return false;
	}

}
