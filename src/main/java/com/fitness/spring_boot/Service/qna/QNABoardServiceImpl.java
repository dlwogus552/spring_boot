package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.domain.qna.QNABoard;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.qna.QNABoardFileDTO;
import com.fitness.spring_boot.repository.qna.QNABoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class QNABoardServiceImpl implements QNABoardService {
    private final QNABoardFileService fileService;
    private final QNABoardRepository repository;
    private final ModelMapper modelMapper;

    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;

    @Override
    public PageResponseDTO<QNABoardDTO> getList(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("qnabno");
        Page<QNABoard> result = repository.searchAll(type, keyword, pageable);
        List<QNABoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, QNABoardDTO.class))
                .toList();
        return PageResponseDTO.<QNABoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public Long register(QNABoardDTO qnaBoardDTO) {
        QNABoard board = modelMapper.map(qnaBoardDTO, QNABoard.class);
        Long qnabno = repository.save(board).getQnabno();
        qnaBoardDTO.setQnabno(qnabno);
        if(qnaBoardDTO.getFiles() != null && !qnaBoardDTO.getFiles().get(0).isEmpty()) {
            fileService.FileUpload(qnaBoardDTO);
        }
        return qnabno;
    }

    @Override
    public Long modify(QNABoardDTO qnaBoardDTO) {
        QNABoard board = repository.findById(qnaBoardDTO.getQnabno()).orElseThrow();
        board.change(qnaBoardDTO.getTitle(), qnaBoardDTO.getContent());
        return repository.save(board).getQnabno();
    }

    @Override
    @Transactional
    public void remove(Long bno) {
        List<QNABoardFileDTO> files = fileService.getFileList(bno);
        if (!files.isEmpty() && files != null) {
            fileService.FileDeleteAll(bno);
        }
        repository.deleteById(bno);
    }

    @Override
    public QNABoardDTO getBoard(Long bno) {
        QNABoard result = repository.findById(bno).get();
        result.updateReadcnt();
        repository.save(result);
        QNABoardDTO dto = modelMapper.map(result, QNABoardDTO.class);
        return dto;
    }
}
