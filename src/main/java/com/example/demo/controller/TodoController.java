package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("todo")
@Slf4j
public class TodoController {
	@Autowired
	private TodoService service;
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			entity.setUserId("temporary-userId");
			
			Optional<TodoEntity> entities = service.create(entity);
			
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
					.data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
					.error(error).build();
			return ResponseEntity.ok().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveTodo(){
		String temporaryUserId = "temporary-userId";
		List<TodoEntity> entities = service.retrieve(temporaryUserId);
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
				.data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
		try {
			TodoEntity entity = TodoDTO.toEntity(dto);
			entity.setUserId("temporary-userId");
			
			Optional<TodoEntity> entities = service.update(entity);
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
					.data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e){
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
					.error(error).build();
			return ResponseEntity.ok().body(response);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody TodoDTO dto){
		try {
			List<String> message = new ArrayList<>();
			String msg = service.delete(dto.getId());
			message.add(msg);
			
			ResponseDTO<String> response = ResponseDTO.<String>builder()
					.data(message).build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e){
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
					.error(error).build();
			return ResponseEntity.ok().body(response);
		}
	}
}
