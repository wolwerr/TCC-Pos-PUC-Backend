package com.pequenosgenios.pg.repositories;

import com.pequenosgenios.pg.domain.Contact;
import com.pequenosgenios.pg.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
