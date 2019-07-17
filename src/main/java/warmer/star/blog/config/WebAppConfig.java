
package warmer.star.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import warmer.star.blog.interceptor.MyErrorPageRegistrar;

import javax.servlet.MultipartConfigElement;


@Configuration
@Data
public class WebAppConfig{

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${file.location}")
    private String location;

    @Value("${blogowner.openid}")
    private String openid;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("10MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new MyErrorPageRegistrar();
    }
}