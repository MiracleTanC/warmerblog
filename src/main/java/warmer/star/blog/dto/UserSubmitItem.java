package warmer.star.blog.dto;

import lombok.Data;
import warmer.star.blog.model.User;

import java.io.Serializable;
import java.util.List;

@Data
public class UserSubmitItem extends User implements Serializable {
	private List<Integer> roles;
}
