package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tag implements Serializable {
	private Integer id;
    private String tagName; //标签名
    private String aliasName;  //别名
    private String color;  //别名

}
