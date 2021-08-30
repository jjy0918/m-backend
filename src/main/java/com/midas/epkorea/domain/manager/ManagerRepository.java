package com.midas.epkorea.domain.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    Page<Manager> findAllByNameContains(String name, Pageable pageable);

    Page<Manager> findAllByBelongContains(String id, Pageable pageable);

    Page<Manager> findAllByIdContainsOrBelongContains(String id, String belong, Pageable pageable);

    Optional<Manager> findById(String id);
}
