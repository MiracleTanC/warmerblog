package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserRole implements Serializable {

	private long id;

    private Integer userid;  
    
    private Integer roleid;
    private Role roleItem;

    private List<RolePermission> rolepermission;

}