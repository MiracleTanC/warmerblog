package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermission implements Serializable {
	private Integer id;
	private Integer roleid;
	private Integer menuid;
	private String operatecode;
	private Menu menuItem;
}