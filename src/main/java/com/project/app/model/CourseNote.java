package com.project.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "coursenote")
public class CourseNote {

  @Id
  String idNote;
  String idStudent;
  String course;
  String kinship;
  double note;
  double note2;
  double note3;
  double average;
  String idTeacher;
  


}
