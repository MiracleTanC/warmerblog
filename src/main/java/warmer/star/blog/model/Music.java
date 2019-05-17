package warmer.star.blog.model;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class Music implements Serializable {
	private Integer id;
    private String title; 
    private String url;
	private String singer;
	private String converUrl;
	private String totalTime;
    private Integer sortCode;
	private Date createOn;
    private Date updateOn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSortCode() {
		return sortCode;
	}
	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	public Date getUpdateOn() {
		return updateOn;
	}
	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}
	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getConverUrl() {
		return converUrl;
	}

	public void setConverUrl(String converUrl) {
		this.converUrl = converUrl;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}


}
