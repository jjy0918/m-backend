package com.midas.epkorea.domain.category;

import com.midas.epkorea.domain.productmanagetment.ProductManagement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    private int no;

    private String parent;

    private String child;

}
