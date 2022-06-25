package com.mcb.forum.controller.dto;

import java.time.LocalDateTime;

import com.mcb.forum.entities.Response;

public class ResponseDto {

	private Long id;
	private String message;
	private LocalDateTime creationDate;
	private String autorName;
	
	public ResponseDto() {
	}
	
	public ResponseDto(Response response) {
		this.id = response.getId();
		this.message = response.getMessage();
		this.creationDate = response.getCreationDate();
		this.autorName = response.getAutor().getName();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public String getAutorName() {
		return autorName;
	}
	public void setAutorName(String autorName) {
		this.autorName = autorName;
	}
	
	
}
