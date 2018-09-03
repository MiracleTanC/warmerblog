package warmer.star.blog.model;

import java.io.Serializable;


public class UserRole implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private long id;

    private Integer userid;  
    
    private Integer roleid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}    

   

}