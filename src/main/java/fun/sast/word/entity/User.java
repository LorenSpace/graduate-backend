package fun.sast.word.entity;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于用户注册使用，可选表(User)表实体类
 *
 * @author cxy621
 * @since 2024-04-27 23:54:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 926580923683755811L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private LocalDateTime createdTime;
}

