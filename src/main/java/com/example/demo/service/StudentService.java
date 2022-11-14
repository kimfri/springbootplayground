package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student createStudent(Student student){
        try {
            if (!studentRepository.existsByEmail(student.getEmail())){
                return studentRepository.save(student);
            }else {
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<Student> readStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public String updateStudent(Student student){
        if (studentRepository.existsByEmail(student.getEmail())){
            try {
                List<Student> students = studentRepository.findByEmail(student.getEmail());
                students.stream().forEach(s -> {
                    Student studentToBeUpdate = studentRepository.findById(s.getId()).get();
                    studentToBeUpdate.setName(student.getName());
                    studentToBeUpdate.setEmail(student.getEmail());
                    studentRepository.save(studentToBeUpdate);
                });
                return "Student record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Student does not exists in the database.";
        }
    }

    @Transactional
    public String deleteStudent(Student student){
        if (studentRepository.existsByEmail(student.getEmail())){
            try {
                List<Student> students = studentRepository.findByEmail(student.getEmail());
                students.stream().forEach(s -> {
                    studentRepository.delete(s);
                });
                return "Student record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Student does not exist";
        }
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }
}
