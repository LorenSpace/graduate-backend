package fun.sast.word.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.sast.word.entity.Sentence;
import fun.sast.word.pojo.vo.SentenceVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 存储输入的句子，检测是否存在委婉语(Sentence)表数据库访问层
 *
 * @author cxy621
 * @since 2024-04-27 23:54:52
 */
@Mapper
public interface SentenceMapper extends BaseMapper<Sentence> {
    List<SentenceVO> getSentenceByUId(Long user_id);

    List<SentenceVO> getAllSentences();
}

