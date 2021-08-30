package com.midas.epkorea.domain.constructiondetailimage;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@ToString
@Getter
public class ConstructionDetailImage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;

    private int constructionNo;

    private String image;

}
