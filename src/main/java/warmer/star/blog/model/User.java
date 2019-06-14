package warmer.star.blog.model;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCredential() {
        return credential;
    }

    public void setCredential(boolean credential) {
        this.credential = credential;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(Integer sourcetype) {
        this.sourcetype = sourcetype;
    }
}