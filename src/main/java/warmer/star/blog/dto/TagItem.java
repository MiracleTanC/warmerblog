/**    
* @Title: TagItem.java  
* @Package warmer.star.blog.dto  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年6月5日 下午3:38:04  
* @version V1.0    
*/
package warmer.star.blog.dto;

import java.io.Serializable;

public class TagItem implements Serializable {
  
	private static final long serialVersionUID = 1L;
	private Integer id;
	public String name;
	public String alia;
	public String getAlia() {
		return alia;
	}
	public void setAlia(String alia) {
		this.alia = alia;
	}
	public String color;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
