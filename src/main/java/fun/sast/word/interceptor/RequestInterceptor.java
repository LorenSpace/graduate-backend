package fun.sast.word.interceptor;

import fun.sast.word.pojo.TraceLog;
import fun.sast.word.util.CommonUtil;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * @author: 風楪fy
 * @create: 2022-01-27 01:48
 **/
@Component
public class RequestInterceptor implements HandlerInterceptor {
    public static ThreadLocal<TraceLog> requestHolder = new ThreadLocal<>();
    public static String TRACE_ID = "TRACE_ID";

    /**
     * 每次收到请求时，记录该次请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        // MDC 机制 https://www.jianshu.com/p/1dea7479eb07
        // 每一系列请求添加一个 trace_id 方便之后追踪
        MDC.put(TRACE_ID, UUID.randomUUID().toString());
        TraceLog preTraceLog = new TraceLog()
                .setEnv(getEnv(request.getCookies()))
                .setSpendTime(System.currentTimeMillis() + "")
                .setUrl(request.getRequestURI())
                .setUserAgent(CommonUtil.getUserAgent(request));
        requestHolder.set(preTraceLog);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) {
        requestHolder.remove();
        MDC.clear();
    }

    /**
     * 由于只做了PC端网页，所以暂时只放PC
     *
     * @param cookies
     * @return
     */
    private String getEnv(Cookie[] cookies) {
        return "PC";
    }
}
