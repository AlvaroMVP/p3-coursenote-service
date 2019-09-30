package com.project.app.testservice;

import static org.mockito.Mockito.when;

import com.project.app.model.CourseNote;
import com.project.app.repository.CourseNoteRepository;
import com.project.app.service.impl.CourseNoteServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class CourseNoteServiceTest {

  @Mock
  private CourseNoteRepository courseNoteRepository;

  @InjectMocks
  private CourseNoteServiceImpl courseNoteService;

  @Test
  public void findAll() {
    CourseNote courseNote = new CourseNote();
    courseNote.setIdStudent("");
    courseNote.setCourse("");
    courseNote.setKinship("");
    courseNote.setNote(12);
    courseNote.setNote2(12);
    courseNote.setNote3(12);
    courseNote.setAverage(12);
    courseNote.setIdTeacher("");
    when(courseNoteService.findAll()).thenReturn(Flux.just(courseNote));
    Flux<CourseNote> actual = courseNoteService.findAll();
    assertResults(actual, courseNote);
  }

  private void assertResults(Publisher<CourseNote> publisher, CourseNote... expectedProducts) {
    StepVerifier.create(publisher)
    .expectNext(expectedProducts)
      .verifyComplete();
  }

}
