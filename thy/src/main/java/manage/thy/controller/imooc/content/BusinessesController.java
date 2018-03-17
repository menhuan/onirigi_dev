package manage.thy.controller.imooc.content;

import static manage.thy.constant.DicTypeConst.CATEGORY;
import static manage.thy.constant.DicTypeConst.CITY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import manage.thy.constant.PageCodeEnum;
import manage.thy.dto.imooc.BusinessDto;
import manage.thy.service.imooc.BusinessService;
import manage.thy.service.imooc.DisService;

/**
 * 商铺
 * @author ASUS
 * 创建时间  2017年12月22日 下午9:04:22
 *
 */
@Controller
@RequestMapping("/business")
public class BusinessesController {

	/**
	 * 日志文件
	 */
	private static final Logger  LOGGER = LoggerFactory.getLogger(BusinessesController.class);
	
	@Autowired
	private BusinessService  businessService;
	
	@Autowired
	private DisService  disService;
	
	/**
	 * 
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午9:07:06
	 * @param model
	 * @param dto
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String search (Model model ,BusinessDto dto) {
		LOGGER.debug("分页商户列表查询");
		
		try {
			model.addAttribute("list", businessService.searchByPage(dto));
			model.addAttribute("searchParam", dto);
		} catch (Exception e) {
			LOGGER.error("分页商户列表查询出现异常。。",e);
		}

		return "/public/content/businessList" ;	
	}
	
	
	/**
	 * 删除商户
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午9:19:35
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public String remove(@PathVariable("id")Long id) throws Exception {
		
		businessService.remove(id) ;
		
		return "redirect:/business";
	}
	
	
	
	/**
	 * 新增页面初始化
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午9:20:57
	 * @param model
	 * @return
	 */
	public String addInit(Model model) {
		LOGGER.info("新增页面初识化。。。。");
		try {
			model.addAttribute("cityList", disService.getListByType(CITY));
			model.addAttribute("categoryList", disService.getListByType(CATEGORY));
		} catch (Exception e) {
			LOGGER.error("商户新增页面初始化。。。。。。");
		}
		
		return "/public/content/businessAdd";
	}
	
	/**
	 * 商户新增
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午10:07:43
	 * @param dto
	 * @param attr
	 * @return
	 */
	@RequestMapping( value= "add" , method = RequestMethod.POST)
	public String add(BusinessDto dto , RedirectAttributes attr) {
		try {
			if(businessService.add(dto)) {
				attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
				return "redirect:/business";
			}
			attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
		} catch (Exception e) {
			LOGGER.error("商户新增失败。。。",e);
		}
	
		return "redirect:/business/addPage" ;
	}
	
	/**
	 * 商户修改页面初始化
	 * @author ASUS
	 * 创建时间  2017年12月24日 下午10:24:17
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value =  "/modifyInit/{id}" ,method = RequestMethod.POST)
	public String modifyInit(Model model ,Long id) {
		try {
			model.addAttribute("cityList", disService.getListByType(CITY));
			model.addAttribute("categoryList", disService.getListByType(CATEGORY));
			
			model.addAttribute("modifyObj", businessService.selectById(id));
		} catch (Exception e) {
			LOGGER.error("商户页面初始化失败",e);
		}
		
		return "/public/content/businessModify" ;
		
	}
	
}
