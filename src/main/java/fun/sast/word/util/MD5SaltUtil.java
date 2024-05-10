package fun.sast.word.util;

import org.springframework.util.DigestUtils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-05-07 11:41
 **/
public class MD5SaltUtil {

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * 使用 MD5 和盐值加密密码
     *
     * @param password 待加密的密码
     * @return 加密后的字符串
     */
    public static String md5WithSalt(String password) {
        String salt = generateSalt();
        // 将盐值与密码组合
        String base = salt + password;
        // 使用 Spring 的 DigestUtils 生成 MD5 哈希
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
