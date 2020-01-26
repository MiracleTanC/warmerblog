package warmer.star.blog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.mapper.PermissionMapper;
import warmer.star.blog.model.Permission;
import warmer.star.blog.service.PermissionService;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> getList() {
		return permissionMapper.getList();
	}

	@Override
	public List<Permission> getById(Integer id) {
		return permissionMapper.getById(id);
	}

	@Override
	public List<Permission> getByMenuId(Integer menuid) {
		return permissionMapper.getByMenuId(menuid);
	}

	@Override
	public void savePermission(Permission submitItem) {
		permissionMapper.savePermission(submitItem);
	}

	@Override
	public void updatePermission(Permission submitItem) {
		permissionMapper.updatePermission(submitItem);
	}

	@Override
	public void deletePermission(Integer id) {
		permissionMapper.deletePermission(id);
	}
}
