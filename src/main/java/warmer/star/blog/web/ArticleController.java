package warmer.star.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.dto.ArticleSubmitItem;
import warmer.star.blog.dto.TagItem;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.model.ArticleFile;
import warmer.star.blog.model.Tag;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.service.TagService;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.PageRecord;
import warmer.star.blog.util.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class ArticleController extends BaseController {

    // 文章service
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/article")
    @PreAuthorize("hasPermission('/article','article_select') or hasRole('ROLE_admin')")
    public String index(ArticleQueryItem queryItem) {
        return "article/index";
    }

    @RequestMapping("/getArticlelist")
    @ResponseBody
    public R getArticlelist(@RequestBody ArticleQueryItem query) {
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
        String searchWords = query.getTitle();
        List<String> highLightFields = new ArrayList<String>();
        highLightFields.add("title");
        highLightFields.add("abstractContent");
        List<HashMap<String, String>> marchStrs = new ArrayList<HashMap<String, String>>();
        if (StringUtil.isNotEmpty(searchWords)) {
            HashMap<String, String> title = new HashMap<String, String>();
            title.put("condition", "title");
            title.put("value", searchWords);
            marchStrs.add(title);
            HashMap<String, String> abstractContent = new HashMap<String, String>();
            abstractContent.put("condition", "abstractContent");
            abstractContent.put("value", searchWords);
            marchStrs.add(abstractContent);
            query.setSortfield("createTime");
        }
        //如果带有检索词则按照相关性排序
        //EsPageRecord record = ElasticsearchUtils.mutilSearchDataPage(index, type, query.getPageIndex(), query.getPageSize(), 0,0, null, query.getSortfield(), false, highLightFields, marchStrs);
        //return R.success().put("data", record);
        return R.success().put("data", "");
    }
    @RequestMapping("/article/edit/{t}/{articleId}")
    public String edit(@PathVariable("t") Integer t, @PathVariable("articleId") Integer articleId, Model model) {
        ArticleItem articleItem = new ArticleItem();
        if (articleId != null && articleId != 0) {
            articleItem = articleService.getById(articleId);
            ArticleDetail contentItem = articleService.getContentById(articleId);
            if (contentItem != null && StringUtils.isNotBlank(contentItem.getContent())) {
                articleItem.setContent(contentItem.getContent());
            }
            model.addAttribute("articleModel", articleItem);
        }
        if (t == 0) {
            return "article/markdown";
        }
        return "article/edit";
    }

    @RequestMapping("/article/deleteArticle")
    @ResponseBody
    @PreAuthorize("hasPermission('/article/deleteArticle','article_delete')")
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
    @PreAuthorize("hasPermission('/article/saveArticle','article_edit')")
    public R saveArticle(ArticleSubmitItem submitItem) {
        boolean result = false;
        try {
            Date time = DateTimeHelper.getNowDate();
            List<String> tagIds=new ArrayList<>();
            //更新标签库
            if (StringUtils.isNotBlank(submitItem.getArticleTags())) {
                String[] tags = submitItem.getArticleTags().split(",");
                if (tags != null && tags.length > 0) {
                    for (String tag : tags) {
                        //检查tag是存在
                        List<Tag> tagList = tagService.getTagByName(tag);
                        if (tagList != null && tagList.size() > 0) {
                            tagIds.add(tagList.get(0).getId().toString());
                            continue;
                        }
                        TagItem t = new TagItem();
                        t.setAlia(tag);
                        t.setColor("#EB6841");
                        t.setName(tag);
                        Integer tageId=tagService.saveTag(t);
                        tagIds.add(tageId.toString());
                    }
                }
            }
            String articleTagIds=String.join(",",tagIds);
            submitItem.setArticleTags(articleTagIds);
            if (submitItem.getId() == 0) {
                submitItem.setCreateTime(time);
                submitItem.setUpdateTime(time);
                articleService.saveArticle(submitItem);
                int id = Integer.parseInt(submitItem.getId().toString());
                ArticleDetail detail = new ArticleDetail();
                detail.setArticleId(id);
                detail.setContent(submitItem.getContent());
                detail.setCreateTime(time);
                detail.setUpdateTime(time);
                articleService.saveContent(detail);
                List<ArticleFile> files = new ArrayList<ArticleFile>();
                if (submitItem.getCoverImageList()!=null&&submitItem.getCoverImageList().length > 0) {
                    for (String url : submitItem.getCoverImageList()) {
                        ArticleFile img = new ArticleFile();
                        img.setArticleId(id);
                        img.setCoverImage(url);
                        img.setCreateTime(time);
                        img.setUpdateTime(time);
                        files.add(img);
                    }
                }
                if (files.size() > 0) {
                    articleService.saveImage(files);
                }
                result = true;
            } else {
                int id = submitItem.getId();
                submitItem.setUpdateTime(DateTimeHelper.getNowDate());
                articleService.updateArticle(submitItem);
                //删除正文
                articleService.deleteContent(id);
                ArticleDetail detail = new ArticleDetail();
                detail.setArticleId(id);
                detail.setContent(submitItem.getContent());
                detail.setCreateTime(time);
                detail.setUpdateTime(time);
                //添加正文
                articleService.saveContent(detail);
                List<ArticleFile> files = new ArrayList<ArticleFile>();
                if (submitItem.getCoverImageList()!=null&&submitItem.getCoverImageList().length > 0) {
                    for (String url : submitItem.getCoverImageList()) {
                        ArticleFile img = new ArticleFile();
                        img.setArticleId(id);
                        img.setCoverImage(url);
                        img.setCreateTime(time);
                        img.setUpdateTime(time);
                        files.add(img);
                    }
                }
                //删除封面
                articleService.deleteImage(id);
                if (files.size() > 0) {
                    //添加封面
                    articleService.saveImage(files);
                }
                result = true;
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