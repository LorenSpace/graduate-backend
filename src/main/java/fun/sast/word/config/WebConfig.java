package fun.sast.word.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-05-08 15:05
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 只是简单的添加所有域仍然存在跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
