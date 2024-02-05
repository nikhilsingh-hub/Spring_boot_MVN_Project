package com.lab.labtest.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.lab.labtest.entity.UserEntity;

@Service
public interface UserService {
    String saveUser(UserEntity userEntity);

    void deleteUserById(Long id);

    UserEntity getUserById(long id);
    
    UserEntity checkCredentials(UserEntity userEntity);

    void saveAdminDetails();

    List<UserEntity> getAllUsers();
}
