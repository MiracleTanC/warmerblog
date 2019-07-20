package warmer.star.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warmer.star.blog.mapper.RoleMapper;
import warmer.star.blog.model.Role;
import warmer.star.blog.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

	@Override
	public Role getRole(Integer roleId) {
		return roleMapper.getRole(roleId);
	}

	@Override
	public List<Role> getAll() {
		return roleMapper.getAll();
	}

	@Override
	public Integer saveRole(Role submitItem) {
		return roleMapper.saveRole(submitItem);
	}

	@Override
	public boolean updateRole(Role submitItem) {
		return roleMapper.updateRole(submitItem);
	}

	@Override
	public boolean updateRoleCode(int id, String rolecode) {
		return roleMapper.updateRoleCode(id,rolecode);
	}

	@Override
	public boolean deleteRoleById(int id) {
		return roleMapper.deleteRoleById(id);
	}


}


