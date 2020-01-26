package warmer.star.blog.dto;

import lombok.Data;
import warmer.star.blog.model.UserInfo;

import java.io.Serializable;

@Data
public class UserItem extends UserInfo implements Serializable {
	private Integer serialNumber;
	private String createTime;
}
