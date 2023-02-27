package com.pequenosgenios.pg.services.impl;

import com.pequenosgenios.pg.domain.Contact;
import com.pequenosgenios.pg.dto.ContactDTO;
import com.pequenosgenios.pg.services.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pequenosgenios.pg.repositories.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public ContactDTO insert(ContactDTO newContactDTO) {
        Contact model = new Contact(newContactDTO);

        model = this.contactRepository.save(model);
        newContactDTO.setId(model.getId());
        return newContactDTO;
    }
    @Transactional(readOnly = true)
    public Page<ContactDTO> findAll(Pageable pageable) {
        return this.contactRepository.findAll(pageable).map(ContactDTO::new);
    }

    @Transactional(readOnly = true)
    public ContactDTO findById(Long id) {
        return new ContactDTO(this.findModel(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public ContactDTO update(Long id, ContactDTO contactDTO) {
        ContactDTO fromDatabase = this.findById(id);
        Util.myCopyProperties(contactDTO, fromDatabase);
        this.contactRepository.save(new Contact(fromDatabase));
        return contactDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.contactRepository.delete(this.findModel(id));
    }

    protected Contact findModel(Long id) {
        return this.contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Page<ContactDTO> findByName(String name, Pageable pageable) {
        return this.contactRepository.findAllByNameContainsIgnoreCase(name, pageable).map(ContactDTO::new);
    }
}
