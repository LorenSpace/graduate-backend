package fun.sast.word.exception;

import fun.sast.word.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-04-28 01:07
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(LocalRunTimeException.class)
    public Result<Void> localRunTimeException(LocalRunTimeException e) {
        log.error("异常", e);
        if (e.getErrorEnum() != null) {
            return Result.failure(e.getErrorEnum());
        } else {
            return Result.failure(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handlerValidationException(MethodArgumentNotValidException e) {
        log.error("参数校验异常", e);
        // 流处理，获取错误信息
        String messages = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return Result.failure(messages);
    }

    // 处理 form data 方式调用接口时，参数校验异常
    @ExceptionHandler(BindException.class)
    public Result<Void> bindExceptionHandler(BindException e) {
        log.error("参数校验异常", e);
        String message = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return Result.failure(message);
    }

    // 处理所有未被捕获的异常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("未知异常", e);
        return Result.failure(e.getMessage());
    }
}
