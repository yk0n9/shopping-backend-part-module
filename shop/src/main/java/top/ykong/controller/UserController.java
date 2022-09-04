package top.ykong.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ykong.entity.User;
import top.ykong.entity.UserSexEnum;
import top.ykong.service.UserService;
import top.ykong.util.PageEx;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/pageUser")
    @ApiOperation("分页查询")
    public PageEx<User> hello1(@RequestParam int page, @RequestParam int limit) throws InterruptedException {
        Thread.sleep(5000);
        return userService.showPage(page, limit);
    }

    @GetMapping("/showAll")
    public Page<User> shwoAll(@RequestParam int page, @RequestParam int limit) {
        return userService.showAll(page, limit);
    }

    @PostMapping("/register")
    public Object reg(@RequestParam String phone_number, @RequestParam String password) {
        return userService.registerUser(phone_number, password);
    }

    @GetMapping("/getUserOfLogin")
    public PageEx<User> getUserOfLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object user = redisTemplate.opsForValue().get(token);
        if (user != null) {
            return new PageEx<>(200, "获取登陆用户成功", (User) user);
        }
        return new PageEx<>(400, "获取登录用户失败");
    }

    @PostMapping("/login")
    public Object login(@RequestParam String account, @RequestParam String password, HttpSession session) {
        User user = userService.checkPassword(account, password);
        if (user != null) {
            user.setAccount(account);
            user.setStatus(1);
            userService.updateStatus(user);
            String token = UUID.randomUUID().toString();
            session.setAttribute(token, user);
            redisTemplate.opsForValue().set(token, user, Duration.ofMinutes(30));

            System.out.println("user : " + token);
            return new PageEx<>(200, "登陆成功");
        }

        return new PageEx<>(400, "登陆失败");
    }

    @GetMapping("/logout")
    public Object logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        session.invalidate();
        user.setStatus(0);
        userService.updateStatus(user);
        return new PageEx<>(200, "您已登出");
    }

    @PostMapping("/updatePassword")
    public Object update(@RequestParam String account, @RequestParam String password) {
        return userService.modifyUserPassword(account, password);
    }

    @PostMapping("/add_information")
    public Object add_information(@RequestParam String phone_number, @RequestParam String username, @RequestParam Date birth, @RequestParam UserSexEnum sex, @RequestParam String hometown, @RequestParam String account, @RequestParam String mail) {
        return userService.modifyUserInfo(phone_number, username, birth, sex, hometown, account, mail);
    }

}
