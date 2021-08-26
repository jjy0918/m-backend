package com.midas.epkorea.util;

import com.midas.epkorea.exception.PageException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
public class PageDto {

    // 현재 페이지 번호
    private int pageNumber;

    // 전체 페이지 개수
    private int totalPages;

    // 페이지 사이즈
    private int pageSize;

    // 현재 페이지의 원소 개수수
    private int numberOfElements;

   // 전체 페이지 원소 개수수
  private long totalElements;

  public PageDto(Page page){

      this.pageNumber=page.getNumber()+1;
      this.totalPages = page.getTotalPages();
      this.pageSize = page.getSize();
      this.numberOfElements = page.getNumberOfElements();
      this.totalElements = page.getTotalElements();

      if(this.totalPages !=0 && this.pageNumber > this.totalPages) {
          throw new PageException();
      }


  }


}
