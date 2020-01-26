package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Partner implements Serializable {

	private Integer id;

    private String siteName;    //友链名称

    private String siteUrl; //友链URL

    private String siteDesc; //友链描述

    private Integer sort;  //排序

}
