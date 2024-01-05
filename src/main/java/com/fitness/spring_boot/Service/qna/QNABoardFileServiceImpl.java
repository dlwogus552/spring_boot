package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.domain.qna.QNABoard;
import com.fitness.spring_boot.domain.qna.QNABoardFile;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.qna.QNABoardFileDTO;
import com.fitness.spring_boot.repository.qna.QNABoardFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class QNABoardFileServiceImpl implements QNABoardFileService{
    private final QNABoardFileRepository repository;
    private final ModelMapper modelMapper;

    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;

    @Override
    public void FileUpload(QNABoardDTO qnaBoardDTO) {
        // 파일이 있을 경우

            log.info("파일 확인 : " + qnaBoardDTO.getFiles());
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

                QNABoardFile qnaBoardFile = QNABoardFile.builder()
                        .filename(oFilename)
                        .uuid(uuid)
                        .qnaBoard(modelMapper.map(qnaBoardDTO, QNABoard.class))
                        .build();
                repository.save(qnaBoardFile);
            });

    }

    @Override
    public QNABoardFileDTO getFile(Long qnabno) {
        return null;
    }

    @Override
    public List<QNABoardFileDTO> getFileList(Long qnaBno) {
        List<QNABoardFile> result = repository.findByFileList(qnaBno);
        List<QNABoardFileDTO> dto = result.stream()
                .map(fileDTO -> modelMapper.map(fileDTO, QNABoardFileDTO.class))
                .toList();
        return dto;
    }

    @Override
    public void FileDelete(Long qnafno) {
        repository.deleteById(qnafno);
    }

    @Override
    public void FileDeleteAll(Long qnaBno) {
        List<QNABoardFileDTO> dtoList = getFileList(qnaBno);
        List<Long> fnoList = new ArrayList<>();
        dtoList.forEach(dto -> {
            fnoList.add(dto.getQnafno());
            File file = new File(uploadPath + "\\qnafile", dto.getUuid()+"_"+dto.getFilename());
            file.delete();
        });
        repository.deleteAllById(fnoList);
    }
}
