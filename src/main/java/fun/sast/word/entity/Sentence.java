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
 * 存储输入的句子，检测是否存在委婉语(Sentence)表实体类
 *
 * @author cxy621
 * @since 2024-04-27 23:54:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sentence implements Serializable {
    @Serial
    private static final long serialVersionUID = 577090507288538414L;

    // MyBatisPlus 需要使用注解指定对应主键
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long uId;
    // 限制 2000 字符
    private String content;

    private Integer isEuphemism;

    private LocalDateTime createdTime;
}

