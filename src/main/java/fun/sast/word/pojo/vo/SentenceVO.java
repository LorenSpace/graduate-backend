package fun.sast.word.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-05-07 12:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentenceVO {
    private String content;

    private Integer isEuphemism;

    private LocalDateTime createdTime;
}
