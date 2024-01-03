package com.fitness.spring_boot.dto;

import com.fitness.spring_boot.Service.ExerciseFileService;
import com.fitness.spring_boot.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseFileDTO {
    private Long efno;
    private String uuid;
    private String filename;
    private boolean image;
    private boolean thumbnail;
    private Exercise exercise;

    public String getLink(Long eno){
        if(thumbnail && this.exercise.getEno()==eno){
            return "s_"+uuid+"_"+filename;
        }else{
            return "";
        }
    }
    public String getSaveFileName(String filename, String uuid){
            return uuid+"_"+filename;
    }

}
