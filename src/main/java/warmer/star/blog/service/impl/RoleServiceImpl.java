package warmer.star.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import warmer.star.blog.mapper.RoleMapper;
import warmer.star.blog.model.Role;
import warmer.star.blog.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

	@Override
	public Role getRole(Integer roleId) {
		return roleMapper.getRole(roleId);
	}

   
}


