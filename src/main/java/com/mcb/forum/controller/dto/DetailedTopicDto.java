package com.mcb.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mcb.forum.entities.Topic;
import com.mcb.forum.entities.TopicStatus;

public class DetailedTopicDto {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	private String autorName;
	private TopicStatus status;
	private List<ResponseDto> responses;
	
	public DetailedTopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.status = topic.getStatus();
		this.creationDate = topic.getCreationDate();
		this.autorName = topic.getAutor().getName();
		this.responses = new ArrayList<ResponseDto>();
		this.responses.addAll(topic.getResponses().stream().map(resp -> {return new ResponseDto(resp);}).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public List<ResponseDto> getResponses() {
		return responses;
	}

	public void setResponses(List<ResponseDto> responses) {
		this.responses = responses;
	}
	
}
