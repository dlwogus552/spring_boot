package com.fitness.spring_boot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ExercisePageResponseDTO<E> {
    private int page;
    private int size;

    private int total;

    private int start;
    private int end;

    private boolean next;
    private boolean prev;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public ExercisePageResponseDTO(ExercisePageRequestDTO exercisePageRequestDTO, List<E> dtoList, int total){
        if (total <= 0) {
            return;
        }
        this.page=exercisePageRequestDTO.getPage();
        this.size=exercisePageRequestDTO.getSize();

        this.total=total;
        this.dtoList=dtoList;

        this.end = (int)(Math.ceil(this.page/5.0)*5);
        this.start = this.end -4;

        int last = (int)(Math.ceil((total/(double)size)));
        this.end=end>last ? last : end;

        this.prev = this.start>1;
        this.next = total>this.end*this.size;
    }
}
