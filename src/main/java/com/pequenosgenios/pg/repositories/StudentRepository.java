package com.pequenosgenios.pg.repositories;

import com.pequenosgenios.pg.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String email);

	Page<Student> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

}