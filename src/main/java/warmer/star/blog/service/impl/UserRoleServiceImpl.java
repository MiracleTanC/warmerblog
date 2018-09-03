package warmer.star.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import warmer.star.blog.mapper.UserRoleMapper;
import warmer.star.blog.model.UserRole;
import warmer.star.blog.service.UserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

	@Override
	public List<UserRole> getUserRole(Integer userId) {
		return userRoleMapper.getUserRole(userId);
	}

   
}


