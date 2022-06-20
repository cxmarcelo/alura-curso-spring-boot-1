package com.mcb.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcb.forum.entities.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	List<Topic> findByCourseName(String courseName);

}
