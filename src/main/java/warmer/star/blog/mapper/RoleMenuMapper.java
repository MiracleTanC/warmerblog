package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import warmer.star.blog.model.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {
	List<RoleMenu> getRoleMenu(@Param("roleId") Integer roleId);
	void saveRoleMenu(List<RoleMenu> roleMenuList);
	void  deleteRoleMenu(List<String> roleIds);
}
