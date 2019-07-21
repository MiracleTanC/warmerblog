package warmer.star.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warmer.star.blog.mapper.UserRoleMapper;
import warmer.star.blog.model.UserRole;
import warmer.star.blog.service.UserRoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

	@Override
	public List<UserRole> getUserRole(Integer userId) {
		return userRoleMapper.getUserRole(userId);
	}

	@Override
	public void saveUserRole(List<UserRole> userRoles) {
		userRoleMapper.saveUserRole(userRoles);
	}

	@Override
	public void deleteUserRole(Integer userId) {
		userRoleMapper.deleteUserRole(userId);
	}


}


