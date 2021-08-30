package com.midas.epkorea.domain.construction;

import com.midas.epkorea.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
