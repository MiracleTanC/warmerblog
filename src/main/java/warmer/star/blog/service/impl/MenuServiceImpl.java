package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.mapper.MenuMapper;
import warmer.star.blog.model.Menu;
import warmer.star.blog.service.MenuService;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuList(Integer pid) {
        return menuMapper.getMenuList(pid);
    }

    @Override
    public List<Menu> getAll() {
        return menuMapper.getAll();
    }

    @Override
    public Menu getById(Integer id) {
        return menuMapper.getById(id);
    }

    @Override
    public Integer saveMenu(Menu submitItem) {
        return menuMapper.saveMenu(submitItem);
    }

    @Override
    public boolean updateMenu(Menu submitItem) {
        return menuMapper.updateMenu(submitItem);
    }

    @Override
    public boolean updateMenuCode(int id, String code) {
        return menuMapper.updateMenuCode(id,code);
    }

    @Override
    public boolean deleteMenuById(int id) {
        return menuMapper.deleteMenuById(id);
    }
}
