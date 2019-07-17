package warmer.star.blog.service;

import warmer.star.blog.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuList(Integer pid);
    List<Menu> getAll();
    Menu getById(Integer id);
    Integer saveMenu(Menu submitItem);
    boolean updateMenu(Menu submitItem);
    boolean updateMenuCode(int id, String code);
    boolean deleteMenuById(int id);
}
