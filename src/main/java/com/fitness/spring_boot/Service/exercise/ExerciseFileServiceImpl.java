package com.fitness.spring_boot.Service.exercise;

import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;
import com.fitness.spring_boot.repository.exercise.ExerciseFileUploadRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseFileServiceImpl implements ExerciseFileService{
    private final ExerciseFileUploadRepository exerciseFileUploadRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ExerciseFileDTO> getList() {
        List<ExerciseFile> result = exerciseFileUploadRepository.findAll();
        List<ExerciseFileDTO> list = result.stream().map(files -> modelMapper.map(files, ExerciseFileDTO.class)).collect(Collectors.toList());
        return list;
    }
    @Override
    public List<ExerciseFileDTO> getViewList(Long eno) {
        List<ExerciseFile> result = exerciseFileUploadRepository.findByExercise_EnoOrderByEfno(eno);
        List<ExerciseFileDTO> list = result.stream().map(files -> modelMapper.map(files, ExerciseFileDTO.class)).collect(Collectors.toList());
        return list;
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
    public void deleteAll(Long eno) {
        exerciseFileUploadRepository.deleteExerciseFileByExercise_Eno(eno);
    }


}
