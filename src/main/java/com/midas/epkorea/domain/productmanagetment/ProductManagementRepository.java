package com.midas.epkorea.domain.productmanagetment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductManagementRepository extends JpaRepository<ProductManagement,Integer> {

    Page<ProductManagement> findAllByNameContains(Pageable pageable,String name);

    Page<ProductManagement> findAllByCategoryIn(Pageable pageable, List<Integer> categories);

}
