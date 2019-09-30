package com.project.app.controller;

import com.project.app.model.CourseNote;
import com.project.app.service.impl.CourseNoteServiceImpl;
import com.project.model.Student;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1.0")
public class CourseNoteController {

  @Autowired
  private CourseNoteServiceImpl courseNoteService;

  /**
  * . method to list notes
  */
  @GetMapping("/coursenote")
  public Mono<ResponseEntity<Flux<CourseNote>>> findAll() {
    return Mono.just(ResponseEntity.ok().contentType(
    MediaType.APPLICATION_JSON_UTF8).body(courseNoteService.findAll()));
  }

  /**
  * . method to search notes by idStudent
  *
  */
  @GetMapping("/coursenote/{idStudent}")
  public Mono<ResponseEntity<CourseNote>> findByIdStudent(
      @PathVariable String idStudent) throws Exception {
    return courseNoteService.findByIdStudent(idStudent)
    .map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
    .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
  * . method to create notes
  */
  @PostMapping
  public Mono<ResponseEntity<Map<String, Object>>> create(
      @Valid @RequestBody Mono<CourseNote> studentMono) {
    Map<String, Object> reply = new HashMap<String, Object>();
    return studentMono.flatMap(coursenote -> {
      return courseNoteService.save(coursenote).map(p -> {
        reply.put("coursenote", p);
        reply.put("message", "Notes created successfully");
        return ResponseEntity.created(URI.create("/api/v1.0".concat(p.getIdNote())))
      .contentType(MediaType.APPLICATION_JSON_UTF8).body(reply);
      });
    });
  }
  /**
   * . method to update notes
   */
  @PutMapping("/{idStudent}")
  public Mono<ResponseEntity<CourseNote>> save(@RequestBody CourseNote courseNote, @PathVariable String idStudent) {
    return courseNoteService.findByIdStudent(idStudent).flatMap(s -> {
      s.setIdNote(courseNote.getIdNote());
      s.setCourse(courseNote.getCourse());
      s.setKinship(courseNote.getKinship());
      s.setNote(courseNote.getNote());
      s.setNote2(courseNote.getNote2());
      s.setNote3(courseNote.getNote3());
      s.setAverage(courseNote.getAverage());
      s.setIdTeacher(courseNote.getIdTeacher());
      return courseNoteService.save(s);
    }).map(s -> ResponseEntity.created(URI.create("/api/v1.0".concat(s.getIdStudent())))
      .contentType(MediaType.APPLICATION_JSON_UTF8).body(s))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

}
