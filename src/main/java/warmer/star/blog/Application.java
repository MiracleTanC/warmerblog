package warmer.star.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("warmer.star.blog.mapper")
/*@Scope(value = WebApplicationContext.SCOPE_SESSION)*/
/*public class Application extends SpringBootServletInitializer{//war包tomcat模式
	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        //application.setBannerMode(Banner.Mode.CONSOLE);
		application.setBannerMode(Banner.Mode.LOG);
        application.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}*/
public class Application {//jar 直接启动模式
	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		SpringApplication application = new SpringApplication(Application.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}
