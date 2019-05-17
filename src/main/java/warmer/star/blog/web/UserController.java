package warmer.star.blog.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.model.UserInfo;
import warmer.star.blog.service.UserService;
import warmer.star.blog.util.AppUserUtil;
import warmer.star.blog.util.R;
import warmer.star.blog.util.RedisUtil;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("/user")
	//@PreAuthorize("hasRole('USER')")
	public String index(Model model) {
		String username = AppUserUtil.GetUserCode();
		if (StringUtil.isEmpty(username)) {
			return "redirect:/login";
		}
		UserInfo userInfo = redisUtil.get(username,UserInfo.class);
		if (userInfo==null) {
			userInfo = userService.getUserInfo(username);
			if (userInfo != null) {
				redisUtil.remove(username);
				redisUtil.set(username, JSON.toJSONString(userInfo));
				redisUtil.expire(username, 3600);
			}
		}
		model.addAttribute("userModel", userInfo);
		return "user/index";
	}

	@RequestMapping("/user/saveUserInfo")
	@ResponseBody
	public R saveUserInfo(UserInfo submitItem) {
		try {
			if (submitItem.getUserId() <= 0) {
				return R.error("操作失败");
			}
			userService.updateUserInfo(submitItem);
			redisUtil.remove(submitItem.getUsername());
			redisUtil.set(submitItem.getUsername(), JSON.toJSONString(submitItem));
			redisUtil.expire(submitItem.getUsername(), 3600);
			return R.success("操作成功");
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
		}

		return R.error("操作失败");
	}

}