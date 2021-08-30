package com.midas.epkorea.dto;

import com.midas.epkorea.exception.PageException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public static Pageable getPageRequest(int page){
        Pageable pageRequest=null;
        try{
            // 페이지 번호, 시작, 정렬
            pageRequest = PageRequest.of(page, 10, Sort.by("no").descending());

        }catch (IllegalArgumentException illException){
            // 요청하려는 페이지가 0이하인 작은 경우 예외 발생시킨다.
            throw new PageException();
        }
        return pageRequest;
    }




}
