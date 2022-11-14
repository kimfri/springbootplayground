package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public boolean existsByEmail(String email);

    public List<Student> findByEmail(String email);

    @Query("select max(s.id) from Student s")
    public Integer findMaxId();

    public default Optional<Student> findById(UUID id) {
        return findAll().stream()
            .filter(it -> id.equals(it.getId()))
            .findFirst();
    }
}
