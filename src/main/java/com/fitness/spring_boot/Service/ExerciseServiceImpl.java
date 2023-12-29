package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.exercisedto.ExerciseDTO;
import com.fitness.spring_boot.repository.Exercise.ExerciseRepository;
import lombok.RequiredArgsConstructor;
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
        return exerciseRepository.save(exercise).getEno();
    }

    @Override
    public List<ExerciseDTO> getList(Pageable pageable) {
        return null;
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
    public Exercise getBoard(Long eno) {
        Exercise result = exerciseRepository.findById(eno).orElseThrow();
        return result;
    }
}
