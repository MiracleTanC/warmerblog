package warmer.star.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ComponentsController extends BaseController {

	@RequestMapping("/transfer")
	public String index(Model model) {

		return "transfer/index";
	}


}