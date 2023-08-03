package com.pequenosgenios.pg.services.impl;

import com.pequenosgenios.pg.config.UserDetailsImpl;
import com.pequenosgenios.pg.domain.User;
import com.pequenosgenios.pg.dto.UserDTO;
import com.pequenosgenios.pg.email.SendEmailService;
import com.pequenosgenios.pg.messages.EmailMessages;
import com.pequenosgenios.pg.repositories.UserRepository;
import com.pequenosgenios.pg.services.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private final SendEmailService sendEmailService;
  public SendEmailService getSendEmailService() {

    return sendEmailService;
    }
  public UserDetailsServiceImpl(UserRepository userRepository, SendEmailService sendEmailService) {
    this.userRepository = userRepository;
    this.sendEmailService = sendEmailService;
  }

  @Transactional(readOnly = true)
  public User findByUsername(String username) {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.findByUsername(username);
    return UserDetailsImpl.build(user);
  }

  @Transactional(rollbackFor = Exception.class)
  public UserDTO insert(UserDTO newUserDTO) throws MessagingException {
    String email = newUserDTO.getEmail();
    if (this.userRepository.existsByEmail(email)) {
      throw new RuntimeException("Email already exists");
    }
    User model = new User(newUserDTO);
    model = this.userRepository.save(model);
    newUserDTO.setId(model.getId());
    try {
        this.getSendEmailService().enviarEmailComAnexoNovoCadastro(
                newUserDTO.getEmail(),
                EmailMessages.createTitle(newUserDTO),
                EmailMessages.messageToNewUserLogo(newUserDTO), "/logo/Logogrande.jpg" );
        } catch (Exception e) {
        e.printStackTrace();
        }
    return newUserDTO;
  }

  @Transactional(rollbackFor = Exception.class)
  public UserDTO enviarSenha(String userName){
    UserDTO userDTO = new UserDTO(this.findByUsername(userName));
    this.userRepository.findByUsername(userDTO.getEmail());
    try {
      this.getSendEmailService().enviarEmailComAnexoRecuperarSenha(
              userDTO.getEmail(),
              EmailMessages.createTitleSenha(userDTO),
              EmailMessages.recuperarDados(userDTO), "/logo/Logogrande.jpg" );
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return userDTO;
  }

  @Transactional(readOnly = true)
  public Page<UserDTO> findAll(Pageable pageable) {
    return this.userRepository.findAll(pageable).map(UserDTO::new);
  }

  @Transactional(readOnly = true)
  public UserDTO findById(Long id) {
    return new UserDTO(this.findModel(id));
  }

  @Transactional(rollbackFor = Exception.class)
  public UserDTO update(Long id, UserDTO userDTO) {
    UserDTO fromDatabase = this.findById(id);
    Util.myCopyProperties(userDTO, fromDatabase);
    this.userRepository.save(new User(fromDatabase));
    return userDTO;
  }

  @Transactional(rollbackFor = Exception.class)
  public UserDTO trocarSenha(Long id, UserDTO userDTO) {
    UserDTO fromDatabase = this.findById(id);
    if(id == null){
      throw new RuntimeException("not found");
    }
//    if (userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
//      Util.myCopyProperties(userDTO, fromDatabase);
//      this.userRepository.save(new User(fromDatabase));
//    }
    Util.myCopyProperties(userDTO, fromDatabase);
    this.userRepository.save(new User(fromDatabase));
    return userDTO;
  }

  @Transactional(rollbackFor = Exception.class)
  public void delete(Long id) {
    this.userRepository.delete(this.findModel(id));
  }

  protected User findModel(Long id) {
    return this.userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("not found"));
  }

}

