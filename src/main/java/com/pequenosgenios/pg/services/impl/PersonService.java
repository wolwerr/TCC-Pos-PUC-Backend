package com.pequenosgenios.pg.services.impl;

import com.pequenosgenios.pg.domain.Person;
import com.pequenosgenios.pg.dto.PersonDTO;
import com.pequenosgenios.pg.repositories.PersonRepository;
import com.pequenosgenios.pg.services.Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository addressRepository) {
        this.personRepository = addressRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public PersonDTO insert(PersonDTO personDTO) {
        String email = personDTO.getEmail();
        if (this.personRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        Person model = new Person(personDTO);
        model = this.personRepository.save(model);
        personDTO.setId(model.getId());
        return personDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public PersonDTO update(Long id, PersonDTO personDTO) {
        PersonDTO fromDatabase = this.findById(id);
        Util.myCopyProperties(personDTO, fromDatabase);
        this.personRepository.save(new Person(fromDatabase));
        return personDTO;
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Person model = this.personRepository
                .findById(id).orElseThrow(() -> new RuntimeException("not found"));
        return new PersonDTO(model);
    }

}
