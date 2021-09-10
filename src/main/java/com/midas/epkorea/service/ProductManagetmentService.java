package com.midas.epkorea.service;

import com.midas.epkorea.domain.category.Category;
import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.productmanagementtable.ProductManagementTable;
import com.midas.epkorea.domain.productmanagementtable.ProductManagementTableRepository;
import com.midas.epkorea.domain.productmanagetment.ProductManagement;
import com.midas.epkorea.domain.productmanagetment.ProductManagementRepository;
import com.midas.epkorea.dto.PageDto;
import com.midas.epkorea.dto.ProductManagementRequestDto;
import com.midas.epkorea.dto.ProductManagementResponseDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.ProductManagementNotPresentException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.midas.epkorea.domain.productmanagetment.QProductManagement.productManagement;

@Service
@RequiredArgsConstructor
public class ProductManagetmentService {

    private final ProductManagementRepository productManagementRepository;

    private final ProductManagementTableRepository productManagementTableRepository;

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    // 현재 세션에 존재하는 계정 가져오기
    private Manager getManager(){
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();

        return (Manager)authentication.getPrincipal();
    }
    
    // 번호로 관리자 받아오기
    private ProductManagement getProductManagement(int no) throws ProductManagementNotPresentException {
        Optional<ProductManagement> productManagement = productManagementRepository.findById(no);

        return productManagement.orElseThrow(ProductManagementNotPresentException::new);

    }

    // 카테고리에 맞는 BooleanBuilder 생성
    private BooleanBuilder getWhereBuilderByManager(){
        Manager nowManager = getManager();

        List<Integer> categoryNumber = Category.getPMCategoryNumbers(nowManager);

        BooleanBuilder builder = new BooleanBuilder();

        categoryNumber.forEach(nowNum-> builder.or(productManagement.category.eq(nowNum)));

        if(!builder.hasValue()){
            builder.and(productManagement.category.eq(-1));
        }

        return builder;
    }

    private ProductManagementResponseDto getPMList(int page,BooleanBuilder builder){

        Pageable pageRequest = PageDto.getPageRequest(page);

        List<ProductManagement> content = queryFactory
                .selectFrom(productManagement)
                .where(
                        builder
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(productManagement.no.desc())
                .fetch();
        ProductManagementResponseDto productManagementResponseDto;
        if(!content.isEmpty()){
            JPAQuery<ProductManagement> countQuery = queryFactory
                    .selectFrom(productManagement)
                    .where(
                            builder
                    );

            Page<ProductManagement> pp = PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);

            productManagementResponseDto = new ProductManagementResponseDto(pp);
        }
        else{
            productManagementResponseDto = new ProductManagementResponseDto();
        }

        return productManagementResponseDto;
    }

    public ResponseEntity<ProductManagementResponseDto> getProductManagementList(int page) {

        BooleanBuilder builder = getWhereBuilderByManager();

        ProductManagementResponseDto productManagementResponseDto = getPMList(page,builder);

        productManagementResponseDto.setMessage("find all productManagement");

        return new ResponseEntity<>(productManagementResponseDto, HttpStatus.OK);
    }

    private boolean checkAuth(ProductManagement productManagement){
        Manager nowManager = getManager();

        List<Integer> categoryNumber = Category.getPMCategoryNumbers(nowManager);

        boolean checkAuth=false;

        for(int i=0;i<categoryNumber.size() && !checkAuth;i++){
            checkAuth = (categoryNumber.get(i) == productManagement.getCategory());
        }

        return checkAuth;
    }

    public ResponseEntity<ResponseDto> getProductManagementByNo(int no) throws ProductManagementNotPresentException {

        ProductManagement getProductManagement = getProductManagement(no);

        // 해당 접근 권한이 있는 경우
        if(checkAuth(getProductManagement)){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("find productManagement by no")
                    .data(getProductManagement)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }
        // 해당 접근 권한이 없는 경우
        else{
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail find productManagement by no")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<ProductManagementResponseDto> searchProductManagement(int page, String word) {
        BooleanBuilder builder = getWhereBuilderByManager();
        builder.and(productManagement.name.contains(word));

        ProductManagementResponseDto productManagementResponseDto = getPMList(page,builder);
        productManagementResponseDto.setMessage("search productManagement by name");

        return new ResponseEntity<>(productManagementResponseDto, HttpStatus.OK);

    }

    public ResponseEntity<ResponseDto> createProductManagement(ProductManagementRequestDto requestDto) {
        ProductManagement productManagement = ProductManagement.builder().category(1).build();
        productManagement.createProductManagementByRequest(requestDto);


        if(!checkAuth(productManagement)){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail create productManagement")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }



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

        // 수정하려는 데이터에 권한이 있는가.
        if(!checkAuth(newProductManagement)){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail edit productManagement")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }

        newProductManagement.createProductManagementByRequest(requestDto);

        // 수정한 데이터에 권한이 있는가
        if(!checkAuth(newProductManagement)){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail edit productManagement")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }

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

    public ResponseEntity<ResponseDto> deleteProductManager(int no) throws ProductManagementNotPresentException {

        Optional<ProductManagement> productManagement = productManagementRepository.findById(no);

        ProductManagement newProductManagement = productManagement.orElseThrow(ProductManagementNotPresentException::new);

        // 삭제하려는 데이터에 권한이 있는가.
        if(!checkAuth(newProductManagement)){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail delete productManagement")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }

        for(ProductManagementTable pt : newProductManagement.getProductManagementTableList()){
            productManagementTableRepository.delete(pt);
        }

        productManagementRepository.delete(newProductManagement);

        ResponseDto responseDto = ResponseDto.builder()
                .message("delete ProduceManagemnet : "+no)
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }
}
