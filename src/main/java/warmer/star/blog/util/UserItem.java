/**    
* @Title: UserItem.java  
* @Package warmer.star.blog.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年6月1日 下午2:51:43  
* @version V1.0    
*/
package warmer.star.blog.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserItem extends User {

	private static final long serialVersionUID = 1L;

	private long userId;

	private String userCode;

	public UserItem(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
