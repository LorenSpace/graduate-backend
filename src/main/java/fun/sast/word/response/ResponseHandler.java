package fun.sast.word.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 17:19
 **/
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    private static final Logger logger = LoggerFactory.getLogger(ResponseHandler.class);

    private final ObjectMapper objectMapper;

    public ResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //是否执行转换，默认为true即可
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 无入侵式的统一转化变量
     *
     * @param body                  方法返回值，即需要写入到响应流中
     * @param returnType            对应方法的返回值
     * @param selectedContentType   当前 content-type
     * @param selectedConverterType 当前转化器
     * @param request               当前请求
     * @param response              当前响应
     * @return 处理后真正写入响应流的对象
     */
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        /*
         若 controller 返回值为 String，其调用的 converter 是 StringConverter，如果还是用这个统一返回则会发生类型转换错误
         参考博文: https://jiapan.me/2018/SpringBoot-%E4%B8%AD%E7%BB%9F%E4%B8%80%E5%8C%85%E8%A3%85%E5%93%8D%E5%BA%94/
         但是这篇文章中的另一种方法不生效
        */
        // 检查是否已经是 Result 类型，避免重复包装
        if (body instanceof Result<?>) {
            return body;
        }

        // 特殊处理 String 类型，确保返回 JSON 格式的字符串
        if (body instanceof String) {
            try {
                response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                // 显式指定泛型类型为 String
                return objectMapper.writeValueAsString(Result.success((String) body));
            } catch (JsonProcessingException e) {
                logger.error("Error processing JSON", e);
            }
        }

        // 对于其他类型，统一包装为 Result 对象
        return Result.success(Objects.requireNonNull(body));
    }
}
