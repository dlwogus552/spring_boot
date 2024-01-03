package com.fitness.spring_boot.repository.qna;

import com.fitness.spring_boot.domain.qna.QNABoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QNABoardFileRepository extends JpaRepository<QNABoardFile, Long> {
    @Query("select f from QNABoardFile f where f.qnaBoard.qnabno =:qnabno")
    List<QNABoardFile> findByFileList(Long qnabno);
}
