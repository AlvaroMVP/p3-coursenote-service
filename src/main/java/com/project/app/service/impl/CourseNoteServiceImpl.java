package com.project.app.service.impl;

import com.project.app.model.CourseNote;
import com.project.app.repository.CourseNoteRepository;
import com.project.app.service.CourseNoteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseNoteServiceImpl implements CourseNoteInterface {

  @Autowired
  public CourseNoteRepository courseNoteRepository;

  @Override
  public Flux<CourseNote> findAll() {
    return courseNoteRepository.findAll();
  }

  @Override
  public Mono<CourseNote> findByIdStudent(String idStudent) {
    return courseNoteRepository.findByIdStudent(idStudent);
  }

  @Override
  public Mono<CourseNote> save(CourseNote coursenote) {
    return courseNoteRepository.save(coursenote);
  }
  
  

}
