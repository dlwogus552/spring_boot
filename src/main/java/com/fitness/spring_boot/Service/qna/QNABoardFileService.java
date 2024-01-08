package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.qna.QNABoardFileDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QNABoardFileService {
    void FileUpload(QNABoardDTO qnaBoardDTO);

    QNABoardFileDTO getFile(Long qnafno);

    List<QNABoardFileDTO> getFileList(Long qnaBno);

    void FileDelete(Long qnafno);
    void FileDeleteAll(Long qnafno);
}
