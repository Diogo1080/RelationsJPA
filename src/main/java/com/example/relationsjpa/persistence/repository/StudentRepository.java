package com.example.relationsjpa.persistence.repository;

import com.example.relationsjpa.persistence.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
