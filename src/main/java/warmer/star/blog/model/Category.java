package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Category implements Serializable {
	private Integer id;

	private String categoryCode;

	private String categoryName; // 分类名称

	private String fullName; // 分类全称

	private Integer sort;

	private Integer parentId;

	private Integer isParent;

	private String path;

	private Integer level;

	private Integer status;

	private Date createOn;

	private Date updateOn;


}
