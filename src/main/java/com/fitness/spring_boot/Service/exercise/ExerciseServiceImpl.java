package com.fitness.spring_boot.Service.exercise;

import com.fitness.spring_boot.domain.exercise.Exercise;
import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.repository.exercise.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    private final ExerciseFileService exerciseFileService;
    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;

    @Transactional
    @Override
    public void register(ExerciseDTO exerciseDTO) {
        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
        Long eno = exerciseRepository.save(exercise).getEno();
        exerciseDTO.setEno(eno);
        exerciseFileService.upload(exerciseDTO);
    }


    @Override
    public PageResponseDTO<ExerciseDTO> getList(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("eno");
        Page<Exercise> result = exerciseRepository.searchAll(type, keyword, pageable);

        List<ExerciseDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board, ExerciseDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<ExerciseDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total((int) result.getTotalElements())
                .build();
    }


    @Override
    public Long modify(ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseRepository.findById(exerciseDTO.getEno()).orElseThrow();
        exercise.change(exerciseDTO.getTitle(), exerciseDTO.getContent(), exerciseDTO.getPart());
        return exerciseRepository.save(exercise).getEno();
    }

    @Override
    public void remove(Long eno) { // 이런거 이렇게 해버리면 삭제 되었는지 알수있나 기억은 안나는데 existById 로 존재하는지 체크하고 삭제를 하던가 하는게 좋음 예외처리가 진짜 생명임.
        exerciseRepository.deleteById(eno);
    }

    @Override
    public ExerciseDTO getBoard(Long eno) {
        Exercise result = exerciseRepository.findById(eno).orElseThrow();
        result.updateVisitcount();
        exerciseRepository.save(result);
        ExerciseDTO exerciseDTO = modelMapper.map(result, ExerciseDTO.class);
        return exerciseDTO;
    }

}
