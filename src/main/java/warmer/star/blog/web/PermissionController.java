package warmer.star.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.dto.PermissionQueryItem;
import warmer.star.blog.model.Permission;
import warmer.star.blog.service.MenuService;
import warmer.star.blog.service.PermissionService;
import warmer.star.blog.util.PageRecord;
import warmer.star.blog.util.R;
import warmer.star.blog.util.RedisUtil;

import java.util.List;
@Controller
public class PermissionController extends  BaseController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisUtil redisUtil;
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/permission/index")
    public String index() {
        return "permission/index";
    }


    @RequestMapping("/permission/getpermissionlist")
    @ResponseBody
    public R getPermissionlist(PermissionQueryItem query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize(), true);
        List<Permission> permissions=permissionService.getList();
        PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        PageRecord<Permission> pageRecord = new PageRecord<Permission>();
        pageRecord.setRows(permissions);
        pageRecord.setCurrentPage(query.getPageIndex());
        pageRecord.setCurrentPageSize(query.getPageSize());
        pageRecord.setTotalCount(total);
        pageRecord.setTotalPage(pages);
        return R.success().put("data", pageRecord);
    }


    @RequestMapping("/permission/savepermission")
    @ResponseBody
    public R savePermission(Permission submitItem) {
        boolean result=false;
        try {
            if(submitItem.getId()==0){
                permissionService.savePermission(submitItem);
            }else {
                permissionService.updatePermission(submitItem);
            }
            result=true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(result){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }
    @RequestMapping("/permission/deletepermission")
    @ResponseBody
    public R deletePermission(int id) {
        try {
            permissionService.deletePermission(id);
            return R.success("操作成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error("操作失败");
        }
    }
}
