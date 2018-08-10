package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.dto.ArticleSubmitItem;

public interface ArticleMapper {
    List<ArticleItem> getArticleList(ArticleQueryItem queryItem);
    List<ArticleItem> getRecommendArticleList();
    List<ArticleItem> getTopReadArticleList();
    ArticleItem getById(@Param("articleId") int articleId);
    boolean saveArticle(ArticleSubmitItem submitItem);
    boolean updateArticle(ArticleSubmitItem submitItem);
    boolean deleteArticle(@Param("articleId") int articleId);
}
