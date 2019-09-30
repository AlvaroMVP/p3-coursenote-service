package com.project.app;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.assertj.core.api.Assertions;
import com.project.app.model.CourseNote;
import com.project.app.service.impl.CourseNoteServiceImpl;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  @Autowired private WebTestClient client;
  
  @Autowired private CourseNoteServiceImpl courseNoteService;
	  
  @Test
  public void create() {
  CourseNote courseNote = new CourseNote("12234","432dsffa221","French","",12,12,12,12,"5d922f5597493f13984c1f15");
  client
  .post()
  .uri("/api/v1.0/")
  .contentType(MediaType.APPLICATION_JSON_UTF8)
  .accept(MediaType.APPLICATION_JSON_UTF8)
  .body(Mono.just(courseNote), CourseNote.class)
  .exchange()
  .expectStatus()
  .isCreated()
  .expectHeader()
  .contentType(MediaType.APPLICATION_JSON_UTF8)
  .expectBodyList(CourseNote.class);
  }
  
  @Test
  public void findAll() {
	  client
      .get()
      .uri("/api/v1.0/coursenote")
      .accept(MediaType.APPLICATION_JSON_UTF8)
      .exchange()
      .expectStatus().isOk()
      .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
      .expectBodyList(CourseNote.class)
      .consumeWith(
        response -> {
          List<CourseNote> courseNote = response.getResponseBody();
          courseNote.forEach(p -> {
            System.out.println(p.getIdNote());
          });
          Assertions.assertThat(courseNote.size() > 0).isTrue();
        });
  }
  
  
  @Test
  public void findByIdStudent() {

    CourseNote courseNote = courseNoteService.findByIdStudent("5d7c0696e7ac942af83bef07").block();
    client
        .get()
        .uri("/api/v1.0/coursenote" + "/{idStudent}", Collections.singletonMap(
         "idStudent", courseNote.getIdStudent()))
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody(CourseNote.class)
        .consumeWith(
            response -> {
              CourseNote p = response.getResponseBody();
              Assertions.assertThat(p.getIdStudent()).isNotEmpty();
              Assertions.assertThat(p.getIdStudent().length() > 0).isTrue();
            });
  }
  
  @Test
  public void save() {

    CourseNote courseNote = courseNoteService.findByIdStudent("432dsffa22").block();

    CourseNote courseNoteEdit =
              new CourseNote(
                      "5d92523097493f13984c1f19",
                      "432dsffa22",
                      "History",
                      "Parent",
                      17,
                      18,
                      18,
                      17.3,
                      "5d922f5597493f13984c1f16"
                      );

    client
              .put()
              .uri("/api/v1.0" + "/{idStudent}", Collections.singletonMap(
               "idStudent", courseNote.getIdStudent()))
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .accept(MediaType.APPLICATION_JSON_UTF8)
              .body(Mono.just(courseNoteEdit), CourseNote.class)
              .exchange()
              .expectStatus()
              .isCreated()
              .expectHeader()
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .expectBody()
              .jsonPath("$.idStudent")
              .isNotEmpty();
  }

}
