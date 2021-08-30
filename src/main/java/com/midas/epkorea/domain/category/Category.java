package com.midas.epkorea.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
