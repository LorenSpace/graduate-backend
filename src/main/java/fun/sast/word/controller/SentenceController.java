package fun.sast.word.controller;


import fun.sast.word.entity.Sentence;
import fun.sast.word.pojo.vo.SentenceVO;
import fun.sast.word.service.SentenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 存储输入的句子，检测是否存在委婉语(Sentence)表控制层
 *
 * @author cxy621
 * @since 2024-04-27 23:54:51
 */
@RestController
@Slf4j
@RequestMapping("/sentence")
public class SentenceController {

    private final SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @PostMapping("/add")
    public Long addSentence(@RequestBody Sentence sentence,
                            @RequestParam(value = "user_id", defaultValue = "-1") Long id) {
        return sentenceService.addSentence(sentence, id);
    }

    @GetMapping("/result/{sen_id}")
    public SentenceVO getSentenceById(@PathVariable("sen_id") Long sen_id) {
        return sentenceService.getSentenceById(sen_id);
    }

//    @GetMapping("/result/{user_id}")
//    public List<SentenceVO> getSentencesByUserId(@PathVariable("user_id") Long user_id) {
//        return sentenceService.getAllSentencesByUserId(user_id);
//    }

    @GetMapping("/result/all")
    public List<SentenceVO> getAllSentences() {
        return sentenceService.getAllSentences();
    }
}

