package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import warmer.star.blog.model.UserRole;

import java.util.List;

public interface UserRoleMapper {

    List<UserRole> getUserRole(@Param("userId") Integer userId);

    void saveUserRole(List<UserRole> userRoles);
    void deleteUserRole(@Param("userId")Integer userId);
}
