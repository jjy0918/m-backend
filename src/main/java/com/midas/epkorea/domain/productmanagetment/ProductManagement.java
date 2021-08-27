package com.midas.epkorea.domain.productmanagetment;

import com.midas.epkorea.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ProductManagement {

    @Id
    private int no;

    private String name;

    private int category;

    private String image;

    private String catalog;

    private Timestamp registrationDate;

    private boolean expose;

    private String editor;

    // 원투원 조인
    // name => 조인할 테이블과 연결할 현 테이블의 키 값.
    @OneToOne
    @JoinColumn(name = "category",insertable=false, updatable=false)
    private Category categoryDetail;

}
