package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warmer.star.blog.mapper.RoleMapper;
import warmer.star.blog.model.Role;
import warmer.star.blog.service.RoleService;
import warmer.star.blog.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
	@Autowired
    private RedisUtil redisUtil;
	@Override
	public Role getRole(Integer roleId) {
		return roleMapper.getRole(roleId);
	}

	@Override
	public List<Role> getAll() {
		List<Role> roles=new ArrayList<>();
		if(redisUtil.get("roleList")!=null){
			roles=(List<Role>)redisUtil.get("roleList");
		}else{
			roles=roleMapper.getAll();
			redisUtil.set("roleList",roles,3600);
		}
		return roles;
	}

	@Override
	public Integer saveRole(Role submitItem) {
		redisUtil.remove("roleList");
		return roleMapper.saveRole(submitItem);
	}

	@Override
	public boolean updateRole(Role submitItem) {
		redisUtil.remove("roleList");
		return roleMapper.updateRole(submitItem);
	}

	@Override
	public boolean updateRoleCode(int id, String rolecode) {
		redisUtil.remove("roleList");
		return roleMapper.updateRoleCode(id,rolecode);
	}

	@Override
	public boolean deleteRoleById(int id) {
		redisUtil.remove("roleList");
		return roleMapper.deleteRoleById(id);
	}


}


