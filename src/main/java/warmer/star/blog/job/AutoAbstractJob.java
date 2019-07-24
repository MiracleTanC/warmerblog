package warmer.star.blog.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.util.AutoAbstract.TextRank;
import warmer.star.blog.util.StringUtil;

import java.util.List;

@Component
public class AutoAbstractJob {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ArticleService articleService;
	
	@Scheduled(cron = "0 0/10 * * * ? ")//每10分钟
	public void AutoAbstract() {
		logger.info("====开始自动摘要====");
		try {
			TextRank textRank=new TextRank();
			List<Integer> articleIds= articleService.getNoAbstractArticleId();
			if(articleIds!=null&&articleIds.size()>0){
				for (Integer id:articleIds){
					ArticleDetail detail=articleService.getContentById(id);
					if(detail!=null&&StringUtil.isNotBlank(detail.getContent())){
						String noHtmlContent=StringUtil.delHTMLTag(detail.getContent());
						String abstractContent=textRank.Summarize(noHtmlContent);
						if(StringUtil.isNotBlank(abstractContent)){
							articleService.updateAbstractById(id,abstractContent);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("====结束自动摘要====");
	}
}
