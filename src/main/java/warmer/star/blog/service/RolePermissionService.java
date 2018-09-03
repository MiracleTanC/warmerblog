package warmer.star.blog.service;


import java.util.List;

import warmer.star.blog.model.RolePermission;

public interface RolePermissionService {

	List<RolePermission> getRolePermission(Integer roleId);
}
