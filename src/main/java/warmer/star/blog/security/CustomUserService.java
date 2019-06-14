package warmer.star.blog.security;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import warmer.star.blog.model.UserInfo;
import warmer.star.blog.model.UserRole;
import warmer.star.blog.service.UserRoleService;
import warmer.star.blog.service.UserService;
import warmer.star.blog.util.AppUser;
import warmer.star.blog.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
    private RedisUtil redisUtil;
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
			if(userInfo!=null){
				user.setUserId(userinfo.getId());
				user.setUserCode(userinfo.getUsername());
				user.setAvatar(userInfo.getAvatar());
				user.setNickname(userinfo.getUsername());
				redisUtil.remove(username);
				redisUtil.set(username, JSON.toJSONString(userInfo),3600);

			}

			return user;
		} else {
			throw new UsernameNotFoundException("admin: " + username + " do not exist!");
		}
	}
}
