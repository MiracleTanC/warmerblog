package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleMenu implements Serializable {
	private Integer id;
	private Integer roleid;
	private Integer menuid;
	private Menu menuItem;
}