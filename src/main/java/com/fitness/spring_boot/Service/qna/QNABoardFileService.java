package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.qna.QNABoardFileDTO;

import java.util.List;

public interface QNABoardFileService {
    void FileUpload(QNABoardDTO qnaBoardDTO);

    QNABoardFileDTO getFile(Long qnabno);

    List<QNABoardFileDTO> getFileList(Long qnaBno);

    void FileDelete(Long qnafno);
    void FileDeleteAll(Long qnafno);
}
