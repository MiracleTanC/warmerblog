package warmer.star.blog.service;


import java.util.List;

import warmer.star.blog.model.UserRole;

public interface UserRoleService {

	 List<UserRole> getUserRole(Integer userId);
}
