package fun.sast.word;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "fun.sast.word.mapper")
public class WordApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordApplication.class, args);
    }

}
