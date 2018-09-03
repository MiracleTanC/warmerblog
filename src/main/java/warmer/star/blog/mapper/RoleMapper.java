package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;

import warmer.star.blog.model.Role;

public interface RoleMapper {

    Role getRole(@Param("roleId") Integer roleId);
}
