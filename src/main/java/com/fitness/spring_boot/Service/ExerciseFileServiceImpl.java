package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.ExerciseFile;
import com.fitness.spring_boot.exercisedto.ExerciseFileDTO;
import com.fitness.spring_boot.repository.Exercise.ExerciseFileUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ExerciseFileServiceImpl implements ExerciseFileService{
    private final ExerciseFileUploadRepository exerciseFileUploadRepository;

    @Override
    public List<ExerciseFileDTO> getList(Long bno) {
        return null;
    }

    @Override
    public ExerciseFileDTO getFile(Long fno) {
        return null;
    }

    @Override
    public void delete(Long fno) {

    }

    @Override
    public Long upload(ExerciseFile file) {
        return exerciseFileUploadRepository.save(file).getEfno();
    }

    @Override
    public void deleteAll(Long bno) {

    }
}
