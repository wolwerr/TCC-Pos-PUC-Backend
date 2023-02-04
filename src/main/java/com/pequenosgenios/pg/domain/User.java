package com.pequenosgenios.pg.domain;

import com.pequenosgenios.pg.dto.PersonDTO;
import com.pequenosgenios.pg.dto.UserDTO;
import lombok.*;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import javax.persistence.*;

@Entity(name = "user_auth")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.email = userDTO.getEmail();
        this.password = new Argon2PasswordEncoder( 16, 32, 1, 4096, 3).encode(userDTO.getPassword());
        this.role = userDTO.getRole();
        this.username = userDTO.getUsername();
    }
}

