package fun.sast.word.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.sast.word.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用于用户注册使用，可选表(User)表数据库访问层
 *
 * @author cxy621
 * @since 2024-04-27 23:54:53
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}

