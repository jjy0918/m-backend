package com.midas.epkorea.dto;

import com.midas.epkorea.domain.productmanagetment.ProductManagement;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductManagementResponseDto {
    private PageDto pageInfo;

    private List<ProductManagementListDto> productManagementListDtoList;

    public ProductManagementResponseDto(Page<ProductManagement> page) {
        this.pageInfo = new PageDto(page);
        productManagementListDtoList = new ArrayList<>();
        page.getContent().forEach(pmldl ->
            productManagementListDtoList.add(
                    ProductManagementListDto.builder()
                            .no(pmldl.getNo())
                            .catalog(pmldl.getCatalog())
                            .category(pmldl.getCategoryDetail().getChild())
                            .name(pmldl.getName())
                            .registrationDate(ChangeDateTime.timestampToString(pmldl.getRegistrationDate()))
                            .expose(pmldl.isExpose())
                            .build()
            )
         );
    }
}
