package warmer.star.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warmer.star.blog.mapper.RoleMenuMapper;
import warmer.star.blog.model.RoleMenu;
import warmer.star.blog.service.RoleMenuService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

	@Override
	public List<RoleMenu> getRoleMenu(Integer roleId) {
		return roleMenuMapper.getRoleMenu(roleId);
	}


	@Override
	public void saveRoleMenu(List<RoleMenu> roleMenuList) {
		roleMenuMapper.saveRoleMenu(roleMenuList);
	}

	@Override
	public void deleteRoleMenu(List<String> roleIds) {
		roleMenuMapper.deleteRoleMenu(roleIds);
	}


}


