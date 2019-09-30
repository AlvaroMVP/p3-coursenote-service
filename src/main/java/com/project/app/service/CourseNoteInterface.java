package com.project.app.service;

import com.project.app.model.CourseNote;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseNoteInterface {

  public Flux<CourseNote> findAll();

  public Mono<CourseNote> findByIdStudent(String idStudent);
  
  public Mono<CourseNote> save(CourseNote coursenote);

}
