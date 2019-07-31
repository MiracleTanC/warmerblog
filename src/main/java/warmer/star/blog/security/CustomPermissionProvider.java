package warmer.star.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import warmer.star.blog.model.Permission;
import warmer.star.blog.model.Role;
import warmer.star.blog.model.RolePermission;
import warmer.star.blog.service.RolePermissionService;
import warmer.star.blog.service.RoleService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomPermissionProvider implements PermissionEvaluator {

	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private RoleService roleService;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		// 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色code
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        List<Role> roleList=roleService.getAll();
        if(roleList!=null&&roleList.size()>0){
			// 遍历用户所有角色
			for(GrantedAuthority authority : authorities) {
				String roleCode = authority.getAuthority();
				List<Role> roleItems=roleList.stream().filter(n->n.getRolecode().equals(roleCode)).collect(Collectors.toList());
				if(roleItems!=null&&roleItems.size()>0){
					Role roleItem=roleItems.get(0);
					// 得到角色所有的资源权限
					List<RolePermission> permissionList = rolePermissionService.getRolePermission(roleItem.getId());
					if(permissionList!=null&&permissionList.size()>0){
						// 遍历permissionList
						for(RolePermission userPermission : permissionList) {
							Permission permissionItem=userPermission.getPermission();
							// 如果访问的资源用户符合的话，返回true   url 和权限编码
							if(permissionItem!=null&&targetDomainObject.equals(permissionItem.getUrl())
									&& permissionItem.getCode().contains(permission.toString())) {
								return true;
							}
						}
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
