package warmer.star.blog.security;


import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import warmer.star.blog.security.oauth2.AbstractPrincipalExtractor;

public class MyUserInfoTokenServices extends UserInfoTokenServices {

    public MyUserInfoTokenServices(String userInfoEndPointUrl, String clienId, AbstractPrincipalExtractor principalExtractor) {
        super(userInfoEndPointUrl, clienId);
        super.setPrincipalExtractor(principalExtractor);
    }
}
