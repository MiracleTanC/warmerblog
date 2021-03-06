package warmer.star.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CompositeFilter;
import warmer.star.blog.security.oauth2.*;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	@Autowired
	CustomUserService customUserService;
	@Autowired
	CustomPermissionProvider customPermissionProvider;

	//@Autowired
	//ApiSuccessHandler apiSuccessHandler;

	@Autowired
	WebSuccessHandler webSuccessHandler;
	@Autowired
	WebFailedHandler webFailedHandler;

	//@Autowired
	//TokenAuthorizationFilter tokenAuthorizationFilter;

	@Autowired
	GithubPrincipalExtractor githubPrincipalExtractor;
	@Autowired
	QQPrincipalExtractor qqPrincipalExtractor;
	@Autowired
	GiteePrincipalExtractor giteePrincipalExtractor;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable();
		// 允许iframe加载 // http.authorizeRequests().anyRequest().permitAll().and().headers().frameOptions().disable();
		http.authorizeRequests().
				antMatchers("/qiniu/**").
				permitAll().and().headers().frameOptions().disable(); // 允许iframe
		http.authorizeRequests().antMatchers("/", "/kg/**",  "/login/**","/admin", "/static/**").permitAll()
				.antMatchers("/article/**", "/banner/**", "/category/**", "/partner/**", "/tag/**").authenticated()
				//.anyRequest().authenticated()
				.and().logout().logoutUrl("/loginOut").logoutSuccessUrl("/")
				.and().rememberMe().tokenValiditySeconds(3600)
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)//在网站基本认证之前添加ssoFilter过滤器
				.formLogin()
				.loginPage("/login")
				.successHandler(webSuccessHandler)
				.defaultSuccessUrl("/home")
				.failureHandler(webFailedHandler)
				.failureUrl("/login?error=true")
				.permitAll();
		//http.addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//忽略静态资源
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder()); // user Details Service验证
	}
	 /**
     * 注入自定义权限验证,CustomPermissionProvider
     */
    @Bean
    public DefaultWebSecurityExpressionHandler myWebSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		//handler.setDefaultRolePrefix("");//默认情况下，如果提供的角色不以“ROLE_”开头 这里设置不让以ROLE_开头
        handler.setPermissionEvaluator(customPermissionProvider);
        return handler;
    }
	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {//springboot 2.0情况下适用
		return  new GrantedAuthorityDefaults("");//默认情况下，如果提供的角色不以“ROLE_”开头 这里设置不让以ROLE_开头
	}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/*注册一个额外的Filter：OAuth2ClientContextFilter
	 * 主要作用是重定向，当遇到需要权限的页面或URL，代码抛出异常，这时这个Filter将重定向到OAuth鉴权的地址
	 */
	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
		return new ClientResources("github");
	}
	@Bean
	@ConfigurationProperties("qq")
	public ClientResources qq() {
		return new ClientResources("qq");
	}
	@Bean
	@ConfigurationProperties("gitee")
	public ClientResources gitee() {
		return new ClientResources("gitee");
	}
	//自定义过滤器，用于拦截oauth2第三方登录返回code的url,并根据code,clientid,clientSecret去授权服务器拿accace_token
	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(github(), "/login/github", githubPrincipalExtractor));
		filters.add(ssoFilter(qq(), "/login/qq", qqPrincipalExtractor));
		filters.add(ssoFilter(gitee(), "/login/gitee", giteePrincipalExtractor));
		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, String path, AbstractPrincipalExtractor principalExtractor) {
		//OAuth2ClientAuthenticationProcessingFilter
		//它的构造器需要传入defaultFilterProcessesUrl，用于指定这个filter拦截哪个url。
		//它依赖OAuth2RestTemplate来获取token
		//还依赖ResourceServerTokenServices进行校验token
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		//对rest template的封装，为获取token等提供便捷方法
		//DefaultUserInfoRestTemplateFactory实例了OAuth2RestTemplate,这个提供了OAuth2RestTemplate
		filter.setAuthenticationSuccessHandler(webSuccessHandler);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);

		AuthorizationCodeAccessTokenProvider authCodeProvider = new AuthorizationCodeAccessTokenProvider();
		authCodeProvider.setStateMandatory(false);
		AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(authCodeProvider));
		template.setAccessTokenProvider(provider);

		UserInfoTokenServices tokenServices = new MyUserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId(), principalExtractor);
		tokenServices.setRestTemplate(template);
		filter.setTokenServices(tokenServices);
		return filter;
	}


}
