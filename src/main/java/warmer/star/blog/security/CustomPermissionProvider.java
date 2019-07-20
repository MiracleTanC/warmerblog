package warmer.star.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import warmer.star.blog.model.Menu;
import warmer.star.blog.model.RolePermission;
import warmer.star.blog.service.RolePermissionService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Component
public class CustomPermissionProvider implements PermissionEvaluator {

	@Autowired
	private RolePermissionService rolePermissionService;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
		// 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色id
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        // 遍历用户所有角色
        for(GrantedAuthority authority : authorities) {
        	Integer roleId = Integer.valueOf(authority.getAuthority());
            // 得到角色所有的权限
        	List<RolePermission> permissionList = rolePermissionService.getRolePermission(roleId);
            // 遍历permissionList
            for(RolePermission sysPermission : permissionList) {
            	Menu permissionItem=sysPermission.getMenuItem();
                // 如果访问的Url和权限用户符合的话，返回true
                if(permissionItem!=null&&targetUrl.equals(permissionItem.getUrl())
                        && sysPermission.getOperatecode().contains(permission.toString())) {
                    return true;
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
