package warmer.star.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("warmer.star.blog.mapper")

public class Application extends SpringBootServletInitializer{//war包tomcat模式
	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
		//application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
/*public class BlogApplication {//jar 直接启动模式
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}*/
