package com.fitness.spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page=1;
    @Builder.Default
    private int size=12;
    private String type; //종류 : b,c,s,a,f,h
    private String keyword;
    private String link;

    public Pageable getPageable(String...props){
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    public String getLink(){
        if(link==null){
            StringBuilder builder=new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);

            if(type !=null){
                builder.append("&type="+type);
            }

            if(keyword != null){
                try {
                    builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
                }catch (UnsupportedEncodingException e){e.printStackTrace();}


            }
            link = builder.toString();
        }
        return link;
    }

}
