package warmer.star.blog.config;

/*import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;*/

//@Configuration
/**
 * springboot 自动装配这些配置,对应的字段放在yml的spring下即可,若是不放在spring下,需自己加载配置,就用到了下边的写法
 * @author Tan
 *
 */
public class MailConfig {
	/*@Value("${tcmail.mail.default-encoding}")
	private String encoding;
	@Value("${tcmail.mail.host}")
	private String host;
	@Value("${tcmail.mail.port}")
	private String port;
	@Value("${tcmail.mail.protocol}")
	private String protocol;
	@Value("${tcmail.mail.username}")
	private String username;
	*//**
	 * 邮箱授权码,非登录密码
	 *//*
	@Value("${tcmail.mail.password}")
	private String password;

	@Value("${tcmail.mail.auth}")
	private String auth;
	@Bean(name = "JavaMailSender")
	public JavaMailSender getSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setUsername(this.username);
		javaMailSender.setPassword(this.password);
		javaMailSender.setHost(this.host);
		javaMailSender.setPort(Integer.valueOf(this.port));
		javaMailSender.setDefaultEncoding(this.encoding);
		
		Properties props = new Properties();
        props.setProperty("mail.smtp.host", this.host);
        props.setProperty("mail.smtp.auth", this.auth);
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });
        javaMailSender.setSession(session);
		return javaMailSender;
	}*/
}
