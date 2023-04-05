package com.example.demo.dto;

import java.util.List;

import com.example.demo.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<E> {
	private String error;
	private List<E> data;
}

