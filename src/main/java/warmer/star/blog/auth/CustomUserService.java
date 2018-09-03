package warmer.star.blog.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import warmer.star.blog.model.UserInfo;
import warmer.star.blog.model.UserRole;
import warmer.star.blog.service.UserRoleService;
import warmer.star.blog.service.UserService;
import warmer.star.blog.util.AppUser;
import warmer.star.blog.util.RedisService;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
    private RedisService redisService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		warmer.star.blog.model.User userinfo = userService.getUserByUsername(username);
		if (userinfo != null) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			List<UserRole> userRoleList = userRoleService.getUserRole(userinfo.getId());
			for (UserRole userRole : userRoleList) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRoleid().toString());
				// 1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
				grantedAuthorities.add(grantedAuthority);
			}
           
			AppUser user = new AppUser(userinfo.getUsername(),
					userinfo.getPassword() == null ? "" : userinfo.getPassword(), grantedAuthorities);
			
			UserInfo userInfo=userService.getUserInfo(username);
			user.setUserId(userinfo.getId());
			user.setUserCode(userinfo.getUsername());
			user.setAvatar(userInfo.getAvatar());
			user.setNickname(userinfo.getUsername());
	        redisService.remove(username);
	    	redisService.set(username, JSON.toJSONString(userInfo));
	        redisService.expire(username, 3600);
			return user;
		} else {
			throw new UsernameNotFoundException("admin: " + username + " do not exist!");
		}
	}
}
