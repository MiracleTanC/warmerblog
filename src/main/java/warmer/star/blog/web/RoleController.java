package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.model.*;
import warmer.star.blog.service.*;
import warmer.star.blog.util.AppUserUtil;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.R;
import warmer.star.blog.util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/role")
public class RoleController extends  BaseController{

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/index")
    @PreAuthorize("hasRole('ADMIN')")
    public String Index(Model model) {
        return "role/index";
    }

    @RequestMapping("/getrolelist")
    @ResponseBody
    public R getRolelist() {
        List<Role> roleList = roleService.getAll();
        return R.success().put("data", roleList);
    }
    @RequestMapping("/deleterole")
    @ResponseBody
    public R deleteRole(Integer roleId) {
        roleService.deleteRoleById(roleId);
        return R.success().put("msg","操作成功");
    }
    @RequestMapping("/saverole")
    @ResponseBody
    public R saveRole(Role submitItem) {
        if(submitItem.getId()==null||submitItem.getId()==0) {
            if(StringUtil.isNotBlank(submitItem.getRolecode())){
                submitItem.setRolecode(submitItem.getRolecode().toUpperCase());
            }
            submitItem.setCreateuser(AppUserUtil.GetUserId());
            submitItem.setCreatetime(DateTimeHelper.getNowDate());
            roleService.saveRole(submitItem);
            if(StringUtil.isBlank(submitItem.getRolecode())){
                Integer roleId = submitItem.getId();
                String code = String.format("%04d", roleId);
                roleService.updateRoleCode(roleId, code);
            }
        }else {
            roleService.updateRole(submitItem);
        }
        return R.success("操作成功");
    }
    @RequestMapping("/getpermissionlist")
    @ResponseBody
    public R getTreelist(int parentId) {
        List<Menu> data = menuService.getAll();
        List<Permission> oplist=permissionService.getList();
        List<HashMap<String, Object>> maps = getTree(parentId,data,oplist);
        return R.success().put("data", maps);

    }
    private List<HashMap<String, Object>> getTree(int parentId,List<Menu> nodelList,List<Permission> oplist) {
        List<HashMap<String, Object>> maps=new ArrayList<HashMap<String, Object>>();
        Iterator<Menu> treeList=nodelList.stream().filter(m->m.getPid()==parentId).iterator();
        while (treeList.hasNext()) {
            Menu menu = (Menu) treeList.next();
            HashMap<String, Object> menuModel = new HashMap<String, Object>();
            menuModel.put("id", menu.getId());
            menuModel.put("isoperate", 0);
            menuModel.put("pid", menu.getPid());
            menuModel.put("code", menu.getCode());
            menuModel.put("name", menu.getName());
            menuModel.put("sort", menu.getSort());
            menuModel.put("level", menu.getLevel());
            menuModel.put("url", menu.getUrl());
            menuModel.put("icon", menu.getIcon());
            menuModel.put("parentId", menu.getPid());
            List<HashMap<String, Object>> childrenList=getTree(menu.getId(),nodelList,oplist);
            if(childrenList.isEmpty()){
                List<Permission> permissionList=oplist.stream().filter(n->n.getMenuid().equals(menu.getId())).collect(Collectors.toList());
                //增加操作权限
                if(permissionList!=null&permissionList.size()>0){
                    permissionList.forEach(n->{
                        String operateId=menu.getId()+"#"+n.getId()+"#"+n.getCode();
                        String operateName=n.getName();
                        String operateCode=n.getCode();
                        HashMap<String, Object> operateModel = new HashMap<String, Object>();
                        operateModel.put("id", operateId);
                        operateModel.put("isoperate", 1);
                        operateModel.put("pid", menu.getId());
                        operateModel.put("code", operateCode);
                        operateModel.put("name", operateName);
                        operateModel.put("level", menu.getLevel()+1);
                        operateModel.put("parentId", menu.getId());
                        childrenList.add(operateModel);
                    });
                }
            }
            menuModel.put("children",childrenList);
            menuModel.put("isLeaf", childrenList.isEmpty()?true:false);
            maps.add(menuModel);
        }
        return maps;
    }
    @RequestMapping("/saveauth")
    @ResponseBody
    public R saveauth(@RequestBody List<RolePermission> submitItem) {
        boolean result=false;
        try {
            if(submitItem!=null&&submitItem.size()>0){
                List<String> roleIds=submitItem.stream().map(n->n.getRoleid().toString()).distinct().collect(Collectors.toList());
                rolePermissionService.deletePermission(roleIds);
                rolePermissionService.savePermission(submitItem);
                result=true;
            }
        } catch (Exception e) {
            log.error("操作失败:{0}", e);
            log.error(e.getMessage());
            result=false;
        }
        if(result){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }
    @RequestMapping("/saverolemenu")
    @ResponseBody
    public R saveRoleMenu(@RequestBody List<RoleMenu> submitItem) {
        boolean result=false;
        try {
            if(submitItem!=null&&submitItem.size()>0){
                List<String> roleIds=submitItem.stream().map(n->n.getRoleid().toString()).distinct().collect(Collectors.toList());
                rolePermissionService.deletePermission(roleIds);
                roleMenuService.saveRoleMenu(submitItem);
                result=true;
            }
        } catch (Exception e) {
            log.error("操作失败:{0}", e);
            log.error(e.getMessage());
            result=false;
        }
        if(result){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }
    @RequestMapping("/getroleauth")
    @ResponseBody
    public R getRoleAuth(Integer roleid) {
        try {
            List<String> ownMenu=new ArrayList<>();
            List<String> ownAuth=new ArrayList<>();
            List<RolePermission> rolePermissions=rolePermissionService.getRolePermission(roleid);
            if(rolePermissions!=null&&rolePermissions.size()>0){
                rolePermissions.forEach(n->{
                    if(n.getPermission()!=null){
                        String menuId=String.valueOf(n.getPermission().getMenuid());
                        if(!ownMenu.contains(menuId)){
                            ownMenu.add(menuId);
                        }
                        String authId=String.valueOf(n.getPermission().getMenuid()+"#"+n.getPermissionid()+"#"+n.getPermission().getCode());
                        if(!ownAuth.contains(authId)){
                            ownAuth.add(authId);
                        }
                    }
                });
            }
            Map<String,Object> mp=new HashMap<>();
            mp.put("ownMenu",ownMenu);
            mp.put("ownAuth",ownAuth);
            return R.success().put("data", mp);

        } catch (Exception e) {
            log.error("操作失败:{0}", e);
            log.error(e.getMessage());
        }

        return R.error("操作失败");
    }
    @RequestMapping("/getrolemenu")
    @ResponseBody
    public R getRoleMenu(Integer roleid) {
        try {
            List<String> ownMenu=new ArrayList<>();
            List<RoleMenu> roleMenus=roleMenuService.getRoleMenu(roleid);
            if(roleMenus!=null&&roleMenus.size()>0){
                roleMenus.forEach(n->{
                    if(n.getMenuItem()!=null){
                        String menuId=String.valueOf(n.getMenuid());
                        if(!ownMenu.contains(menuId)){
                            ownMenu.add(menuId);
                        }
                    }
                });
            }
            Map<String,Object> mp=new HashMap<>();
            mp.put("selectMenu",ownMenu);
            return R.success().put("data", mp);

        } catch (Exception e) {
            log.error("操作失败:{0}", e);
            log.error(e.getMessage());
        }

        return R.error("操作失败");
    }
}
