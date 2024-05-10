package fun.sast.word.exception;

import fun.sast.word.common.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-04-28 01:06
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class LocalRunTimeException extends RuntimeException {
    private ErrorEnum errorEnum;

    //默认错误
    public LocalRunTimeException(String message) {
        super(message);
    }

    public LocalRunTimeException(ErrorEnum errorEnum) {
        super(errorEnum.getErrMsg());
        this.errorEnum = errorEnum;
    }
}
