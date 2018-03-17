package manage.thy.controller.imooc.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author ASUS
 * 创建时间  2017年11月29日 下午11:12:51
 *
 */
@Controller
public class HomeController {

	@RequestMapping("imooc")
	public String index() {
		return "public/index";
	}
}
