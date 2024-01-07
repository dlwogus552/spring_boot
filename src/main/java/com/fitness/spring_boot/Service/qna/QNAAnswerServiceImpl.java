package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.domain.qna.QNAAnswer;
import com.fitness.spring_boot.dto.qna.QNAAnswerDTO;
import com.fitness.spring_boot.repository.qna.QNAAnswerRepository;
import com.fitness.spring_boot.repository.qna.QNABoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class QNAAnswerServiceImpl implements QNAAnswerService {
    private final QNAAnswerRepository repository;
    private final QNABoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public QNAAnswerDTO getAnswer(Long qnaBno) {
        log.info("여기 들어옴?");
        QNAAnswer result = repository.findByQnabno(qnaBno);
        log.info("service 부분 result : " + result);
        if (result != null) {
            QNAAnswerDTO dto = modelMapper.map(result, QNAAnswerDTO.class);
            log.info("service 부분 : " + dto);
            return dto;
        }
        return null;
    }

    @Override
    public Long register(QNAAnswerDTO qnaAnswerDTO) {
        log.info("QNAAnswer 확인 : " + qnaAnswerDTO);
        QNAAnswer answer = modelMapper.map(qnaAnswerDTO, QNAAnswer.class);
        answer.getQnaBoard().setAnswer(true); // 답변여부 true로 변환
        boardRepository.save(answer.getQnaBoard());
        Long result = repository.save(answer).getQnaano();
        return result;
    }

    @Override
    public void remove(Long qnaano) {
        repository.deleteById(qnaano);
    }
}
