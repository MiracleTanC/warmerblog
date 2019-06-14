package warmer.star.blog.enums;

public enum UserSource {
    facebook("Facebook", 0),
    github("Github", 1),
    qq("QQ", 2),
    wechat("微信", 3);

    private final  String name;

    private  Integer value;

    public String getName() {
        return name;
    }

    public final Integer getValue() {
        return value;
    }

    private UserSource(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
    public static UserSource GetAnswerStyle(Integer value) {
        switch (value) {
            case 0:
                return UserSource.facebook;
            case 1:
                return UserSource.github;
            case 2:
                return UserSource.qq;
            case 3:
                return UserSource.wechat;
            default:
                return null;
        }
    }
}
