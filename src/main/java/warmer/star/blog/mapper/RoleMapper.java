package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import warmer.star.blog.model.Role;

import java.util.List;
@Repository
public interface RoleMapper {

    Role getRole(@Param("roleId") Integer roleId);
    List<Role> getAll();
    Integer saveRole(Role submitItem);
    boolean updateRole(Role submitItem);
    boolean updateRoleCode(@Param("id") int id, @Param("rolecode") String rolecode);
    boolean deleteRoleById(@Param("id") int id);
}
