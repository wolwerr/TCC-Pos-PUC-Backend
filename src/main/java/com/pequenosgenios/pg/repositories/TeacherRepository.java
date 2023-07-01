package com.pequenosgenios.pg.repositories;

import com.pequenosgenios.pg.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByEmail(String email);

    Page<Teacher> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
