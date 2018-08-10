/**    
* @Title: BannerItem.java  
* @Package warmer.star.blog.dto  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年6月6日 下午4:40:57  
* @version V1.0    
*/
package warmer.star.blog.dto;

import java.io.Serializable;
import java.util.Date;

import warmer.star.blog.util.DateTimeHelper;

/**  
* @ClassName: BannerItem  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author tc  
* @date 2018年6月6日 下午4:40:57  
*    
*/
public class BannerItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer serialNumber;
	private Integer id;
    private String title; 
    private String imgSrc; 
    private String linkUrl;
    private Integer sortCode; 
    private Integer isDeleted; 
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
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getSortCode() {
		return sortCode;
	}
	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCreateOn() {
		String dataStr=DateTimeHelper.dateToShortStr(this.createOn);
        return dataStr;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	public String getUpdateOn() {
		String dataStr=DateTimeHelper.dateToShortStr(this.updateOn);
        return dataStr;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
		
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
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
	private Date createOn;   
    private Date updateOn;  
    private Integer isShow; 
    
}
