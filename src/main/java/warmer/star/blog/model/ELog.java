package warmer.star.blog.model;

import java.util.Date;
import java.util.List;

public class ELog {
    private Integer id;
    private String content;  //内容
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private List<ELogFile> eLogFiles;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<ELogFile> geteLogFiles() {
        return eLogFiles;
    }

    public void seteLogFiles(List<ELogFile> eLogFiles) {
        this.eLogFiles = eLogFiles;
    }
}
