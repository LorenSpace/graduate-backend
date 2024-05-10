package fun.sast.word.response;

import fun.sast.word.common.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 17:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Boolean success;
    private String errMsg;
    private Integer errCode;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(true, null, null, data);
    }

    public static Result<Void> failure(String errMsg) {
        return new Result<>(false, errMsg, 5000, null);
    }

    public static Result<Void> failure(ErrorEnum error) {
        return new Result<>(false, error.getErrMsg(), error.getErrCode(), null);
    }
}
