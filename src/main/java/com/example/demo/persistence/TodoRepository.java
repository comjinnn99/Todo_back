package com.example.demo.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
	
}
