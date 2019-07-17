package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.dto.ArticleSubmitItem;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.model.ArticleFile;
import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper {
    List<ArticleItem> getArticleList(ArticleQueryItem queryItem);
    List<ArticleItem> getRecommendArticleList();
    List<ArticleItem> getTopReadArticleList();
    List<Map<String,Object>> getRelvantArticle(@Param("articleId")Integer articleId,@Param("tagIds") String tagIds);
    List<ArticleFile> getImageById(@Param("articleId") int articleId);
    ArticleDetail getContentById(@Param("articleId") int articleId);
    ArticleItem getById(@Param("articleId") int articleId);
    int saveArticle(ArticleSubmitItem submitItem);
    void saveContent(ArticleDetail submitItem);
    void saveImage(List<ArticleFile> fileList);
    boolean updateArticle(ArticleSubmitItem submitItem);
    boolean deleteArticle(@Param("articleId") int articleId);
    boolean deleteImage(@Param("articleId") int articleId);
    boolean deleteContent(@Param("articleId") int articleId);
    void updateArticleViewCount(@Param("articleId") int articleId,@Param("viewCount") int viewCount);
    void updateArticleTags(@Param("articleId") int articleId,@Param("articleTags") String articleTags);
}
