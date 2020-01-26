package warmer.star.blog.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.dto.UserQueryItem;
import warmer.star.blog.dto.UserSubmitItem;
import warmer.star.blog.model.User;
import warmer.star.blog.model.UserInfo;
import warmer.star.blog.model.UserRole;
import warmer.star.blog.service.UserRoleService;
import warmer.star.blog.service.UserService;
import warmer.star.blog.util.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RedisUtil redisUtil;
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/user")
    public String index(Model model) {
        return "user/index";
    }

    @RequestMapping("/user/getuserlist")
    @ResponseBody
    public R getUserlist(@RequestBody UserQueryItem query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize(), true);
        List<User> userList = userService.getUserList();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        PageRecord<User> pageRecord = new PageRecord<User>();
        pageRecord.setRows(userList);
        pageRecord.setCurrentPage(query.getPageIndex());
        pageRecord.setCurrentPageSize(query.getPageSize());
        pageRecord.setTotalCount(total);
        pageRecord.setTotalPage(pages);
        return R.success().put("data", pageRecord);
    }

    @RequestMapping("/user/detail")
    //@PreAuthorize("hasRole('USER')")
    public String Detail(Model model) {
        String username = AppUserUtil.GetUserCode();
        if (StringUtil.isEmpty(username)) {
            return "redirect:/login";
        }
        UserInfo userInfo = redisUtil.get(username, UserInfo.class);
        if (userInfo == null) {
            userInfo = userService.getUserInfo(username);
            if (userInfo != null) {
                redisUtil.remove(username);
                redisUtil.set(username, JSON.toJSONString(userInfo));
                redisUtil.expire(username, 3600);
            }
        }
        model.addAttribute("userModel", userInfo);
        return "user/detail";
    }

    @RequestMapping("/user/saveUserInfo")
    @ResponseBody
    public R saveUserInfo(UserInfo submitItem) {
        try {
            if (submitItem.getUserId() <= 0) {
                return R.error("操作失败");
            }
            userService.updateUserInfo(submitItem);
            redisUtil.remove(submitItem.getUsername());
            redisUtil.set(submitItem.getUsername(), JSON.toJSONString(submitItem));
            redisUtil.expire(submitItem.getUsername(), 3600);
            return R.success("操作成功");
        } catch (Exception e) {
            log.error("操作失败:{0}", e);
            log.error(e.getMessage());
        }

        return R.error("操作失败");
    }

    @RequestMapping("/user/saveUser")
    @ResponseBody
    public R saveUser(@RequestBody UserSubmitItem submitItem) {
        try {
            if (submitItem.getId() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encode = encoder.encode("123456");
                User user = new User();
                user.setUsername(submitItem.getUsername());
                user.setPassword(encode);
                user.setCreateTime(DateTimeHelper.getNowDate());
                userService.saveUser(user);
                Integer userId = user.getId();
                UserInfo userInfo = new UserInfo();
                userInfo = submitItem.getUserItem();
                userInfo.setUserId(userId);
                userService.addUserInfo(userInfo);
                //添加角色
                if(submitItem.getRoles()!=null&&submitItem.getRoles().size()>0){
                    List<UserRole> userRoles=new ArrayList<>();
                    for (Integer roleId:submitItem.getRoles()){
                        UserRole ur=new UserRole();
                        ur.setUserid(userId);
                        ur.setRoleid(roleId);
                        userRoles.add(ur);
                    }
                    userRoleService.saveUserRole(userRoles);
                }
            } else {
                UserInfo userDetail=userService.getUserInfoById(submitItem.getId());
                if(userDetail!=null){
                    userDetail=submitItem.getUserItem();
                    userDetail.setUserId(submitItem.getId());
                    userService.updateUserInfo(userDetail);
                }else{
                    UserInfo userInfo = new UserInfo();
                    userInfo = submitItem.getUserItem();
                    userInfo.setUserId(submitItem.getId());
                    userService.addUserInfo(userInfo);
                }
                //修改角色
                userRoleService.deleteUserRole(submitItem.getId());
                if(submitItem.getRoles()!=null&&submitItem.getRoles().size()>0){
                    List<UserRole> userRoles=new ArrayList<>();
                    for (Integer roleId:submitItem.getRoles()){
                        UserRole ur=new UserRole();
                        ur.setUserid(submitItem.getId());
                        ur.setRoleid(roleId);
                        userRoles.add(ur);
                    }
                    userRoleService.saveUserRole(userRoles);
                }
            }
            return R.success("操作成功");
        } catch (Exception e) {
            log.error("操作失败:{0}", e);
            log.error(e.getMessage());
        }

        return R.error("操作失败");
    }
}