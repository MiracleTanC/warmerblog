package warmer.star.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGetFile {

	@Test
	public void get() {
		String basePath="F:\\git\\warmerblog\\src\\main\\resources\\static\\icons\\svg";
		String[] list=new File(basePath).list();
		for(int i=0;i<list.length;i++){
			System.out.println(" "+list[i]);
		}

	}

}
