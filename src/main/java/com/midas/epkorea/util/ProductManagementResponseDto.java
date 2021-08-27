package com.midas.epkorea.util;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.productmanagetment.ProductManagement;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductManagementResponseDto {
    private PageDto pageInfo;

    private List<ProductManagementListDto> productManagementListDtoList;

    public ProductManagementResponseDto(Page page) {
        this.pageInfo = new PageDto(page);
        productManagementListDtoList = new ArrayList<>();
        page.getContent().forEach(pmdto ->{

            ProductManagement pm = (ProductManagement) pmdto;

            productManagementListDtoList.add(
                    ProductManagementListDto.builder()
                            .no(pm.getNo())
                            .catalog(pm.getCatalog())
                            .category(pm.getCategoryDetail().getChild())
                            .name(pm.getName())
                            .registrationDate(ChangeDateTime.TimestampToString(pm.getRegistrationDate()))
                            .expose(pm.isExpose())
                            .build()
            );
        } );
    }
}
