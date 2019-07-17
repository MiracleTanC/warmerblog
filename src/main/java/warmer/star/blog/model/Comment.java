package warmer.star.blog.model;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private String uuid;
    private String useruuid;
    private String touseruuid;
    private String content;
    private String usernickname;
    private String tousernickname;
    private String userip;
}
