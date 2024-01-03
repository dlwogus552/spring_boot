package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.domain.qna.QNABoard;
import com.fitness.spring_boot.domain.qna.QNABoardFile;
import com.fitness.spring_boot.dto.qna.QNABoardFileDTO;
import com.fitness.spring_boot.repository.qna.QNABoardFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class QNABoardFileServiceImpl implements QNABoardFileService{
    private final QNABoardFileRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Long FileUpload(QNABoardFileDTO qnaBoardFileDTO) {
        QNABoard board = QNABoard.builder().qnabno(qnaBoardFileDTO.getQnaBoard().getQnabno()).build();
        qnaBoardFileDTO.setQnaBoard(board);
        QNABoardFile qnaBoardFile = modelMapper.map(qnaBoardFileDTO, QNABoardFile.class);
        Long qnafno = repository.save(qnaBoardFile).getQnafno();
        return qnafno;
    }

    @Override
    public QNABoardFileDTO getFile(Long qnabno) {
        return null;
    }

    @Override
    public List<QNABoardFileDTO> getFileList(Long qnaBno) {
        return null;
    }

    @Override
    public void FileDelete(Long qnafno) {

    }
}
