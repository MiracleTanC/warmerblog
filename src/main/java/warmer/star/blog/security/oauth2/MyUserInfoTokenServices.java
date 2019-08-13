package warmer.star.blog.security.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

public class MyUserInfoTokenServices extends UserInfoTokenServices {

    public MyUserInfoTokenServices(String userInfoEndPointUrl, String clienId, AbstractPrincipalExtractor principalExtractor) {
        super(userInfoEndPointUrl, clienId);
        super.setPrincipalExtractor(principalExtractor);
    }
}
