package com.lab.labtest.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.labtest.entity.UserEntity;
import com.lab.labtest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveAdminDetails(){
		boolean adminExists = userRepository.existsByDesignation("admin");

        if (!adminExists){
			UserEntity myAdmin = new UserEntity();
		myAdmin.setDesignation("admin");
		myAdmin.setEmail("admin@gmail.com");
		myAdmin.setRelatedData("123");
		this.userRepository.save(myAdmin);
		}
		
		return;
	}
	@Override
	public UserEntity checkCredentials(UserEntity userEntity){
		UserEntity admin = this.userRepository.findByDesignationAndEmail("admin", userEntity.getEmail());
		if (admin != null && userEntity.getRelatedData().equals(admin.getRelatedData())) {
            admin.setRelatedData(null);
            return admin;
        } else {
            return null;
        }
	}

	@Override
	public List<UserEntity> getAllUsers(){
		List<UserEntity> candidates = userRepository.findAll();
		return candidates;
	}

	@Override
	public String saveUser(UserEntity userEntity) {
		try {
			this.userRepository.save(userEntity);
			return "User saved successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error saving user: " + e.getMessage();
		}
	}

	@Override
	public void deleteUserById(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public UserEntity getUserById(long id) {
		try {
			Optional<UserEntity> optional = userRepository.findById(id);
			UserEntity candidate = null;
			if (optional.isPresent()) {
				candidate = optional.get();
			} else {
				throw new RuntimeException(" Employee not found for id :: " + id);
			}
			return candidate;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Error while fetching for user " + e);
		}

	}
}
