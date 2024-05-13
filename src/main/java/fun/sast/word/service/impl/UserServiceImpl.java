package fun.sast.word.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.sast.word.common.enums.ErrorEnum;
import fun.sast.word.entity.User;
import fun.sast.word.exception.LocalRunTimeException;
import fun.sast.word.mapper.UserMapper;
import fun.sast.word.service.UserService;
import fun.sast.word.util.MD5SaltUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用于用户注册使用，可选表(User)表服务实现类
 *
 * @author cxy621
 * @since 2024-04-27 23:54:53
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String addUser(User user) {
        if (findUserByUsername(user.getUsername()) != null) {
            throw new LocalRunTimeException(ErrorEnum.USER_EXISTS);
        }
        user.setPassword(MD5SaltUtil.md5WithSalt(user.getPassword()));
        user.setCreatedTime(LocalDateTime.now());
        userMapper.insert(user);
        return "success";
    }

    private User findUserByUsername(String username) {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUsername, username)
        );
    }
}

