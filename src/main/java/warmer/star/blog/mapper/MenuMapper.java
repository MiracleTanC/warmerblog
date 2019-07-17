package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import warmer.star.blog.model.Menu;

import java.util.List;

@Repository
public interface MenuMapper {
    List<Menu> getMenuList(@Param("pid") Integer pid);
    List<Menu> getAll();
    Menu getById(@Param("id") Integer id);
    Integer saveMenu(Menu submitItem);
    boolean updateMenu(Menu submitItem);
    boolean updateMenuCode(@Param("id") int id, @Param("code") String code);
    boolean deleteMenuById(@Param("id") int id);
}
