package warmer.star.blog.model;

import java.io.Serializable;


public class Permission implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private long id;

    private String permissioncode;  
    
    private String permissionname;    

    private String comment; //过期

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPermissioncode() {
		return permissioncode;
	}

	public void setPermissioncode(String permissioncode) {
		this.permissioncode = permissioncode;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}