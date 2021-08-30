package com.midas.epkorea.domain.productmanagementtable;

import com.midas.epkorea.dto.TableListRequestDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString
public class ProductManagementTable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;

    private int productManagementNo;

    private String title;

    private String text;

    public void createProductManagementTableByRequest(TableListRequestDto requestDto, int pmNo) {

        this.title=requestDto.getTitle();
        this.text = requestDto.getText();

        this.productManagementNo=pmNo;

    }

}
