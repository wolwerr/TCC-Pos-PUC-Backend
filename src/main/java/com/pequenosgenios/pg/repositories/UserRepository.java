package com.pequenosgenios.pg.repositories;

import com.pequenosgenios.pg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
