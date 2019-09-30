package com.project.app.repository;

import com.project.app.model.CourseNote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

public interface CourseNoteRepository extends ReactiveMongoRepository<CourseNote, String> {

  Mono<CourseNote> findByIdStudent(String idStudent);

}
