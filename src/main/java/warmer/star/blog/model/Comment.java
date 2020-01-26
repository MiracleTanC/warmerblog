package warmer.star.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private String uuid;
    private Integer useruuid;
    private Integer touseruuid;
    private String content;
    private Integer pid;
    private Integer status;
    private String userip;
    private Date createtime;
    private UserInfo userItem;
    private UserInfo toUserItem;
}
