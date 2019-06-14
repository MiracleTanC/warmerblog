package warmer.star.blog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import warmer.star.blog.util.TokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xinshengshu on 2018/9/25.
 */
@Component
public class TokenAuthorizationFilter extends GenericFilterBean {

    private final String HEADER_NAME = "Authorization";

    private final TokenUtils tokenUtils;

    public TokenAuthorizationFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = resolveToken((HttpServletRequest) request);

        if(tokenUtils.validateToken(token)){
            Authentication authentication = tokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

//        cleanAuthentication();
    }

    /**
     * 从请求头解析出token
     * @param request
     * @return token
     */
    private String resolveToken(HttpServletRequest request){
       /* Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            // 排除Cookie字段
            if (key.equalsIgnoreCase("Cookie")) {
                continue;
            }
            String value = request.getHeader(key);
            System.out.println("headerName:" + key+"value:"+value);
        }*/


        String token = request.getHeader(HEADER_NAME);
        //System.out.println("resolveToken: " + token);
        if(token==null||!token.startsWith("Bearer "))
            return null;
        else
            return token.substring(7);
    }

    private void cleanAuthentication(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
