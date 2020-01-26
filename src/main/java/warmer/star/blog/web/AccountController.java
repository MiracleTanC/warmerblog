/**    
* @Title: AccountsController.java  
* @Package warmer.miao.blog.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年5月25日 下午2:26:45  
* @version V1.0    
*/
package warmer.star.blog.web;

import com.github.pagehelper.util.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import warmer.star.blog.enums.GitHubConstants;
import warmer.star.blog.model.User;
import warmer.star.blog.util.HttpClientUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
		return "redirect:/login";
	}
	//授权成功后的回调,我们需要在这个方法中拿到code去请求token
	@RequestMapping("/githubcallback")
	public String callback(HttpServletRequest request,String code, String state) throws Exception{
		//获取到code和state
		System.out.println("code:"+code);
		System.out.println("state:"+state);

		if(!StringUtil.isEmpty(code)&&!StringUtil.isEmpty(state)){
			//拿到我们的code,去请求token
			//发送一个请求到
			String token_url = GitHubConstants.TOKEN_URL.replace("CLIENT_ID", GitHubConstants.CLIENT_ID)
					.replace("CLIENT_SECRET", GitHubConstants.CLIENT_SECRET)
					.replace("CALLBACK", GitHubConstants.CALLBACK)
					.replace("CODE", code);
			//System.out.println("用户信息数据"+token_url);//这个里面有我们想要的用户信息数据
			String responseStr = HttpClientUtil.doGet(token_url);
			String token = HttpClientUtil.parseResponseEntity(responseStr).get("access_token");

			//根据token发送请求获取登录人的信息
			String userinfo_url = GitHubConstants.USER_INFO_URL.replace("TOKEN", token);
			responseStr = HttpClientUtil.doGet(userinfo_url);//json

			Map<String, String> responseMap = HttpClientUtil.parseResponseEntityJSON(responseStr);
			System.out.println("登录用户信息:"+responseMap);//responseMap里面保存着用户登录信息
			System.out.println("获取登录用户的用户名:"+responseMap.get("login"));
		}
		return "redirect:/home";
	}

}
