package com.midas.epkorea.domain.productmanagetment;

import com.midas.epkorea.domain.category.Category;
import com.midas.epkorea.util.ProductManagementRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class ProductManagement {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public void createProductManagementByRequest(ProductManagementRequestDto requestDto) {

        this.name = requestDto.getName();

        this.category = requestDto.getCategory();

        this.catalog = requestDto.getCatalog();

        this.image = requestDto.getImage();

        this.expose = requestDto.isExpose();

        this.editor = requestDto.getEditor();

    }
}
