package manage.thy.controller.imooc.content;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import manage.thy.constant.PageCodeEnum;
import manage.thy.dto.imooc.AdDto;
import manage.thy.model.imooc.AdBean;
import manage.thy.service.imooc.AdService;

/**
 * 广告Controller
 * @author ASUS
 * 创建时间  2017年11月30日 下午10:34:32
 *
 */
@Controller
@RequestMapping("ad")
public class AdController {

	/**
	 * 日志 
	 */
	private static final Logger logger = LoggerFactory.getLogger(AdController.class) ;
	
	
	@Autowired
	private  AdService  adService;
	
	
	/**
	 * 增加广告
	 * @author ASUS
	 * 创建时间  2017年11月30日 下午10:35:03
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	public String addAd(AdDto adDto , Model model) {

		try {
			if(adService.addAd(adDto)) {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
			}else {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
			}
			
		} catch (Exception e) {
			logger.error("增加广告错误。。。。",e);
		}
		
		
		return "forward:/ad";
	}
	
	/**
	 * 初始化页面
	 * @author ASUS
	 * 创建时间  2017年11月30日 下午10:44:07
	 * @return
	 */
	@RequestMapping(value = "search",method = RequestMethod.GET)
	public String initList(AdDto adDto ,Model model) {
		
		try {
			model.addAttribute("searchParam", adDto);
			model.addAttribute("list", adService.select(adDto));
		} catch (Exception e) {
			logger.error("初始化页面出现异常",e);
		}
		return "public/content/adList";
	}
	
	/**
	 * 广告管理初识页面
	 * @author ASUS
	 * 创建时间  2017年12月5日 下午10:06:59
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String init(Model model , HttpServletRequest request ) {
		AdDto adDto = new AdDto() ;
		try {
			model.addAttribute("list",adService.select(adDto));
			model.addAttribute("searchParam", adDto);
		} catch (Exception e) {
			logger.error("广告查询 出现异常",e);
		}
		return "public/content/adList";
	}
	
	
	/**
	 * 删除 指定数据  
	 * 1. 直接删除 ，删除成功后直接跳转  失败的话页面 也没有提示 可能需要做下修改
	 * @author ASUS
	 * 创建时间  2017年12月5日 下午10:11:38
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "remove" ,method = RequestMethod.POST)
	public String remove(@RequestParam("id") Long id , Model model) {
		try {
			if(adService.remove(id)) {
				model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_SUCCESS);
			}else {
				model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
			}
		} catch (Exception e) {
			logger.error("移除广告任务 出现错误。。。",e);
		}
		
		return "forward:/ad";
	}
	
	/**
	 * 跳转到新增页面
	 * @author ASUS
	 * 创建时间  2017年12月5日 下午10:19:54
	 * @return
	 */
	@RequestMapping(value = "addInit" ,method = RequestMethod.GET)
	public String addInit() {
		return  "public/content/adAdd";
	}
	
	/**
	 * 修改
	 * @author ASUS
	 * 创建时间  2017年12月5日 下午10:24:52
	 * @param model
	 * @param adDto
	 * @return
	 */
	@RequestMapping(value = "modify" ,method = RequestMethod.POST)
	public String  modify(Model model , AdDto adDto) {
		model.addAttribute("modifyObj", adDto);
		try {
			if(adService.modify(adDto)) {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
			}else {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
			}
		} catch (Exception e) {
			logger.error("修改广告出现异常。。。。",e);
			e.printStackTrace();
		}
		
		return "public/content/adModify";
	}
	
	/**
	 * 修改页面初始化
	 * @author ASUS
	 * 创建时间  2017年12月5日 下午10:27:14
	 * @param model
	 * @param adDto
	 * @return
	 */
	@RequestMapping(value = "modifyInit" ,method = RequestMethod.POST)
	public String modifyInit(Model model , AdDto adDto ) {
		
		try {
			List<AdDto>  bean =adService.select(adDto);
			
			adDto = JSON.parseObject(JSON.toJSONString(bean.get(0)), AdDto.class);
			adDto.setImg(adDto.getImgFileName());
			
			model.addAttribute("modifyObj", adDto);
		} catch (Exception e) {
			logger.error("跳转修改页面出现异常。。。",e);
		}
	
		return "public/content/adModify";
	}
	
	
}
