package com.midas.epkorea.domain.productmanagetment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductManagementRepository extends JpaRepository<ProductManagement,Integer> {

    Page findAllByNameContains(Pageable pageable,String name);

}
