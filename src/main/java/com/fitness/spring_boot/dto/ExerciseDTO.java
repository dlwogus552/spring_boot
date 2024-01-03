package com.fitness.spring_boot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private Long eno;
    private String title;
    private String content;
    private String part;
    private LocalDateTime regDate;
    private int visitcount;
    @NotEmpty
    private List<MultipartFile> files;


    public String changePart(String type) {
        String result = "";
        switch (type) {
            case "b":
                result = "등";
                break;
            case "c":
                result = "가슴";
                break;
            case "s":
                result = "어깨";
                break;
            case "a":
                result = "팔";
                break;
            case "f":
                result = "대퇴사두";
                break;
            case "h":
                result = "햄스트링 / 둔근";
                break;
        }
        return result;
    }
    public String localDateFormat (){
            String date= regDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return date;
    }

}
