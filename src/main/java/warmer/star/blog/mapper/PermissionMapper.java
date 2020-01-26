package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import warmer.star.blog.model.Permission;

import java.util.List;
@Repository
public interface PermissionMapper {
	List<Permission> getList();
	List<Permission> getById(@Param("id") Integer id);
	List<Permission> getByMenuId(@Param("menuid") Integer menuid);
	void savePermission(Permission submitItem);
	void updatePermission(Permission submitItem);
	void deletePermission(Integer id);
}
