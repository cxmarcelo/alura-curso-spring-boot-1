package com.mcb.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcb.forum.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByName(String name);

}
