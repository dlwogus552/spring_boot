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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Page<QNABoard> result = repository.findByKeyword(type, keyword, pageable);
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
        // 파일이 있을 경우
        if(qnaBoardDTO.getFiles() != null && !qnaBoardDTO.getFiles().get(0).isEmpty()) {
            log.info("파일 확인 : " + qnaBoardDTO.getFiles());
            final List<QNABoardFileDTO> list = new ArrayList<>();
            File folder = new File(uploadPath+"\\qnafile");
            if (!folder.exists()) {
                try {
                    folder.mkdirs();
                    log.info("qnafile 폴더가 생성됨");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.info("qnafile 폴더가 이미 있음");
            }
            qnaBoardDTO.getFiles().forEach(multipartFile -> {
                String oFilename = multipartFile.getOriginalFilename();
                log.info("oFilename : " + oFilename);
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(folder.getPath(), uuid + "_" + oFilename);
                try {
                    multipartFile.transferTo(savePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                QNABoardFileDTO fileDTO = QNABoardFileDTO.builder()
                        .qnafno(qnabno)
                        .filename(oFilename)
                        .qnaBoard(board)
                        .uuid(uuid)
                        .build();
                fileService.FileUpload(fileDTO);
            });
        }
        return qnabno;
    }

    @Override
    public Long modify(QNABoardDTO qnaBoardDTO) {
        return null;
    }

    @Override
    public void remove(Long bno) {

    }

    @Override
    public QNABoardDTO getBoard(Long bno) {
        return null;
    }
}
