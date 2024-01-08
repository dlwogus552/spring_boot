package com.fitness.spring_boot.Service.review;

import com.fitness.spring_boot.domain.review.Review;
import com.fitness.spring_boot.domain.review.ReviewFile;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import com.fitness.spring_boot.dto.review.ReviewFileDTO;
import com.fitness.spring_boot.repository.review.ReviewFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewFileServiceImpl implements ReviewFileService{
    private final ReviewFileRepository reviewFileRepository;
    private final ModelMapper modelMapper;
    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;

    @Override
    @Transactional(readOnly = true)
    public List<ReviewFileDTO> getList(Long rno) {
        List<ReviewFile> result = reviewFileRepository.findByReview_RnoOrderByRfno(rno);
        List<ReviewFileDTO> list = result.stream().map(files -> modelMapper.map(files, ReviewFileDTO.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void upload(ReviewDTO reviewDTO) {
        File folder = new File(uploadPath + "\\review");
        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //파일 업로드
        for (MultipartFile file : reviewDTO.getFiles()) {
            String originalFileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            Path savePath = Paths.get(folder.getPath(), uuid + "_" + originalFileName);
            try {
                file.transferTo(savePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ReviewFile reviewFile = ReviewFile.builder()
                    .uuid(uuid)
                    .filename(originalFileName)
                    .review(modelMapper.map(reviewDTO, Review.class))
                    .build();
            reviewFileRepository.save(reviewFile);
        }//for end

    }

    @Override
    public void deleteAll(Long rno) {
        List<ReviewFileDTO> fileDTOList = getList(rno);
        //파일 삭제
        for (ReviewFileDTO dto : fileDTOList) {
            File file = new File(uploadPath + "\\review", dto.getLink());
            file.delete();
        }
        reviewFileRepository.deleteReviewFileByReview_Rno(rno);
    }
}
