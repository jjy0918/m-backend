package com.midas.epkorea.domain.construction;

import com.midas.epkorea.domain.category.Category;
import com.midas.epkorea.domain.constructionbanner.ConstructionBanner;
import com.midas.epkorea.domain.constructiondetailimage.ConstructionDetailImage;
import com.midas.epkorea.domain.constructiontable.ConstructionTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "construction")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class ConstructionDetail {
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

    @OneToMany
    @JoinColumn(name = "constructionNo",insertable=false, updatable=false)
    private List<ConstructionBanner> banners;

    @OneToMany
    @JoinColumn(name = "constructionNo",insertable=false, updatable=false)
    private List<ConstructionDetailImage> detailimage;

    @OneToMany
    @JoinColumn(name = "constructionNo",insertable=false, updatable=false)
    private List<ConstructionTable> tableList;

}
