package warmer.star.blog.dto;

import java.io.Serializable;
import java.util.Date;

import warmer.star.blog.enums.ArticleStatus;
import warmer.star.blog.util.DateTimeHelper;


public class ArticleSubmitItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer categoryId; //分类id 
    private String title;   //标题
    private String content;  //内容
    private String abstractContent; //摘要内容
    private String publishTime; //显示时间
	private String coverImage; //封面图片
	private String[] coverImageList; //封面图片
	private String articleTags; //文章标签
    private Integer openComment; //打开评论
    private Integer isRecommend; //是否推荐
    private Integer status;  //状态
    private Integer showStyle;  //显示样式
    private Integer articleType;
    private String author; //作者
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间

    public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}

	public String getPublishTime() {
		return  publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getCoverImage() {
		if(this.coverImageList!=null&&this.coverImageList.length>0){
			return String.join(";", this.coverImageList);
		}
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		if(coverImageList!=null&&coverImageList.length>0){
			this.coverImage=String.join(";", coverImageList);
		}else {
			this.coverImage = coverImage;
		}
		
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

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	/**
	 * @return the coverImageList
	 */
	public String[] getCoverImageList() {
		return coverImageList;
	}

	/**
	 * @param coverImageList the coverImageList to set
	 */
	public void setCoverImageList(String[] coverImageList) {
		this.coverImageList = coverImageList;
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
