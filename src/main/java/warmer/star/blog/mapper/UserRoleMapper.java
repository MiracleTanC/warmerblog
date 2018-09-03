package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.model.UserRole;

public interface UserRoleMapper {

    List<UserRole> getUserRole(@Param("userId") Integer userId);
}
