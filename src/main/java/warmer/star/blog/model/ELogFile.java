package warmer.star.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class ELogFile {
    private Integer id;
    private Integer eLogId;
    private String fileurl;
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
}
