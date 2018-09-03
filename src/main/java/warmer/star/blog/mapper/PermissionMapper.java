package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;

import warmer.star.blog.model.Permission;

public interface PermissionMapper {
	Permission getPermission(@Param("permissionId") Integer permissionId);
}
