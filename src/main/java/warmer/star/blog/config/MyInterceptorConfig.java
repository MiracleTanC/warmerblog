package warmer.star.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import warmer.star.blog.interceptor.LoggerInterceptor;

@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/");//有多个拦截器继续add进去

		super.addInterceptors(registry);
	}
	//在spring2.0+的版本中，只要用户自定义了拦截器，则静态资源会被拦截。但是在spring1.0+的版本中，是不会拦截静态资源的。
	//因此，在使用spring2.0+时，配置拦截器之后，我们要把静态资源的路径加入到不拦截的路径之中。
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("classpath:/resources/")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
	}
}
