package warmer.star.blog.service;


import warmer.star.blog.model.RolePermission;

import java.util.List;
import java.util.Map;

public interface RolePermissionService {

	List<RolePermission> getRolePermission(Integer roleId);
	List<Map<String,String>> getAuthOperateList();
	void savePermission(List<RolePermission> rolePermissionList);
	void  deletePermission(List<String> roleIds);
}
