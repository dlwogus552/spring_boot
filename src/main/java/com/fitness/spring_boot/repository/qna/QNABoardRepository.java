package com.fitness.spring_boot.repository.qna;

import com.fitness.spring_boot.domain.qna.QNABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QNABoardRepository extends JpaRepository<QNABoard, Long> {

    // 제목으로 찾기
    @Query("select b from QNABoard b where :type like concat('%', :keyword,'%') " +
            "order by b.qnabno desc ")
    Page<QNABoard> findByKeyword(String type, String keyword, Pageable pageable);

//    // 작성자로 찾기
//    @Query("select b from QNABoard b where b.writer like concat('%', :keyword,'%') " +
//            "order by b.qnabno desc ")
//    Page<QNABoard> findByWriterKeyword(String keyword, Pageable pageable);
//
//    // 내용으로 찾기
//    @Query("select b from QNABoard b where b.content like concat('%', :keyword,'%') " +
//            "order by b.qnabno desc ")
//    Page<QNABoard> findByContentKeyword(String keyword, Pageable pageable);
}
