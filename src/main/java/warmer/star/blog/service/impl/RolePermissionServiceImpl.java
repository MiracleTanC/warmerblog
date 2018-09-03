package warmer.star.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import warmer.star.blog.mapper.RolePermissionMapper;
import warmer.star.blog.model.RolePermission;
import warmer.star.blog.service.RolePermissionService;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

	@Override
	public List<RolePermission> getRolePermission(Integer roleId) {
		return rolePermissionMapper.getRolePermission(roleId);
	}

   
}


