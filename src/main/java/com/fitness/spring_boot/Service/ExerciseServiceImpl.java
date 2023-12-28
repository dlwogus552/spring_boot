package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.exercisedto.ExerciseDTO;
import com.fitness.spring_boot.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long register(ExerciseDTO exerciseDTO) {
        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
        return exerciseRepository.save(exercise).getId();
    }

    @Override
    public List<ExerciseDTO> getList(Pageable pageable) {
        return null;
    }

    @Override
    public Long modify(ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseRepository.findById(exerciseDTO.getId()).orElseThrow();
        exercise.change(exerciseDTO.getTitle(), exerciseDTO.getContent(),exerciseDTO.getThumbnail(), exerciseDTO.getVideo(), exerciseDTO.getPart());
        return exerciseRepository.save(exercise).getId();
    }

    @Override
    public void remove(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseDTO getBoard(Long id) {
        Exercise result = exerciseRepository.findById(id).orElseThrow();
        ExerciseDTO exerciseDTO = modelMapper.map(result,ExerciseDTO.class);
        return exerciseDTO;
    }
}
