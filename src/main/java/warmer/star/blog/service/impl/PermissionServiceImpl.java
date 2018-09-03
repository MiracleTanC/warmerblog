package warmer.star.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import warmer.star.blog.mapper.PermissionMapper;
import warmer.star.blog.model.Permission;
import warmer.star.blog.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

	@Override
	public Permission getPermission(Integer permissionId) {
		return permissionMapper.getPermission(permissionId);
	}

   
}


