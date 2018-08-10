package warmer.star.blog.model;

/**
 * 上传图片的响应体
 * FILE: warmer.miao.blog.vo.PhotoResult.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/21
 * TIME: 22:20
 */
public class PhotoResult {

    private int success;    //成功标准 0失败 1成功
    private String url;     //图片url
    private String message; //错误信息

    public PhotoResult() {
    }

    public PhotoResult(int success, String url, String message) {
        this.success = success;
        this.url = url;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
