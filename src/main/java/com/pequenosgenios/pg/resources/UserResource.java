package com.pequenosgenios.pg.resources;


import com.pequenosgenios.pg.dto.UserDTO;
import com.pequenosgenios.pg.repositories.UserRepository;
import com.pequenosgenios.pg.services.impl.UserDetailsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRepository userRepository;

    public UserResource(UserDetailsServiceImpl userDetailsServiceImpl,
                        UserRepository userRepository) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.userDetailsServiceImpl.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userDetailsServiceImpl.findById(id));
    }

    @GetMapping("/userName/{userName}")
    public ResponseEntity<UserDTO> findByName(@PathVariable String userName, Pageable pageable) throws MessagingException {
        this.userDetailsServiceImpl.findByUserName(userName, pageable);
        return ResponseEntity.ok(this.userDetailsServiceImpl.enviarSenha(userName, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.accepted().body(this.userDetailsServiceImpl.update(id, userDTO));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO newUserDTO) {
        try {
            this.userRepository.findByUsername(newUserDTO.getUsername()).ifPresent(user -> {
                throw new Error("Username already exists");
            });

            UserDTO userDTO = this.userDetailsServiceImpl.insert(newUserDTO);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userDTO.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userDetailsServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

}
