package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.dto.qna.QNAAnswerDTO;

public interface QNAAnswerService {
    QNAAnswerDTO getAnswer(Long qnaBno);
    Long register(QNAAnswerDTO qnaAnswerDTO);
    void remove(Long qnaano);
}
