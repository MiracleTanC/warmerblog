package warmer.star.blog.service;


import warmer.star.blog.model.RoleMenu;

import java.util.List;

public interface RoleMenuService {
	List<RoleMenu> getRoleMenu(Integer roleId);
	void saveRoleMenu(List<RoleMenu> roleMenuList);
	void  deleteRoleMenu(List<String> roleIds);
}
