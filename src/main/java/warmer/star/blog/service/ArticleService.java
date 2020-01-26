package warmer.star.blog.service;

import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.dto.ArticleSubmitItem;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.model.ArticleFile;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    List<ArticleItem> getArticleList(ArticleQueryItem queryItem);
    List<ArticleItem> getRecommendArticleList();
    List<ArticleItem> getTopReadArticleList();
    List<Map<String,Object>> getRelvantArticle(Integer articleId,String tagIds);
    List<Integer> getNoAbstractArticleId();
    ArticleItem getById(int articleId);
    ArticleDetail getContentById(int articleId);
    int saveArticle(ArticleSubmitItem submitItem);
    void saveContent(ArticleDetail submitItem);
    void saveImage(List<ArticleFile> fileList);
    boolean updateArticle(ArticleSubmitItem submitItem);
    void updateAbstractById(int articleId,String abstractContent);
    boolean deleteArticle(int articleId);
    boolean deleteImage(int articleId);
    boolean deleteContent(int articleId);
    void updateArticleViewCount(int articleId,int viewCount);
    void updateArticleTags(int articleId,String articleTags);
}
