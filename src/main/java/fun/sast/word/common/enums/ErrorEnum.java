package fun.sast.word.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-04-28 00:52
 **/
@AllArgsConstructor
@Getter
public enum ErrorEnum {
    USER_EXISTS(2000, "用户名重复"),
    USER_NOT_EXISTS(2001, "用户不存在"),


    SENTENCE_NOT_EXISTS(3001, "不存在对应句子记录"),
    ;
    private final Integer errCode;
    private final String errMsg;
}
