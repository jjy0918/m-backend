package com.midas.epkorea.domain.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.midas.epkorea.domain.manager.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    Page findAllByNameContains(String name, Pageable pageable);

    Page findAllByBelongContains(String id, Pageable pageable);

    Page findAllByIdContainsOrBelongContains(String id, String belong, Pageable pageable);

    Optional<Manager> findById(String id);

}
