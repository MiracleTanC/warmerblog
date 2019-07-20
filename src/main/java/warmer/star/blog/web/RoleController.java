package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.model.Menu;
import warmer.star.blog.model.Role;
import warmer.star.blog.model.RolePermission;
import warmer.star.blog.service.MenuService;
import warmer.star.blog.service.RolePermissionService;
import warmer.star.blog.service.RoleService;
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
    @RequestMapping("/index")
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
            submitItem.setCreateuser(AppUserUtil.GetUserId());
            submitItem.setCreatetime(DateTimeHelper.getNowDate());
            roleService.saveRole(submitItem);
            Integer roleId = submitItem.getId();
            String code = String.format("%04d", roleId);
            roleService.updateRoleCode(roleId, code);
        }else {
            roleService.updateRole(submitItem);
        }
        return R.success("操作成功");
    }
    @RequestMapping("/getpermissionlist")
    @ResponseBody
    public R getTreelist(int parentId) {
        List<Menu> data = menuService.getAll();
        List<Map<String,String>> oplist=rolePermissionService.getAuthOperateList();
        List<HashMap<String, Object>> maps = getTree(parentId,data,oplist);
        return R.success().put("data", maps);

    }
    private List<HashMap<String, Object>> getTree(int parentId,List<Menu> nodelList,List<Map<String,String>> oplist) {
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
                //增加操作权限
                oplist.forEach(n->{
                    String operateId=menu.getId()+"#"+String.valueOf(n.get("code"));
                    String operateName=n.get("name");
                    String operateCode=n.get("code");
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
    @RequestMapping("/getroleauth")
    @ResponseBody
    public R getRoleAuth(Integer roleid) {
        try {
            List<String> ownMenu=new ArrayList<>();
            List<String> ownAuth=new ArrayList<>();
            List<RolePermission> rolePermissions=rolePermissionService.getRolePermission(roleid);
            if(rolePermissions!=null&&rolePermissions.size()>0){
                rolePermissions.forEach(n->{
                    ownMenu.add(String.valueOf(n.getMenuid()));
                    String operateCode=n.getOperatecode();
                    if(StringUtil.isNotBlank(operateCode)){
                        String[] ops=operateCode.split(",");
                        if(ops.length>0){
                            for(String c:ops){
                                String operateId=n.getMenuid()+"#"+c;
                                ownAuth.add(operateId);
                            }
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
}
