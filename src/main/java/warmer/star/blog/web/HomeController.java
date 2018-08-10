package warmer.star.blog.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.util.R;

@Controller
@RequestMapping("/") 
public class HomeController extends BaseController {
	
	// 文章service
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/")
	public String index(ArticleQueryItem queryItem) {
		return "home/index";
	}

	@RequestMapping("/detail/{articleId}")
	public String edit(@PathVariable("articleId")int articleId,Model model) {
		ArticleItem articleItem=new ArticleItem();
		if(articleId!=0)
		{
			articleItem=articleService.getById(articleId);
			model.addAttribute("articleModel",articleItem);
		}
		return "home/detail";
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
}