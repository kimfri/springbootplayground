package com.example.demo.repository;

import com.example.demo.entity.Student;
import com.example.demo.entity.Students;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonParser {
  private ObjectMapper objectMapper = new ObjectMapper();
  private Path filePath = Paths.get("Students.json");


  List<Student> parseFile() throws IOException {
   return objectMapper.readValue(filePath.toFile(), Students.class).getStudentList();
  }

}
