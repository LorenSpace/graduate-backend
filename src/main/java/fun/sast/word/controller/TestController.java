package fun.sast.word.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: word
 * @author: cxy621
 * @create: 2024-04-28 00:54
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
