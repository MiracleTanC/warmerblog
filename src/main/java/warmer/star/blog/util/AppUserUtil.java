/**    
* @Title: AppUserUtil.java  
* @Package warmer.star.blog.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年6月1日 下午2:49:29  
* @version V1.0    
*/
package warmer.star.blog.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import warmer.star.blog.model.UserInfo;

public class AppUserUtil {
	public static String GetUserCode() {
		String result = "";

		try {
			if (IsUserExsit()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserInfo userItem = (UserInfo) auth.getPrincipal();
				result = userItem.getUsername(); // 主体名，即登录用户名
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static String GetUserName() {
		String result = "";

		try {
			if (IsUserExsit()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				result = auth.getName(); // 主体名，即登录用户名
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static long GetUserId() {
		long result = 0;

		try {
			if (IsUserExsit()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserInfo userInfo = (UserInfo) auth.getPrincipal();
				result = userInfo.getUserId(); // 主体名，即登录用户名
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static boolean IsUserExsit() {
		boolean result = false;

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth.getClass() != AnonymousAuthenticationToken.class) {
				result = auth.isAuthenticated();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
}
