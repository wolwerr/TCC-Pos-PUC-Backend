package com.pequenosgenios.pg.resources;

import com.pequenosgenios.pg.dto.UserDTO;
import com.pequenosgenios.pg.services.impl.UserDetailsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public UserResource(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
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
    public ResponseEntity<Page<UserDTO>> findByName(@PathVariable String userName, Pageable pageable) {
        return ResponseEntity.ok(this.userDetailsServiceImpl.findByUserName(userName, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.accepted().body(this.userDetailsServiceImpl.update(id, userDTO));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO newUserDTO) {
        UserDTO userDTO = this.userDetailsServiceImpl.insert(newUserDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userDetailsServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

}
