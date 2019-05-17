package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import warmer.star.blog.model.UserInfo;
import warmer.star.blog.service.UserService;

@Controller
@RequestMapping("/")
public class AboutController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		UserInfo userInfo=userService.getUserInfo("sa");
		model.addAttribute("userModel", userInfo);
		return "about/index";
	}
}