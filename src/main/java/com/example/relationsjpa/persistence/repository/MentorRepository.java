package com.example.relationsjpa.persistence.repository;

import com.example.relationsjpa.persistence.entity.Mentor;
import org.springframework.data.repository.CrudRepository;

public interface MentorRepository extends CrudRepository<Mentor, Long> {
}
