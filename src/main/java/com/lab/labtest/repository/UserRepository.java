package com.lab.labtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.labtest.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
}
