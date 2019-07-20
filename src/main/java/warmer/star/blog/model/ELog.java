package warmer.star.blog.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ELog {
    private Integer id;
    private String content;  //内容
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private List<ELogFile> eLogFiles;

}
