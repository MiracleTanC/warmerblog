package warmer.star.blog.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.dto.ArticleSubmitItem;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.ElasticsearchUtils;
import warmer.star.blog.util.EsPageRecord;
import warmer.star.blog.util.PageRecord;
import warmer.star.blog.util.R;

@Controller
@RequestMapping("/")
public class ArticleController extends BaseController {

	// 文章service
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/article")
	public String index(ArticleQueryItem queryItem) {
		return "article/index";
	}

	@RequestMapping("/getArticlelist")
	@ResponseBody
	public R getArticlelist(ArticleQueryItem query) {
		PageHelper.startPage(query.getPageIndex(), query.getPageSize(), true);
		// 获取文章列表
		List<ArticleItem> articleList = articleService.getArticleList(query);
		PageInfo<ArticleItem> pageInfo = new PageInfo<ArticleItem>(articleList);
		long total = pageInfo.getTotal();
		int pages = pageInfo.getPages();
		PageRecord<ArticleItem> pageRecord = new PageRecord<ArticleItem>();
		pageRecord.setRows(articleList);
		pageRecord.setCurrentPage(query.getPageIndex());
		pageRecord.setCurrentPageSize(query.getPageSize());
		pageRecord.setTotalCount(total);
		pageRecord.setTotalPage(pages);
		return R.success().put("data", pageRecord);
	}
	@RequestMapping("/getEsArticlelist")
	@ResponseBody
	public R getEsArticlelist(ArticleQueryItem query) {
		String index = "warmer_blog";
		String type = "article";
		String searchWords=query.getTitle();
		List<String> highLightFields=new ArrayList<String>();
		highLightFields.add("title");
		highLightFields.add("abstractContent");
		List<HashMap<String,String>> marchStrs=new ArrayList<HashMap<String,String>>();
		if(StringUtil.isNotEmpty(searchWords)) {
			HashMap<String,String> title=new HashMap<String,String>();
			title.put("condition", "title");
			title.put("value", searchWords);
			marchStrs.add(title);
			HashMap<String,String> abstractContent=new HashMap<String,String>();
			abstractContent.put("condition", "abstractContent");
			abstractContent.put("value", searchWords);
			marchStrs.add(abstractContent);
			query.setSortfield("createTime");
		}
		//如果带有检索词则按照相关性排序
		EsPageRecord record = ElasticsearchUtils.mutilSearchDataPage(index, type, query.getPageIndex(), query.getPageSize(), 0,0, null, query.getSortfield(), false, highLightFields, marchStrs);
		return R.success().put("data", record);
	}
	@RequestMapping("/article/edit/{articleId}")
	public String edit(@PathVariable("articleId") int articleId, Model model) {
		ArticleItem articleItem = new ArticleItem();
		if (articleId != 0) {
			articleItem = articleService.getById(articleId);
			model.addAttribute("articleModel", articleItem);
		}
		return "article/edit";
	}

	@RequestMapping("/article/deleteArticle")
	@ResponseBody
	public R deleteArticle(int articleId) {
		boolean result = false;
		try {
			result = articleService.deleteArticle(articleId);
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
			result = false;
		}
		if (result) {
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}

	@RequestMapping("/article/saveArticle")
	@ResponseBody
	public R saveArticle(ArticleSubmitItem submitItem) {

		boolean result = false;
		try {
			if (submitItem.getId() == 0) {
				submitItem.setCreateTime(DateTimeHelper.getNowDate());
				submitItem.setUpdateTime(DateTimeHelper.getNowDate());
				submitItem.setContent(submitItem.getContent());
				result = articleService.saveArticle(submitItem);
			} else {
				submitItem.setUpdateTime(DateTimeHelper.getNowDate());
				submitItem.setContent(submitItem.getContent());
				result = articleService.updateArticle(submitItem);
			}

		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
			result = false;
		}
		if (result) {
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}
	
}