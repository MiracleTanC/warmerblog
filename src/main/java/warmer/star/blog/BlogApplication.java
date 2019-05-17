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

public class BlogApplication extends SpringBootServletInitializer{//war包tomcat模式
	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BlogApplication.class);  
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlogApplication.class);
	}
}
/*public class BlogApplication {//jar 直接启动模式
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}*/
