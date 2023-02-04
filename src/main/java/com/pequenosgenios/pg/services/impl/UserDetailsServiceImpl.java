package com.pequenosgenios.pg.services.impl;

import com.pequenosgenios.pg.config.UserDetailsImpl;
import com.pequenosgenios.pg.domain.Teacher;
import com.pequenosgenios.pg.domain.User;
import com.pequenosgenios.pg.dto.TeacherDTO;
import com.pequenosgenios.pg.dto.UserDTO;
import com.pequenosgenios.pg.repositories.UserRepository;
import com.pequenosgenios.pg.services.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public User save(User user) {
    return userRepository.save(user);
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
  public UserDTO insert(UserDTO newUserDTO) {
    User model = new User(newUserDTO);
    model = this.userRepository.save(model);
    newUserDTO.setId(model.getId());
    return newUserDTO;
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
  public void delete(Long id) {
    this.userRepository.delete(this.findModel(id));
  }

  protected User findModel(Long id) {
    return this.userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("not found"));
  }

  public Page<UserDTO> findByUserName(String userName, Pageable pageable) {
    return this.userRepository.findAllByUsernameContainsIgnoreCase(userName, pageable).map(UserDTO::new);
  }

}

