package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import warmer.star.blog.model.RolePermission;

import java.util.List;
import java.util.Map;

public interface RolePermissionMapper {

	List<RolePermission> getRolePermission(@Param("roleId") Integer roleId);
	List<Map<String,String>> getAuthOperateList();
	void savePermission(List<RolePermission> rolePermissionList);
	void  deletePermission(List<String> roleIds);
}
