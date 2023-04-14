package com.pequenosgenios.pg.services.impl;

import com.pequenosgenios.pg.domain.Teacher;
import com.pequenosgenios.pg.dto.TeacherDTO;
import com.pequenosgenios.pg.email.SendEmailService;
import com.pequenosgenios.pg.messages.EmailMessages;
import com.pequenosgenios.pg.repositories.TeacherRepository;
import com.pequenosgenios.pg.services.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    private final SendEmailService sendEmailService;

    public SendEmailService getSendEmailService() {
        return sendEmailService;
    }

    public TeacherService(TeacherRepository teacherRepository, SendEmailService sendEmailService) {
        this.teacherRepository = teacherRepository;
        this.sendEmailService = sendEmailService;
    }

    @Transactional(rollbackFor = Exception.class)
    public TeacherDTO insert(TeacherDTO newTeacherDTO) {
        Teacher model = new Teacher(newTeacherDTO);
        model = this.teacherRepository.save(model);
        newTeacherDTO.setId(model.getId());
        try {
            this.getSendEmailService().enviarEmailComAnexoNovoCadastro(
                    newTeacherDTO.getEmail(),
                    EmailMessages.createTitle(newTeacherDTO),
                    EmailMessages.messageToNewUserLogoTeacher(newTeacherDTO), "/logo/Logogrande.jpg" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTeacherDTO;
    }

    @Transactional(readOnly = true)
    public Page<TeacherDTO> findAll(Pageable pageable) {
        return this.teacherRepository.findAll(pageable).map(TeacherDTO::new);
    }

    @Transactional(readOnly = true)
    public TeacherDTO findById(Long id) {
        return new TeacherDTO(this.findModel(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public TeacherDTO update(Long id, TeacherDTO teacherDTO) {
        TeacherDTO fromDatabase = this.findById(id);
        Util.myCopyProperties(teacherDTO, fromDatabase);
        this.teacherRepository.save(new Teacher(fromDatabase));
        return teacherDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.teacherRepository.delete(this.findModel(id));
    }

    protected Teacher findModel(Long id) {
        return this.teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Page<TeacherDTO> findByName(String name, Pageable pageable) {
        return this.teacherRepository.findAllByNameContainsIgnoreCase(name, pageable).map(TeacherDTO::new);
    }
}
