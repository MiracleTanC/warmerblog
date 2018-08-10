package warmer.star.blog.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable();
		// http.authorizeRequests().anyRequest().permitAll().and().headers().frameOptions().disable();
		// 允许iframe加载
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		http.authorizeRequests().antMatchers("/", "/login/**", "/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
				.antMatchers("/admin", "/article/**", "/banner/**", "/category/**", "/partner/**", "/tag/**").authenticated()
				.and().rememberMe().tokenValiditySeconds(3600)
				.and().formLogin().loginPage("/login").successHandler(new AuthenticationSuccessHandler() {//登录后返回之前页面
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
								String[] returnurl = request.getParameterValues("return");
								if (returnurl != null && returnurl.length > 0) {
									redirectStrategy.sendRedirect(request, response, returnurl[0]);
								} else {
									redirectStrategy.sendRedirect(request, response, "\\");
								}
								//消息队列邮件通知登录信息
							}
						}).defaultSuccessUrl("/article").failureUrl("/login?error=true").permitAll()
				.and().logout().logoutUrl("/loginOut").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**/*.*");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		AuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
		return authenticationProvider;
	}
}
