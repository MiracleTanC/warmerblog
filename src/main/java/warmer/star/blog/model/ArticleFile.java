package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class ArticleFile implements Serializable {
	private Integer id;
	private Integer articleId;
    private String coverImage;  //封面图片
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
}
