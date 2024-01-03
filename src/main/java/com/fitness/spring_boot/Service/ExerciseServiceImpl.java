package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.dto.ExerciseDTO;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long register(ExerciseDTO exerciseDTO) {
        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
        return exerciseRepository.save(exercise).getEno();
    }

    @Override
    public PageResponseDTO<ExerciseDTO> getList(PageRequestDTO pageRequestDTO) {
        String type= pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("eno");
        Page<Exercise> result=exerciseRepository.searchAll(type,keyword,pageable);
//        int count = replyRepository.listOfBoard(bno)

        List<ExerciseDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board,ExerciseDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<ExerciseDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total((int)result.getTotalElements())
                .build();
    }


    @Override
    public Long modify(ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseRepository.findById(exerciseDTO.getEno()).orElseThrow();
        exercise.change(exerciseDTO.getTitle(), exerciseDTO.getContent(), exerciseDTO.getPart());
        return exerciseRepository.save(exercise).getEno();
    }

    @Override
    public void remove(Long eno) {
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
