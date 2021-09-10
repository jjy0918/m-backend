package com.midas.epkorea.dto;

import com.midas.epkorea.domain.productmanagetment.ProductManagement;
import com.midas.epkorea.util.ChangeDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductManagementResponseDto extends ResponseDto{
    private PageDto pageInfo;

    private List<ProductManagementListDto> data;

    public ProductManagementResponseDto(Page<ProductManagement> page) {
        this.pageInfo = new PageDto(page);
        data = new ArrayList<>();
        page.getContent().forEach(pmldl ->
                data.add(
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
