package warmer.star.blog.service;


import warmer.star.blog.model.UserRole;

import java.util.List;

public interface UserRoleService {

	 List<UserRole> getUserRole(Integer userId);
	void saveUserRole(List<UserRole> userRoles);
	void deleteUserRole(Integer userId);
}
