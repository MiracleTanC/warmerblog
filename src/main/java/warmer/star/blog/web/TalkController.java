package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.model.UserInfo;
import warmer.star.blog.service.UserService;
import warmer.star.blog.util.R;

import java.util.Map;

@Controller
@RequestMapping("/")
public class TalkController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/talk", method = RequestMethod.GET)
	public String about(Model model) {
		UserInfo userInfo=userService.getUserInfo("sa");
		model.addAttribute("userModel", userInfo);
		return "talk/index";
	}
	@RequestMapping("/talk/save")
	@ResponseBody
	public R save(Map<String,String> submitItem) {

		int id = 0;
		try {


		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
		}

		return R.error("操作失败");
	}
}