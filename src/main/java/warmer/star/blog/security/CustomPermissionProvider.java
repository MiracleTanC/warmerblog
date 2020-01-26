package warmer.star.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import warmer.star.blog.service.RolePermissionService;
import warmer.star.blog.service.RoleService;
import warmer.star.blog.util.AppUserUtil;
import warmer.star.blog.util.RedisUtil;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class CustomPermissionProvider implements PermissionEvaluator {

	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RedisUtil redisUtil;
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		AntPathMatcher antPathMatcher = new AntPathMatcher(File.separator);
		// 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色code
        Collection<GrantedAuthority> authorities = user.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			String roleCode = authority.getAuthority();
			List<HashMap<String, String>> permissionList =AppUserUtil.getRolePermission(roleCode);
			if(permissionList!=null&&permissionList.size()>0){
				// 遍历permissionList
				for(HashMap<String, String> permissionItem : permissionList) {
					String permissionCode=permissionItem.get("code");
					String permissionUrl=permissionItem.get("url");
					// 如果访问的资源用户符合的话，返回true   url 和权限编码
					if(permissionItem!=null&&antPathMatcher.match(permissionUrl, targetDomainObject.toString())&&permissionCode.equals(permission.toString())) {
						return true;
					}
				}
			}
		}
        return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
