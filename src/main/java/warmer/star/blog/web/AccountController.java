/**    
* @Title: AccountsController.java  
* @Package warmer.miao.blog.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年5月25日 下午2:26:45  
* @version V1.0    
*/
package warmer.star.blog.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import warmer.star.blog.config.GeetestConfig;
import warmer.star.blog.model.User;
import warmer.star.blog.util.GeetestLib;

@Controller
public class AccountController extends BaseController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, User user) {
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String Logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String Home() {
		return "login";
	}
	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
	public void getCaptcha() {
		GeetestConfig config=new GeetestConfig();
		GeetestLib gtSdk=config.initGeetest();
		String resStr = "{}";
		String userid = "test";
		//自定义参数,可选择添加
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("user_id", userid); //网站用户id
		param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
		param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP
		//进行验证预处理
		int gtServerStatus = gtSdk.preProcess(param);

	}
}
