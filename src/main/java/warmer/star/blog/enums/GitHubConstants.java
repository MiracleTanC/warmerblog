package warmer.star.blog.enums;

public class GitHubConstants {
    public static final String CLIENT_ID = "Iv1.cd11093aae724698"; // TODO 修改成自己的
    public static final String CLIENT_SECRET = "5568f209d8111793d805cf3b9025b9bef5138951";  // TODO 修改成自己的
    public static final String CALLBACK = "http://127.0.0.1:8080/githubcallback";  // TODO 修改成自己的  [注意：callback要和注册的回调路径保持一致  否则登录授权之后会报NullPointerException]

    //获取code的url
    public static final String CODE_URL = "https://github.com/login/oauth/authorize?client_id=CLIENT_ID&state=STATE&redirect_uri=CALLBACK";
    //获取token的url
    public static final String TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&code=CODE&redirect_uri=CALLBACK";
    //获取用户信息的url
    public static final String USER_INFO_URL = "https://api.github.com/user?access_token=TOKEN";

}
