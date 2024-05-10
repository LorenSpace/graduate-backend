package fun.sast.word.util;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author: 風楪fy
 * @create: 2022-01-27 01:54
 **/
public class CommonUtil {
    /**
     * 获取UA
     *
     * @param request 请求
     * @return java.lang.String
     */
    public static String getUserAgent(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        // hutool 的工具类，用于解析 ua
        UserAgent ua = UserAgentUtil.parse(header);
        return "浏览器:" + ua.getBrowser() + " " +
                ua.getVersion() + "，os:" + ua.getOs() + " " +
                ua.getOsVersion() + "，是否移动设备:" + ua.isMobile();
    }

    /**
     * AOP 获取请求参数
     *
     * @param joinPoint  切点
     * @param excludeSet 排查参数set
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    public static Map<String, Object> getRequestParamMap(
            JoinPoint joinPoint, Set<String> excludeSet) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        IntStream.range(0, paramNames.length)
                .filter(i -> !Optional.ofNullable(excludeSet)
                        .map(set -> set.contains(paramNames[i]))
                        .orElse(false))
                .forEach(i -> param.put(paramNames[i], paramValues[i]));
        return param;
    }
}
