package com.lab.labtest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lab.labtest.entity.UserEntity;

@Service
public interface UserService {
    String saveUser(UserEntity userEntity);

    void deleteUserById(Long id);

    UserEntity getUserById(long id);
    
    UserEntity checkCredentials(UserEntity userEntity);

    List<UserEntity> getAllUsers();

    // Page<UserEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
