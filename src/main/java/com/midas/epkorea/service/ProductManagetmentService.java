package com.midas.epkorea.service;

import com.midas.epkorea.domain.productmanagementtable.ProductManagementTable;
import com.midas.epkorea.domain.productmanagementtable.ProductManagementTableRepository;
import com.midas.epkorea.domain.productmanagetment.ProductManagement;
import com.midas.epkorea.domain.productmanagetment.ProductManagementRepository;
import com.midas.epkorea.dto.PageDto;
import com.midas.epkorea.dto.ProductManagementRequestDto;
import com.midas.epkorea.dto.ProductManagementResponseDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.ProductManagementNotPresentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManagetmentService {

    private final ProductManagementRepository productManagementRepository;

    private final ProductManagementTableRepository productManagementTableRepository;

    @PersistenceContext
    private EntityManager entityManager;


    // 번호로 관리자 받아오기
    private ProductManagement getProductManagement(int no) throws ProductManagementNotPresentException {
        Optional<ProductManagement> productManagement = productManagementRepository.findById(no);

        return productManagement.orElseThrow(ProductManagementNotPresentException::new);

    }

    public ResponseEntity<ProductManagementResponseDto> getProductManagementList(int page) {

        Pageable pageRequest = PageDto.getPageRequest(page);

        ProductManagementResponseDto productManagementResponseDto = new ProductManagementResponseDto(productManagementRepository.findAll(pageRequest));
//        ProductManagementResponseDto productManagementResponseDto = new ProductManagementResponseDto(productManagementRepository.findAllByCategoryIn(pageRequest,test));
        productManagementResponseDto.setMessage("find all productManagement");

        return new ResponseEntity<>(productManagementResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getProductManagementByNo(int no) throws ProductManagementNotPresentException {
        ProductManagement getProductManagement = getProductManagement(no);

        ResponseDto responseDto = ResponseDto.builder()
                .message("find productManagement by no")
                .data(getProductManagement)
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }

    public ResponseEntity<ProductManagementResponseDto> searchProductManagement(int page, String word) {
        Pageable pageRequest = PageDto.getPageRequest(page);

        ProductManagementResponseDto productManagementResponseDto = new ProductManagementResponseDto(productManagementRepository.findAllByNameContains(pageRequest,word));
        productManagementResponseDto.setMessage("search productManagement by name");

        return new ResponseEntity<>(productManagementResponseDto, HttpStatus.OK);

    }

    public ResponseEntity<ResponseDto> createProductManagement(ProductManagementRequestDto requestDto) {
        ProductManagement productManagement = ProductManagement.builder().category(1).build();
        productManagement.createProductManagementByRequest(requestDto);
        productManagementRepository.save(productManagement);

        int no = productManagement.getNo();

        if(requestDto.getTableList()!=null){
            final int[] cnt={0};
            requestDto.getTableList().forEach(pmtr ->{
                if(pmtr.checkTableListItem() && cnt[0] < 10){
                    ProductManagementTable productManagementTable = (ProductManagementTable.builder().build());
                    productManagementTable.createProductManagementTableByRequest(pmtr, no);
                    productManagementTableRepository.save(productManagementTable);
                    cnt[0]++;
                }


            });
        }

        ResponseDto responseDto = ResponseDto.builder()
                .message("ProductManagement create")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED );
    }


    public ResponseEntity<ResponseDto> editProductManagemnet(ProductManagementRequestDto requestDto,int no) throws ProductManagementNotPresentException {


        Optional<ProductManagement> productManagement = productManagementRepository.findById(no);

        ProductManagement newProductManagement = productManagement.orElseThrow(ProductManagementNotPresentException::new);
        newProductManagement.createProductManagementByRequest(requestDto);

        productManagementRepository.save(newProductManagement);

        // 기존 table 삭제
        for(ProductManagementTable pt : newProductManagement.getProductManagementTableList()){
            productManagementTableRepository.delete(pt);
        }


        if(requestDto.getTableList()!=null){
            final int[] cnt={0};
            requestDto.getTableList().forEach(pmtr ->{
                if(pmtr.checkTableListItem() && cnt[0] < 10){
                    ProductManagementTable productManagementTable = (ProductManagementTable.builder().build());
                    productManagementTable.createProductManagementTableByRequest(pmtr, no);
                    productManagementTableRepository.save(productManagementTable);
                    cnt[0]++;
                }


            });
        }

        ResponseDto responseDto = ResponseDto.builder()
                .message("ProductManagement edit")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK );




    }
}
