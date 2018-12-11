package warmer.star.blog.service;

import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.dto.ArticleSubmitItem;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.model.ArticleFile;

import java.util.List;

public interface ArticleService {

    List<ArticleItem> getArticleList(ArticleQueryItem queryItem);
    List<ArticleItem> getRecommendArticleList();
    List<ArticleItem> getTopReadArticleList();
    ArticleItem getById(int articleId);
    ArticleDetail getContentById(int articleId);
    int saveArticle(ArticleSubmitItem submitItem);
    void saveContent(ArticleDetail submitItem);
    void saveImage(List<ArticleFile> fileList);
    boolean updateArticle(ArticleSubmitItem submitItem);
    boolean deleteArticle(int articleId);
    boolean deleteImage(int articleId);
    boolean deleteContent(int articleId);
}
