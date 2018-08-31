/**    
* @Title: AccountsController.java  
* @Package warmer.miao.blog.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年5月25日 下午2:26:45  
* @version V1.0    
*/
package warmer.star.blog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import warmer.star.blog.model.User;

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
	
}
