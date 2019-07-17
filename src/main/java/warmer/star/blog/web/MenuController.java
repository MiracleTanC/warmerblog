package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.model.Menu;
import warmer.star.blog.service.MenuService;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.R;
import warmer.star.blog.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
@Controller
public class MenuController extends BaseController{

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/index")
    public String index() {
        return "menu/index";
    }


    @RequestMapping("/menu/getmenulist")
    @ResponseBody
    public R getTreelist(int parentId) {
        List<Menu> data = menuService.getAll();
        List<HashMap<String, Object>> maps = getTree(parentId,data);
        return R.success().put("data", maps);

    }
    private List<HashMap<String, Object>> getTree(int parentId,List<Menu> nodelList) {
        List<HashMap<String, Object>> maps=new ArrayList<HashMap<String, Object>>();
        Iterator<Menu> treeList=nodelList.stream().filter(m->m.getPid()==parentId).iterator();
        while (treeList.hasNext()) {
            Menu menu = (Menu) treeList.next();
            HashMap<String, Object> menuModel = new HashMap<String, Object>();
            menuModel.put("id", menu.getId());
            menuModel.put("pid", menu.getPid());
            menuModel.put("code", menu.getCode());
            menuModel.put("name", menu.getName());
            menuModel.put("sort", menu.getSort());
            menuModel.put("level", menu.getLevel());
            menuModel.put("url", menu.getUrl());
            menuModel.put("icon", menu.getIcon());
            menuModel.put("parentId", menu.getPid());
            List<HashMap<String, Object>> childrenList=getTree( menu.getId(),nodelList);
            if(!childrenList.isEmpty())
            {
                menuModel.put("children",childrenList);
            }
            menuModel.put("isLeaf", childrenList.isEmpty()?true:false);
            maps.add(menuModel);
        }
        return maps;
    }
    @RequestMapping("/menu/savemenu")
    @ResponseBody
    public R saveMenu(Menu submitItem) {
        boolean result=false;
        Integer menuId=0;
        try {
            if(submitItem.getId()==0){
                submitItem.setStatus(1);
                submitItem.setCreateon(DateTimeHelper.getNowDate());
                submitItem.setUpdateon(DateTimeHelper.getNowDate());
                menuService.saveMenu(submitItem);
                menuId=submitItem.getId();
                String code = String.format("%04d", menuId);
                if(StringUtil.isNotBlank(submitItem.getCode())){
                    code=submitItem.getCode()+code;
                }
                result=menuService.updateMenuCode(menuId,code);
            }else {
                submitItem.setUpdateon(DateTimeHelper.getNowDate());
                result=menuService.updateMenu(submitItem);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(result)
        {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }
    @RequestMapping("/menu/deletemenu")
    @ResponseBody
    public R deleteMenu(int id) {
        boolean result=false;
        try {
            result=menuService.deleteMenuById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(result)
        {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }
}
