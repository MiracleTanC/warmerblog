package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
	private Integer id;
	private Integer menuid;
	private String code;
	private String name;
	private String url;
	private Menu menu;
}