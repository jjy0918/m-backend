package com.midas.epkorea.domain.managerlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerLogRepository extends JpaRepository<ManagerLog,Integer> {
    Page findAllByIdContains(String id, Pageable pageable);

}
