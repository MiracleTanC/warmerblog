package warmer.star.blog.model;

import java.io.Serializable;
import java.util.Date;


public class ArticleFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer articleId;
    private String coverImage;  //封面图片
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
