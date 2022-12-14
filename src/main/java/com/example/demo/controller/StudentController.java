package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StudentController {

  @Autowired
  private StudentService studentService;

  @RequestMapping(value = "info", method = RequestMethod.GET)
  public String info() {
    return "The application is up...";
  }

//    @RequestMapping(value = "createstudent", method = RequestMethod.POST)
//    public String createStudent(@RequestBody Student student){
//        return studentService.createStudent(student);
//    }

  @PostMapping(value = "createstudent")
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    return Optional.ofNullable(studentService.createStudent(student))
        .map(createdStudent -> new ResponseEntity<>(createdStudent, HttpStatus.OK))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student already exists"));
  }

  @RequestMapping(value = "readstudents", method = RequestMethod.GET)
  public List<Student> readStudents() {
    return studentService.readStudents();
  }

  @RequestMapping(value = "updatestudent", method = RequestMethod.PUT)
  public String updateStudet(@RequestBody Student student) {
    return studentService.updateStudent(student);
  }

  @RequestMapping(value = "deletestudent", method = RequestMethod.DELETE)
  public String deleteStudent(@RequestBody Student student) {
    return studentService.deleteStudent(student);
  }

  @GetMapping(value = "student/{id}")
  public ResponseEntity<Student> getStudentByAnotherId(@PathVariable(value = "id") UUID id) {
    return studentService.getStudentById(id)
        .map(it -> new ResponseEntity<>(it, HttpStatus.OK))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such student"));
  }
}
