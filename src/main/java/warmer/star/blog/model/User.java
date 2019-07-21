package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {

    private Integer id;

    private String username;    //用户名

    private String password;    //密码

    private String openid;

    private Integer sourcetype;

    private boolean expired; //过期

    private boolean locked; //被锁

    private boolean credential;  //凭证

    private boolean enabled;  //禁用

    private Date createTime;    //创建时间

    private UserInfo userItem;

    private List<UserRole> userRoles;

    public boolean isAccountNonExpired() {
        return expired;
    }

    public boolean isAccountNonLocked() {
        return locked;
    }

    public boolean isCredentialsNonExpired() {
        return credential;
    }

    public boolean isEnabled() {
        return enabled;
    }


}