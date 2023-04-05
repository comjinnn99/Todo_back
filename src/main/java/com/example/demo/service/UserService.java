package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(UserEntity userEntity) {
		if (userEntity == null || userEntity.getEmail() == null) {
			throw new RuntimeException("invalid arguments");
		}
		
		String email = userEntity.getEmail();
		System.out.println("userservice>>> userentity: "+userEntity);
		
		if (userRepository.existsByEmail(email)) {
			log.warn("email already exists {}", email);
			throw new RuntimeException("email already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity getByCredentials(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
}
