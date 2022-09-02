package top.ykong.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.ykong.entity.User;
import top.ykong.entity.UserSexEnum;
import top.ykong.util.PageEx;

import java.util.Date;
import java.util.Map;

public interface UserService {

    PageEx<User> showPage(int page, int limit);

    Page<User> showAll(int page, int limit);

    User checkPassword(String account, String password);

    Map<String, Object> registerUser(String phone_number, String password);

    Map<String, Object> modifyUserInfo(String phone_number, String username, Date birth, UserSexEnum sex, String hometown, String account, String mail);

    Map<String, Object> modifyUserPassword(String account, String password);

    int updateStatus(User user);
}
