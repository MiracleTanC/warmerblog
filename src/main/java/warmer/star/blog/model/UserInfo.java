package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
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
}
