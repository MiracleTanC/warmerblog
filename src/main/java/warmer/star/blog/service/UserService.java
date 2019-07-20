package warmer.star.blog.service;


import warmer.star.blog.model.User;
import warmer.star.blog.model.UserInfo;

import java.util.List;

public interface UserService {


    User getUserByUsername(String username);

    User getByOpenId(String openId);
    List<User> getUserList();
    UserInfo getUserInfo(String username);

    void updateAvatar(String url, String username);

    void updatePassword(User user);

    void updateUserInfo(UserInfo userInfo);

    User getCurrentUser();
}
