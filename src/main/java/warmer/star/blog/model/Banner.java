package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Banner implements Serializable {
	private Integer id;
    private String title; 
    private String imgSrc; 
    private String linkUrl;
    private Integer sortCode; 
    private Integer isDeleted;
	private Date createOn;   
    private Date updateOn;  
    private Integer isShow;
}
