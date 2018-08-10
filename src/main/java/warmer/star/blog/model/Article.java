package warmer.star.blog.model;

import java.io.Serializable;
import java.util.Date;

import warmer.star.blog.enums.ArticleStatus;
import warmer.star.blog.util.DateTimeHelper;


public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer categoryId; //分类id 
    private String title;   //标题
    private String content;  //内容
    private String abstractContent; //摘要内容
    private String publishTime; //显示时间
    private String coverImage; //封面图片
    private String articleTags; //文章标签
    private Integer openComment; //打开评论
    private Integer isRecommend; //是否推荐
    private Integer status;  //状态
    private String author; //作者
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private Integer showCount;  //浏览数
    private Integer showStyle;  //展示样式
    private Integer articleType;  //原创=0,转载=1
    
    
    public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Integer getOpenComment() {
		return openComment;
	}

	public void setOpenComment(Integer openComment) {
		this.openComment = openComment;
	}

	

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDisPlayStatus() {
    	ArticleStatus state=ArticleStatus.values()[this.status] ;
        return state.toString();
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
    	String dataStr=DateTimeHelper.dateToShortStr(this.createTime);
        return dataStr;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
    	String dataStr=DateTimeHelper.dateToShortStr(this.updateTime);
        return dataStr;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

	/**
	 * @return the articleTags
	 */
	public String getArticleTags() {
		return articleTags;
	}

	/**
	 * @param articleTags the articleTags to set
	 */
	public void setArticleTags(String articleTags) {
		this.articleTags = articleTags;
	}

	/**
	 * @return the showStyle
	 */
	public Integer getShowStyle() {
		return showStyle;
	}

	/**
	 * @param showStyle the showStyle to set
	 */
	public void setShowStyle(Integer showStyle) {
		this.showStyle = showStyle;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * @return the articleType
	 */
	public Integer getArticleType() {
		return articleType;
	}

	/**
	 * @param articleType the articleType to set
	 */
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}



}
