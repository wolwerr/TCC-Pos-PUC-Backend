package com.pequenosgenios.pg.messages;

import com.pequenosgenios.pg.dto.UserDTO;
import java.util.Locale;

public class EmailMessages {

    public static String createTitle(UserDTO user){
        return
                user.getName() + " seu cadastro foi recebido!";
    }
    public static String messageToNewUserLogo(UserDTO user){
        return

                "<div>Olá " + user.getName() +"! Seja muito bem-vindo(a) ao Pequenos Gênios. "
                        +"<br>"
                        +"Os seus dados e sua senha de acesso estão logo abaixo.</div>"
                        +"<br>"
                        +"<div>==============================================================</div>"
                        +"<div><strong>Nome: <strong>" + user.getName() + "</div>"
                        +"<div><strong>Usuário: <strong>" + user.getUsername() + "</div>"
                        +"<div><strong>E-mail: <strong>" + user.getEmail() + "</div>"
                        +"<div><strong>Password: <strong>" + user.getPassword() + "</div>"
                        +"<div>==============================================================</div>"
                        +"<br>"
                        +"<div>https://tcc-pos-puc-frontend.vercel.app/home</div>";
    }

    public static String createTitleSenha(UserDTO user){
        return
                user.getName().toLowerCase(Locale.ROOT) + ", seguem seus dados!";
    }

    public static String recuperarDados(UserDTO user){
        return

                "<div>Olá " + user.getName() +"! Olá seguem os dados solicitados. "
                        +"<br>"
                        +"Os seus dados e sua senha de acesso estão logo abaixo.</div>"
                        +"<br>"
                        +"<div>==============================================================</div>"
                        +"<div><strong>Nome: <strong>" + user.getName() + "</div>"
                        +"<div><strong>Usuário: <strong>" + user.getUsername() + "</div>"
                        +"<div><strong>E-mail: <strong>" + user.getEmail() + "</div>"
                        +"<div><strong>Password: <strong> Para trocar a senha entre em https://tcc-pos-puc-frontend.vercel.app/main/trocarSenha/"+ user.getId() + "</div>"
                        +"<div>==============================================================</div>"
                        +"<br>"
                        +"<div>https://tcc-pos-puc-frontend.vercel.app/home</div>";
    }
}

