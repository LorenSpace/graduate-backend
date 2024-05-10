package fun.sast.word.service;


import fun.sast.word.entity.Sentence;
import fun.sast.word.pojo.vo.SentenceVO;

import java.util.List;

/**
 * 存储输入的句子，检测是否存在委婉语(Sentence)表服务接口
 *
 * @author cxy621
 * @since 2024-04-27 23:54:52
 */
public interface SentenceService {
    Long addSentence(Sentence sentence, Long user_id);

    List<SentenceVO> getAllSentencesByUserId(Long user_id);

    SentenceVO getSentenceById(Long sen_id);

    List<SentenceVO> getAllSentences();
}

