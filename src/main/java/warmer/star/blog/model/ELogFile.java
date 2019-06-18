package warmer.star.blog.model;

import java.util.Date;

public class ELogFile {
    private Integer id;
    private Integer eLogId;
    private String fileurl;
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer geteLogId() {
        return eLogId;
    }

    public void seteLogId(Integer eLogId) {
        this.eLogId = eLogId;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
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
}
