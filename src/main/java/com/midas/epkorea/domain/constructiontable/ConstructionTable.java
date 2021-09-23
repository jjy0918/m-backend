package com.midas.epkorea.domain.constructiontable;

import com.midas.epkorea.dto.request.TableListRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConstructionTable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;

    private int constructionNo;

    private String title;

    private String text;

    public void createConstructionTableByRequest(TableListRequestDto requestDto, int no) {

        this.title=requestDto.getTitle();
        this.text = requestDto.getText();

        this.constructionNo=no;

    }

}
