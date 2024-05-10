package fun.sast.word.service;


import fun.sast.word.entity.User;

/**
 * 用于用户注册使用，可选表(User)表服务接口
 *
 * @author cxy621
 * @since 2024-04-27 23:54:53
 */
public interface UserService {
    String addUser(User user);
}

