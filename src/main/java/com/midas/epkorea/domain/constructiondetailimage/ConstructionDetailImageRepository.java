package com.midas.epkorea.domain.constructiondetailimage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionDetailImageRepository extends JpaRepository<ConstructionDetailImage,Integer> {

    Long deleteByConstructionNo(int constructionNo);


}
