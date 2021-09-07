package com.midas.epkorea.service;

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
import com.midas.epkorea.dto.*;
import com.midas.epkorea.exception.ProductManagementNotPresentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConstructionService {

    private final ConstructionRepository constructionRepository;

    private final ConstructionDetailRepository constructionDetailRepository;

    private final ConstructionTableRepository constructionTableRepository;

    private final ConstructionBannerRepository constructionBannerRepository;

    private final ConstructionDetailImageRepository constructionDetailImageRepository;

    public ResponseEntity<ConstructionResponseDto> getAllConstruction(int page) {
        Pageable pageRequest = PageDto.getPageRequest(page);
        ConstructionResponseDto constructionResponseDto = new ConstructionResponseDto(constructionRepository.findAll(pageRequest));
        constructionResponseDto.setMessage("find all construction");

       return new ResponseEntity<>(constructionResponseDto, HttpStatus.OK);

    }

    public ResponseEntity<ConstructionResponseDto> searchConstructionByName(int page, String word) {

        Pageable pageRequest = PageDto.getPageRequest(page);

        ConstructionResponseDto constructionResponseDto = new ConstructionResponseDto(constructionRepository.findAllByNameContains(pageRequest,word));
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

        constructionTableRepository.deleteByConstructionNo(no);

        constructionBannerRepository.deleteByConstructionNo(no);

        constructionDetailImageRepository.deleteByConstructionNo(no);

        constructionRepository.delete(construction);

        ResponseDto responseDto = ResponseDto.builder()
                .message("delete construction by no")
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }

    public ResponseEntity<ResponseDto> editConstruction(int no, ConstructionRequestDto requestDto) throws ProductManagementNotPresentException {

        Construction construction = getConstructionByNo(no);
        construction.createConstructionByRequest(requestDto);
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
