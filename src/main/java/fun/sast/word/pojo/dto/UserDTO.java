package fun.sast.word.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-05-07 11:03
 **/
@Data
@AllArgsConstructor
public class UserDTO {
    private String username;

    private String password;
}
