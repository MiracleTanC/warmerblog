package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {

	private long id;

    private Integer userid;  
    
    private Integer roleid;

}