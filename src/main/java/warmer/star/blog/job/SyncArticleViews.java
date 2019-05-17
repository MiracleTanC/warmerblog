package warmer.star.blog.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import warmer.star.blog.enums.RedisKey;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.util.RedisUtil;

import java.util.Map;

@Component
public class SyncArticleViews {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ArticleService articleService;
	
	@Scheduled(cron = "0 0/1 * * * ? ")//每1分钟
	public void SyncNodesAndShips() {
		logger.info("开始保存点赞数 、浏览数");
		try {
			//先获取这段时间的浏览数
			Map<Object,Object> viewCountItem=redisUtil.hmget(RedisKey.ARTICLE_VIEWCOUNT_KEY);
			//然后删除redis里这段时间的浏览数
			redisUtil.remove(RedisKey.ARTICLE_VIEWCOUNT_KEY);
			if(!viewCountItem.isEmpty()){
				for(Object item :viewCountItem.keySet()){
					String articleKey=item.toString();//viewcount_1
					String[]  kv=articleKey.split("_");
					Integer articleId=Integer.parseInt(kv[1]);
					Integer viewCount=(Integer) viewCountItem.get(articleKey);
					//更新到数据库
					articleService.updateArticleViewCount(articleId,viewCount);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("结束保存点赞数 、浏览数");
	}
}
