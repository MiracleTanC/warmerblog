package warmer.star.blog.dto;

import warmer.star.blog.enums.ArticleStatus;
import warmer.star.blog.model.ArticleFile;
import warmer.star.blog.model.Category;
import warmer.star.blog.util.DateTimeHelper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ArticleItem implements Serializable {
	private Integer serialNumber;
	private Integer id;
    private Integer categoryId; //分类id 
    private String title;   //标题
    private String abstractContent; //摘要内容
    private String publishTime; //显示时间
    private String  articleTags; //文章标签
    private Integer showStyle;  //展示样式
    private Integer articleType;
    private Integer editorType;//0=markedown,1=tinymce
	private List<ArticleFile> coverImageList; //封面图片
    private Integer openComment; //打开评论
    private Integer isRecommend; //是否推荐
    private Integer status;  //状态
    private String statusAliaName;  //状态
    private String author; //作者
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private Integer showCount;  //浏览数
    private Category category;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getOpenComment() {
		return openComment;
	}

	public void setOpenComment(Integer openComment) {
		this.openComment = openComment;
	}


	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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
	 * @return the statusAliaName
	 */
	public String getStatusAliaName() {
		
		if(this.status>-999) {
			ArticleStatus state=ArticleStatus.values()[this.status] ;
			return state.toString();
		}
		return this.statusAliaName;
	}

	/**
	 * @param statusAliaName the statusAliaName to set
	 */
	public void setStatusAliaName(String statusAliaName) {
		this.statusAliaName = statusAliaName;
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
	public List<ArticleFile> getCoverImageList() {
		return coverImageList;
	}

	/**
	 * @param coverImageList the coverImageList to set
	 */
	public void setCoverImageList(List<ArticleFile> coverImageList) {
		this.coverImageList = coverImageList;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * @return the serialNumber
	 */
	public Integer getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
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

	/**
	 * @return the editorType
	 */
	public Integer getEditorType() {
		return editorType;
	}

	/**
	 * @param editorType the editorType to set
	 */
	public void setEditorType(Integer editorType) {
		this.editorType = editorType;
	}


   
}
