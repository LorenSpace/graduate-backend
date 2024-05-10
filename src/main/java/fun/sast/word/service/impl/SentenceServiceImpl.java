package fun.sast.word.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.sast.word.common.enums.ErrorEnum;
import fun.sast.word.entity.Sentence;
import fun.sast.word.entity.User;
import fun.sast.word.exception.LocalRunTimeException;
import fun.sast.word.mapper.SentenceMapper;
import fun.sast.word.mapper.UserMapper;
import fun.sast.word.pojo.vo.SentenceVO;
import fun.sast.word.service.SentenceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 存储输入的句子，检测是否存在委婉语(Sentence)表服务实现类
 *
 * @author cxy621
 * @since 2024-04-27 23:54:52
 */
@Service("sentenceService")
public class SentenceServiceImpl implements SentenceService {
    private final UserMapper userMapper;

    private final SentenceMapper sentenceMapper;

    public SentenceServiceImpl(SentenceMapper sentenceMapper, UserMapper userMapper) {
        this.sentenceMapper = sentenceMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Long addSentence(Sentence sentence, Long user_id) {
        if (user_id != -1 && getUserById(user_id) == null) {
            throw new LocalRunTimeException(ErrorEnum.USER_NOT_EXISTS);
        }
        sentence.setUId(user_id);
        sentence.setIsEuphemism(2);
        sentence.setCreatedTime(LocalDateTime.now());
        sentenceMapper.insert(sentence);
        return sentence.getId();
    }

    @Override
    public List<SentenceVO> getAllSentencesByUserId(Long user_id) {
        if (user_id != -1 && getUserById(user_id) == null) {
            throw new LocalRunTimeException(ErrorEnum.USER_NOT_EXISTS);
        }
        return sentenceMapper.getSentenceByUId(user_id);
    }

    @Override
    public SentenceVO getSentenceById(Long sen_id) {
        Sentence sentence = sentenceMapper.selectById(sen_id);
        if (sentence == null) {
            throw new LocalRunTimeException(ErrorEnum.SENTENCE_NOT_EXISTS);
        }
        return new SentenceVO(sentence.getContent(), sentence.getIsEuphemism(), sentence.getCreatedTime());
    }

    @Override
    public List<SentenceVO> getAllSentences() {
        return sentenceMapper.getAllSentences();
    }


    private User getUserById(Long id) {
        return userMapper.selectById(id);
    }
}

