package fun.sast.word.controller;


import fun.sast.word.entity.User;
import fun.sast.word.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于用户注册使用，可选表(User)表控制层
 *
 * @author cxy621
 * @since 2024-04-27 23:54:52
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.addUser(user);
    }
}

