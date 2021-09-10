package com.midas.epkorea.service;

import com.midas.epkorea.domain.category.Category;
import com.midas.epkorea.domain.construction.Construction;
import com.midas.epkorea.domain.construction.ConstructionDetail;
import com.midas.epkorea.domain.construction.ConstructionDetailRepository;
import com.midas.epkorea.domain.construction.ConstructionRepository;
import com.midas.epkorea.domain.constructionbanner.ConstructionBanner;
import com.midas.epkorea.domain.constructionbanner.ConstructionBannerRepository;
import com.midas.epkorea.domain.constructiondetailimage.ConstructionDetailImage;
import com.midas.epkorea.domain.constructiondetailimage.ConstructionDetailImageRepository;
import com.midas.epkorea.domain.constructiontable.ConstructionTable;
import com.midas.epkorea.domain.constructiontable.ConstructionTableRepository;
import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.dto.*;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.midas.epkorea.domain.construction.QConstruction.construction;

@Service
@RequiredArgsConstructor
public class ConstructionService {

    private final ConstructionRepository constructionRepository;

    private final ConstructionDetailRepository constructionDetailRepository;

    private final ConstructionTableRepository constructionTableRepository;

    private final ConstructionBannerRepository constructionBannerRepository;

    private final ConstructionDetailImageRepository constructionDetailImageRepository;

    private final JPAQueryFactory queryFactory;

    // 카테고리에 맞는 BooleanBuilder 생성
    private BooleanBuilder getWhereBuilderByManager(){

        Manager nowManager = Manager.getManager();

        List<Integer> categoryNumber = Category.getCMCategoryNumbers(nowManager);

        BooleanBuilder builder = new BooleanBuilder();

        categoryNumber.forEach(nowNum-> builder.or(construction.category.eq(nowNum)));

        if(!builder.hasValue()){
            builder.and(construction.category.eq(-1));
        }

        return builder;
    }


    private ConstructionResponseDto getCMList(int page,BooleanBuilder builder){

        Pageable pageRequest = PageDto.getPageRequest(page);

        List<Construction> content = queryFactory
                .selectFrom(construction)
                .where(
                        builder
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(construction.no.desc())
                .fetch();
        ConstructionResponseDto constructionResponseDto;

        if(!content.isEmpty()){
            JPAQuery<Construction> countQuery = queryFactory
                    .selectFrom(construction)
                    .where(
                            builder
                    );

            Page<Construction> pp = PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);

            constructionResponseDto = new ConstructionResponseDto(pp);
        }
        else{
            constructionResponseDto = new ConstructionResponseDto();
        }

        return constructionResponseDto;
    }

    private boolean checkAuth(int categoryNum){
        Manager nowManager = Manager.getManager();

        List<Integer> categoryNumber = Category.getCMCategoryNumbers(nowManager);

        boolean checkAuth=false;

        for(int i=0;i<categoryNumber.size() && !checkAuth;i++){
            checkAuth = (categoryNumber.get(i) == categoryNum);
        }

        return checkAuth;
    }


    public ResponseEntity<ConstructionResponseDto> getAllConstruction(int page) {

        BooleanBuilder builder = getWhereBuilderByManager();

        ConstructionResponseDto constructionResponseDto = getCMList(page,builder);

        constructionResponseDto.setMessage("find all construction");

       return new ResponseEntity<>(constructionResponseDto, HttpStatus.OK);

    }

    public ResponseEntity<ConstructionResponseDto> searchConstructionByName(int page, String word) {

        BooleanBuilder builder = getWhereBuilderByManager();
        builder.and(construction.name.contains(word));

        ConstructionResponseDto constructionResponseDto = getCMList(page,builder);



        constructionResponseDto.setMessage("search construction by name");
        return new ResponseEntity<>(constructionResponseDto, HttpStatus.OK);


    }

    private void saveConstructionTable(List<TableListRequestDto> tableList, int no){
        if(tableList==null){
            return;
        }
        final int[] cnt={0};
        tableList.forEach(tableListRequestDto ->{
            if(tableListRequestDto.checkTableListItem() && cnt[0] < 10){
                ConstructionTable constructionTable = (ConstructionTable.builder().build());
                constructionTable.createConstructionTableByRequest(tableListRequestDto, no);
                constructionTableRepository.save(constructionTable);
                cnt[0]++;
            }
        });
    }

    private void saveConstructionBanner(List<BannerRequestDto> banner, int no){
        if(banner==null){
            return;
        }
        final int[] cnt={0};
        banner.forEach(bannerRequestDto ->{
            if(bannerRequestDto.checkBannerItem() && cnt[0] < 30){
                ConstructionBanner constructionBanner = (ConstructionBanner.builder().build());
                constructionBanner.createConstructionBannerByRequest(bannerRequestDto, no);
                constructionBannerRepository.save(constructionBanner);
                cnt[0]++;
            }
        });
    }

    private void saveConstructionDetailImage(List<String> images,int no){
        if(images==null){
            return;
        }

        final int[] cnt={0};
        images.forEach(image ->{
            if( cnt[0] < 10){
                ConstructionDetailImage constructionDetailImage = (ConstructionDetailImage.builder()
                        .image(image)
                        .constructionNo(no)
                        .build());
                constructionDetailImageRepository.save(constructionDetailImage);
                cnt[0]++;
            }
        });

    }

    public ResponseEntity<ResponseDto> createConstruction(ConstructionRequestDto requestDto) {

        Construction construction= Construction.builder().build();
        construction.createConstructionByRequest(requestDto);

        if(!checkAuth(construction.getCategory())){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail create construction")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }

        constructionRepository.save(construction);

        int no = construction.getNo();

        saveConstructionTable(requestDto.getTableList(),no);

        saveConstructionBanner(requestDto.getBanner(), no);

        saveConstructionDetailImage(requestDto.getDetailImage(), no);

        ResponseDto responseDto = ResponseDto.builder()
                .message("Construction create")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED );

    }

    public ResponseEntity<ResponseDto> searchConstructionDetailByNo(int no) throws ProductManagementNotPresentException {

        Optional<ConstructionDetail> constructionDetailOptional = constructionDetailRepository.findById(no);

        ConstructionDetail constructionDetail= constructionDetailOptional.orElseThrow(ProductManagementNotPresentException::new);

        if(!checkAuth(constructionDetail.getCategory())){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail find detail construction")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }


        ResponseDto responseDto = ResponseDto.builder()
                .message("find constructionDetail by no")
                .data(constructionDetail)
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);



    }

    private Construction getConstructionByNo(int no) throws ProductManagementNotPresentException {
        Optional<Construction> constructionOptional = constructionRepository.findById(no);

        return constructionOptional.orElseThrow(ProductManagementNotPresentException::new);
    }
    @Transactional
    public ResponseEntity<ResponseDto> deleteConstruction(int no) throws ProductManagementNotPresentException {

        Construction construction = getConstructionByNo(no);

        if(!checkAuth(construction.getCategory())){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail delete construction")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }

        constructionTableRepository.deleteByConstructionNo(no);

        constructionBannerRepository.deleteByConstructionNo(no);

        constructionDetailImageRepository.deleteByConstructionNo(no);

        constructionRepository.delete(construction);

        ResponseDto responseDto = ResponseDto.builder()
                .message("delete construction by no")
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<ResponseDto> editConstruction(int no, ConstructionRequestDto requestDto) throws ProductManagementNotPresentException {

        Construction construction = getConstructionByNo(no);

        if(!checkAuth(construction.getCategory())){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail edit construction")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }

        construction.createConstructionByRequest(requestDto);

        if(!checkAuth(construction.getCategory())){
            ResponseDto responseDto = ResponseDto.builder()
                    .message("fail new Edit construction")
                    .data(null)
                    .build();

            return new ResponseEntity<>(responseDto,HttpStatus.FORBIDDEN);
        }

        constructionRepository.save(construction);

        constructionTableRepository.deleteByConstructionNo(no);

        constructionBannerRepository.deleteByConstructionNo(no);

        constructionDetailImageRepository.deleteByConstructionNo(no);

        saveConstructionTable(requestDto.getTableList(),no);

        saveConstructionBanner(requestDto.getBanner(), no);

        saveConstructionDetailImage(requestDto.getDetailImage(), no);

        ResponseDto responseDto = ResponseDto.builder()
                .message("edit construction by no")
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }


}
