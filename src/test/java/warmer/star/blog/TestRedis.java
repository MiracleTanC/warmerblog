package warmer.star.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import warmer.star.blog.enums.RedisKey;
import warmer.star.blog.util.RedisUtil;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@WebAppConfiguration
public class TestRedis {

	@Autowired
	private RedisUtil redisUtil;

	public void Test() {
		HashMap<String,Object> ms=new HashMap<>();
		ms.put("name","tanchao");
		ms.put("password","123456");
		ms.put("age",27);
		//redisUtil.set("jackson",ms);
		redisUtil.set("fastjson",ms);

	}
	@Test
	public  void Test2(){
		String key=RedisKey.ARTICLE_VIEWCOUNT_CODE+1;
		for(int i=0;i<10;i++){
			//找到redis中该篇文章的点赞数，如果不存在则向redis中添加一条
			Map<Object,Object> viewCountItem=redisUtil.hmget(RedisKey.ARTICLE_VIEWCOUNT_KEY);
			Integer viewCount=0;
			if(!viewCountItem.isEmpty()){
				if(viewCountItem.containsKey(key)){
					viewCount=(Integer)viewCountItem.get(key);
					redisUtil.hset(RedisKey.ARTICLE_VIEWCOUNT_KEY,key,viewCount+1);
				}
			}else{
				redisUtil.hset(RedisKey.ARTICLE_VIEWCOUNT_KEY,key,1);
			}
		}

	}
}
