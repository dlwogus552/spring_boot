package com.fitness.spring_boot.dto;

import com.fitness.spring_boot.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseFileDTO {
    private Long fno;
    private String uuid;
    private String filename;
    private boolean image;
    private Exercise exercise;
    private List<MultipartFile> files;

    public String getLink(){
        if(image){
            return "s_"+uuid+"_"+filename;
        }else{
            return uuid+"_"+filename;
        }
    }
}
