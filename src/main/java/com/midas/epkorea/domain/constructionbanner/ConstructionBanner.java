package com.midas.epkorea.domain.constructionbanner;

import com.midas.epkorea.dto.request.BannerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class ConstructionBanner {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;

    private int constructionNo;

    private String image;

    private String name;

    public void createConstructionBannerByRequest(BannerRequestDto requestDto, int no) {

        this.image=requestDto.getImage();
        this.name = requestDto.getName();

        this.constructionNo=no;

    }

}
