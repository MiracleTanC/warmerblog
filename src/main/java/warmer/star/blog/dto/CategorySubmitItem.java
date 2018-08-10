package warmer.star.blog.dto;

import java.io.Serializable;


public class CategorySubmitItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name; //分类名

    private String code;   //分类别名

    private Integer sort;
    public Integer status;
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private String createon; 
    private String updateon; 
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getUpdateon() {
		return updateon;
	}


	public void setUpdateon(String updateon) {
		this.updateon = updateon;
	}

	public String getCreateon() {
		return createon;
	}


	public void setCreateon(String createon) {
		this.createon = createon;
	}

	private Integer parentId;

   
}
