package com.mcb.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mcb.forum.controller.dto.DetailedTopicDto;
import com.mcb.forum.controller.dto.TopicDto;
import com.mcb.forum.controller.form.TopicForm;
import com.mcb.forum.controller.form.UpdateTopicForm;
import com.mcb.forum.entities.Topic;
import com.mcb.forum.repositories.CourseRepository;
import com.mcb.forum.repositories.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<TopicDto> list(String courseName) {
		if (courseName == null) {
			List<Topic> topicos = topicRepository.findAll();
			return TopicDto.converter(topicos);
		} else {
			List<Topic> topicos = topicRepository.findByCourseName(courseName);
			return TopicDto.converter(topicos);
		}
	}
	
	@PostMapping
	public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		topicRepository.save(topic);
		
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailedTopicDto> detailed(@PathVariable Long id) {
		Optional<Topic> obj = topicRepository.findById(id);
		if(obj.isPresent()) {
			return ResponseEntity.ok(new DetailedTopicDto(obj.get()));	
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @Valid UpdateTopicForm form) {
		Optional<Topic> obj = topicRepository.findById(id);
		if(obj.isPresent()) {
			Topic topic = form.update(id, topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Topic> obj = topicRepository.findById(id);
		if(obj.isPresent()) {
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}