package com.midas.epkorea.domain.construction;

import com.midas.epkorea.domain.category.Category;
import com.midas.epkorea.dto.request.ConstructionRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class Construction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;

    private String name;

    private int category;

    private Timestamp registrationDate;

    private boolean expose;

    private String thumbnail;

    @OneToOne
    @JoinColumn(name = "category",insertable=false, updatable=false)
    private Category categoryDetail;


    public void createConstructionByRequest(ConstructionRequestDto requestDto){

        this.name = requestDto.getName();

        this.category=requestDto.getCategory();

        this.expose=requestDto.isExpose();

        this.thumbnail=requestDto.getThumbnail();


    }

}
