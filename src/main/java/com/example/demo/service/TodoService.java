package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoService {
	@Autowired
	private TodoRepository repository;
	
	public void validate(TodoEntity entity) {
		if (entity == null) {
			log.warn("entity is null");
			throw new RuntimeException("entity is null");
		}
		
		if (entity.getUserId() == null) {
			log.warn("user id is null");
			throw new RuntimeException("user id is null");
		}
	}
	
	public List<TodoEntity> create(TodoEntity entity) {
		validate(entity);
		repository.save(entity);
		
//		return repository.findById(entity.getId());
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(String userId){
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(TodoEntity entity){
		validate(entity);
		if (repository.existsById(entity.getId())) {
			repository.save(entity);
		} else {
			throw new RuntimeException("unknown id");
		}
//		return repository.findById(entity.getId());
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> delete(TodoEntity entity){
		if (repository.existsById(entity.getId())) {
			repository.deleteById(entity.getId());
		} else {
			throw new RuntimeException("id does not exist");
		}

		return repository.findByUserId(entity.getUserId());
	}
}
