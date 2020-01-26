package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Role implements Serializable {
	private Integer id;
    private String rolecode;
    private String rolename;
    private String comment;
    private Integer createuser;
    private Date createtime;
}