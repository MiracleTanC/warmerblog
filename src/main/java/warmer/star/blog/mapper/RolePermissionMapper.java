package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.model.RolePermission;

public interface RolePermissionMapper {

	List<RolePermission> getRolePermission(@Param("roleId") Integer roleId);
}
