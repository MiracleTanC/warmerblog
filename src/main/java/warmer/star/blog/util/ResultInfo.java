package warmer.star.blog.util;

/**
 * REST请求相应体
 * FILE: warmer.miao.blog.util.ResultInfo.java
 * MOTTO:  不积跬步无以至千里,不积小流无以至千里
 * AUTHOR: EumJi
 * DATE: 2017/4/9
 * TIME: 15:34
 */
public class ResultInfo {

    // 操作结果
    private String resultCode;

    // 错误信息
    private String errorInfo;

    // 附属对象
    private Object object;

    public ResultInfo(String resultCode, String errorInfo) {
        super();
        this.resultCode = resultCode;
        this.errorInfo = errorInfo;
    }

    public ResultInfo(String resultCode, String errorInfo, Object object) {
        super();
        this.resultCode = resultCode;
        this.errorInfo = errorInfo;
        this.object = object;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}

