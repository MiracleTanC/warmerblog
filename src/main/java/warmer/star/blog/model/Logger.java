package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Logger implements Serializable {
    private Long id;
    //客户端请求ip
    private String clientIp;
    //客户端请求路径
    private String uri;
    //终端请求方式,普通请求,ajax请求
    private String type;
    //请求方式method,post,get等
    private String method;
    //请求参数内容,json
    private String paramData;
    //请求接口唯一session标识
    private String sessionId;
    //请求时间
    private Timestamp time;
    //接口返回时间
    private String returnTime;
    //接口返回数据json
    private String returnData;
    //请求时httpStatusCode代码，如：200,400,404等
    private String httpStatusCode;
    //请求耗时秒单位
    private int timeConsuming;

}
