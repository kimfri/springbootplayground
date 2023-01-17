package com.example.demo.repository;

import com.example.demo.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends FileRepository<Student, Integer>{


    default List<Student> findAll() {
        try {
            return new JsonParser().parseFile();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    default boolean existsByEmail(String email){
        return true;
    }

    default List<Student> findByEmail(String email){
        return new ArrayList<>();
    }


    default Optional<Student> findById(UUID id) {
//        return findAll().stream()
//            .filter(it -> id.equals(it.getId()))
//            .findFirst();
        return Optional.ofNullable(null);
    }
}
