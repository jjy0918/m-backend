package com.midas.epkorea.domain.construction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionRepository extends JpaRepository<Construction,Integer> {
    Page<Construction> findAllByNameContains(Pageable pageable, String name);

}
