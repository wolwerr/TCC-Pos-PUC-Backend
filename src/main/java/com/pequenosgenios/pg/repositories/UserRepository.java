package com.pequenosgenios.pg.repositories;

import com.pequenosgenios.pg.domain.Teacher;
import com.pequenosgenios.pg.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    Page<User> findAllByUsernameContainsIgnoreCase(String userName, Pageable pageable);
}
