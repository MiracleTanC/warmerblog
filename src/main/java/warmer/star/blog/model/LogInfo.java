package warmer.star.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LogInfo implements Serializable {

	private Integer id;

    private String userId; //用户名

    private String url;  //请求的url

    private String ip;  //请求的ip

    private String method;  //http请求的方法

    private String args;  //请求方法的参数

    private String classMethod;  //对应的类方法

    private String exception;  //异常信息

    private Date operateTime;  //操作时间
}
