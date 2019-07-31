package warmer.star.blog.service;


import warmer.star.blog.model.Permission;

import java.util.List;

public interface PermissionService {

	List<Permission> getList();
	List<Permission> getById(Integer id);
	List<Permission> getByMenuId(Integer menuid);
	void savePermission(Permission submitItem);
	void updatePermission(Permission submitItem);
	void deletePermission(Integer id);
}
