package com.pequenosgenios.pg.dto;

import com.pequenosgenios.pg.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
   protected Long id;
   protected String email;
   protected String password;
   protected String role;
   protected String username;

   public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.username = user.getUsername();
   }
}
