
package warmer.star.blog.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import warmer.star.blog.model.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AppUserUtil {
	public static String GetUserCode() {
		String result = "";

		try {
			if (IsUserExsit()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				AppUser userItem = (AppUser) auth.getPrincipal();
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
	public AppUser GetUser() {
		AppUser result =null;
		try {
			if (IsUserExsit()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				result = (AppUser) auth.getPrincipal();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static Integer GetUserId() {
		Integer result = 0;

		try {
			if (IsUserExsit()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				AppUser userInfo = (AppUser) auth.getPrincipal();
				result = userInfo.getUserId(); // 主体名，即登录用户名
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	public static List<UserRole> GetUserRoles() {
		List<UserRole> result = new ArrayList<UserRole>() ;
		try {
			if (IsUserExsit()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				AppUser userInfo = (AppUser) auth.getPrincipal();
				result = userInfo.getUserRoles();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 过滤菜单权限
	 * @param menuList
	 * @return
	 */
	public static List<Object> filterUserRole(List<Object> menuList){
		if(menuList==null||menuList.size()==0) return new ArrayList<>();
		List<UserRole> userRoles=AppUserUtil.GetUserRoles();
		List<Integer> uerMeunIds=new ArrayList<>();
		userRoles.forEach(m->{
			if(m.getRoleMenu()!=null&&m.getRoleMenu().size()>0){
				m.getRoleMenu().forEach(n->{
					if(n.getMenuItem()!=null){
						Integer menuId=n.getMenuid();
						if(!uerMeunIds.contains(menuId)){
							uerMeunIds.add(menuId);
						}
					}
				});
			}
		});
		if(menuList!=null&&menuList.size()>0){
			Iterator<Object> iterator = menuList.iterator();
			while(iterator.hasNext()) {
				HashMap<String, Object> menuItem = (HashMap<String, Object>) iterator.next();
				List<Integer> cids=new ArrayList<>();
				if(menuItem!=null&&menuItem.size()>0){
					if (menuItem.get("children") != null) {
						List<HashMap<String, Object>> children = (List<HashMap<String, Object>>) menuItem.get("children");
						Iterator<HashMap<String, Object>> childrenIterator = children.iterator();
						while (childrenIterator.hasNext()) {
							HashMap<String, Object> cmenuItem = childrenIterator.next();
							Integer cmenuId = (Integer) cmenuItem.get("id");
							Integer pId = (Integer) cmenuItem.get("pid");
							if (!uerMeunIds.contains(cmenuId)) {
								childrenIterator.remove();
							}else{
								if(!cids.contains(pId)){
									cids.add(pId);
								}
							}
						}
					}
					Integer menuId = (Integer) menuItem.get("id");
					if (!uerMeunIds.contains(menuId)&&!cids.contains(menuId)) {
						iterator.remove();
					}
				}
			}
		}

		return menuList;
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
