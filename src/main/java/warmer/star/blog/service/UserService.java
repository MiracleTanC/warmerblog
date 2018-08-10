package warmer.star.blog.service;


import warmer.star.blog.model.User;
import warmer.star.blog.model.UserInfo;

public interface UserService {


    User getUserByUsername(String username);

    UserInfo getUserInfo(String username);

    void updateAvatar(String url, String username);

    void updatePassword(User user);

    void updateUserInfo(UserInfo userInfo);

    User getCurrentUser();
}
