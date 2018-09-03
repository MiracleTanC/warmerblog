package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;

import warmer.star.blog.model.UserInfo;
import warmer.star.blog.service.UserService;
import warmer.star.blog.util.AppUserUtil;
import warmer.star.blog.util.R;
import warmer.star.blog.util.RedisService;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;

	@RequestMapping("/user")
	//@PreAuthorize("hasRole('USER')")
	public String index(Model model) {
		String username = AppUserUtil.GetUserCode();
		if (StringUtil.isEmpty(username)) {
			return "redirect:/login";
		}
		UserInfo userInfo = new UserInfo();
		String u = redisService.get(username);
		if (StringUtil.isNotEmpty(u)) {
			userInfo = JSON.parseObject(u, UserInfo.class);
		} else {
			userInfo = userService.getUserInfo(username);
			if (userInfo != null) {
				redisService.remove(username);
				redisService.set(username, JSON.toJSONString(userInfo));
				redisService.expire(username, 3600);
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
			redisService.remove(submitItem.getUsername());
			redisService.set(submitItem.getUsername(), JSON.toJSONString(submitItem));
			redisService.expire(submitItem.getUsername(), 3600);
			return R.success("操作成功");
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
		}

		return R.error("操作失败");
	}

}