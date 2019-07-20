package warmer.star.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warmer.star.blog.mapper.RolePermissionMapper;
import warmer.star.blog.model.RolePermission;
import warmer.star.blog.service.RolePermissionService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

	@Override
	public List<RolePermission> getRolePermission(Integer roleId) {
		return rolePermissionMapper.getRolePermission(roleId);
	}

	@Override
	public List<Map<String, String>> getAuthOperateList() {
		return rolePermissionMapper.getAuthOperateList();
	}

	@Override
	public void savePermission(List<RolePermission> rolePermissionList) {
		rolePermissionMapper.savePermission(rolePermissionList);
	}

	@Override
	public void deletePermission(List<String> roleIds) {
		rolePermissionMapper.deletePermission(roleIds);
	}


}


