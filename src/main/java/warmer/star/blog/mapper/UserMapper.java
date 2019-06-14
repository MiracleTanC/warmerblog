package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import warmer.star.blog.model.User;
import warmer.star.blog.model.UserInfo;

import java.util.List;

public interface UserMapper {
    /**
     * 获取用户凭证
     * @param username 账号
     * @return
     */
    User getUser(@Param("username") String username);
    User getByOpenId(@Param("openId") String openId);
    /**
     * 获取所有的用户
     * @return
     */
    List<User> allUser();

    UserInfo getUserInfo(@Param("username") String username);

    void updateAvatar(@Param("url") String url, @Param("username") String username);

    void updatePassword(User user);
    int addUser(User user);

    void addUserInfo(UserInfo userInfo);
    void updateUserInfo(UserInfo userInfo);
}
