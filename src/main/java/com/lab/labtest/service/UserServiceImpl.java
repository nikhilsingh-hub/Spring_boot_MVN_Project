package com.lab.labtest.service;

import java.util.List;
import java.util.Optional;

// import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lab.labtest.entity.UserEntity;
import com.lab.labtest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<UserEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findAll(pageable);
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
