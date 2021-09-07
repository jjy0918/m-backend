package com.midas.epkorea.domain.constructiontable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionTableRepository extends JpaRepository<ConstructionTable,Integer> {

    Long deleteByConstructionNo(int constructionNo);

}
