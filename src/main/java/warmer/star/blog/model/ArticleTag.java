package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleTag implements Serializable {

    private Integer articleId;  //文章id

    private Integer tagId;  //标签id

    private String tagName;  //标签名


}
