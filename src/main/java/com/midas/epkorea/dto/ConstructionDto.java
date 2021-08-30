package com.midas.epkorea.dto;

import com.midas.epkorea.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstructionDto {

    private int no;

    private String category;

    private String name;

    private String registrationDate;

    private boolean expose;



}
