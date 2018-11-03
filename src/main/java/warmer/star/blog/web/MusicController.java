package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import warmer.star.blog.service.UserService;
import warmer.star.blog.util.R;

@Controller
@RequestMapping("/")
public class MusicController extends BaseController {

	@Autowired
	private UserService userService;
	@RequestMapping(value = "/getmusiclist", method = RequestMethod.GET)
	@ResponseBody
	public R getmusiclist() {
		try {
			
			return R.success("操作成功").put("data", new String[] {});
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
		}

		return R.success("操作成功");
	}


}