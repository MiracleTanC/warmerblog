/**
 * @Title: UserItem.java
 * @Package warmer.star.blog.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author tc
 * @date 2018年6月1日 下午2:51:43
 * @version V1.0
 */
package warmer.star.blog.util;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import warmer.star.blog.model.UserRole;

import java.util.Collection;
import java.util.List;

@Data
public class AppUser extends User {
    private Integer userId;
    private String userCode;
    private String username; //用户名
    private String avatar; //图像src
    private String nickname; //昵称
    private String phone; //电话号码
    private String email; //邮箱
    private String signature; //个性签名
    private String address; //地址
    private String wechatQrCode; //二维码
    private String qq;
    private String wechat;  //微信
    private String workExperience;
    private List<UserRole> userRoles;

    public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
