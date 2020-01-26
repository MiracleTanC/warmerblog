package warmer.star.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import warmer.star.blog.mapper.UserMapper;
import warmer.star.blog.model.User;
import warmer.star.blog.util.SpringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAddUser {

	@Test
	public void addUser() {
		UserMapper userMapper = SpringUtils.getBean(UserMapper.class);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode = encoder.encode("miss1991");
		User user=new User();
		user.setUsername("tanchao");
		userMapper.addUser(user);
		user.setPassword(encode);
		userMapper.updatePassword(user);
		System.out.println(encode);
	}

}
