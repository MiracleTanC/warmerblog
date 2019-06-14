package warmer.star.blog.security;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xinshengshu on 2018/9/25.
 */
@Component
public class WebSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        String[] returnurl = request.getParameterValues("return");
        System.out.println(JSON.toJSON(returnurl));
        if (returnurl != null && returnurl.length > 0) {
            redirectStrategy.sendRedirect(request, response, returnurl[0]);
        } else {
            redirectStrategy.sendRedirect(request, response, "/home");
        }
        // TODO: 2019/6/12 消息队列邮件通知登录信息 
    }
}