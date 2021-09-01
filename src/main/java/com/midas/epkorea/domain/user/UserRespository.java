package com.midas.epkorea.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User,Integer> {

    Optional<User> findById(String id);

}
