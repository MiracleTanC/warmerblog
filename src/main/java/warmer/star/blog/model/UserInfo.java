package warmer.star.blog.model;

import java.io.Serializable;


public class UserInfo implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private Integer id; 
	private Integer userId; 
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWechatQrCode() {
        return wechatQrCode;
    }

    public void setWechatQrCode(String announcement) {
        this.wechatQrCode = announcement;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String telegram) {
        this.qq = telegram;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechart) {
        this.wechat = wechart;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", signature='" + signature + '\'' +
                ", address='" + address + '\'' +
                ", wechatQrCode='" + wechatQrCode + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                '}';
    }

	/**
	 * @return the workExperience
	 */
	public String getWorkExperience() {
		return workExperience;
	}

	/**
	 * @param workExperience the workExperience to set
	 */
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
