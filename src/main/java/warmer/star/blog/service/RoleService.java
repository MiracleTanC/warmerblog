package warmer.star.blog.service;


import warmer.star.blog.model.Role;

import java.util.List;

public interface RoleService {

	Role getRole(Integer roleId);
	List<Role> getAll();
	Integer saveRole(Role submitItem);
	boolean updateRole(Role submitItem);
	boolean updateRoleCode(int id, String rolecode);
	boolean deleteRoleById(int id);
}
