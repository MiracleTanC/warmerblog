package warmer.star.blog.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.enums.RedisKey;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.model.Tag;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.service.TagService;
import warmer.star.blog.util.R;
import warmer.star.blog.util.RedisUtil;
import warmer.star.blog.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/") 
public class HomeController extends BaseController {
	// 文章service
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagService tagService;
	@Autowired
	private RedisUtil redisUtil;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		return "admin/index";
	}
	@RequestMapping("/")
	public String index(ArticleQueryItem queryItem) {
		return "home/index";
	}

	@RequestMapping("/detail/{articleId}")
	public String detail(@PathVariable("articleId")int articleId,Model model) {
		ArticleItem articleItem=articleService.getById(articleId);
		if(articleItem!=null){
			//记录浏览量到redis,然后定时更新到数据库
			String key=RedisKey.ARTICLE_VIEWCOUNT_CODE+articleId;
			//找到redis中该篇文章的点赞数，如果不存在则向redis中添加一条
			Map<Object,Object> viewCountItem=redisUtil.hmget(RedisKey.ARTICLE_VIEWCOUNT_KEY);
			Integer viewCount=0;
			if(!viewCountItem.isEmpty()){
				if(viewCountItem.containsKey(key)){
					viewCount=(Integer)viewCountItem.get(key);
					redisUtil.hset(RedisKey.ARTICLE_VIEWCOUNT_KEY,key,viewCount+1);
				}else {
                    redisUtil.hset(RedisKey.ARTICLE_VIEWCOUNT_KEY,key,1);
                }
			}else{
				redisUtil.hset(RedisKey.ARTICLE_VIEWCOUNT_KEY,key,1);
			}
			//获取细览信息
			ArticleDetail contentItem=articleService.getContentById(articleId);
			if(contentItem!=null&&StringUtils.isNotBlank(contentItem.getContent())){
				articleItem.setContent(contentItem.getContent());
			}
			model.addAttribute("articleModel",articleItem);
			//获取文章标签
			List<String> tags=new ArrayList<>();
			if(StringUtil.isNotBlank(articleItem.getArticleTags())){
				List<String> tagIds=Arrays.asList(articleItem.getArticleTags().split(","));
				if(tagIds!=null&&tagIds.size()>0){
					List<Tag> tagItems=tagService.getTagByIds(tagIds);
					if(tagItems!=null&&tagItems.size()>0){
						tags=tagItems.stream().map(n->n.getAliasName()).collect(Collectors.toList());
					}
				}
			}
			model.addAttribute("tagList",tags);
			if(articleItem.getEditorType().equals(0)) {
				return "home/detailmarkdown";
			}
			return "home/detail";
		}
		return "";
	}
	@RequestMapping("/getRecommendArticlelist")
	@ResponseBody
	public R getRecommendArticlelist() {
		List<ArticleItem> articleList = articleService.getRecommendArticleList();
		return R.success().put("data", articleList);
	}
	@RequestMapping("/getTopReadArticleList")
	@ResponseBody
	public R getTopReadArticleList() {
		List<ArticleItem> articleList = articleService.getTopReadArticleList();
		return R.success().put("data", articleList);
	}
	@RequestMapping("/getRelvantArticle")
	@ResponseBody
	public R getRelvantArticle(Integer articleId,String tagIds) {
		List<Map<String,Object>> articleList = articleService.getRelvantArticle(articleId,tagIds);
		return R.success().put("data", articleList);
	}

}