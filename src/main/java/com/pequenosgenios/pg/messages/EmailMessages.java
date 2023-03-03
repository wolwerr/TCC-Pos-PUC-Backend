package com.pequenosgenios.pg.messages;

import com.pequenosgenios.pg.dto.ContactDTO;
import com.pequenosgenios.pg.dto.UserDTO;
import java.util.Locale;

public class EmailMessages {

    public static String createTitle(UserDTO user){
        return
                user.getName() + " seu cadastro foi recebido!";
    }

    public static String createTitleContact(ContactDTO contact){
        return
              contact.getName() + " o seu contato foi recebido!";
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
                        +"<div>=============================================================</div>"
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
                        +"<div>=============================================================</div>"
                        +"<br>"
                        +"<div>https://tcc-pos-puc-frontend.vercel.app/home</div>";
    }

    public static String dadosContact(ContactDTO contact){
        return

                "<div>Olá o Senhor(a) " + contact.getName() +" enviou uma mensagem. "
                        +"<br>"
                        +"Os seus dados estão logo abaixo.</div>"
                        +"<br>"
                        +"<div>==============================================================</div>"
                        +"<div><strong>Nome: <strong>" + contact.getName() + "</div>"
                        +"<div><strong>Usuário: <strong>" + contact.getEmail() + "</div>"
                        +"<div><strong>Título: <strong>" + contact.getSubject() + "</div>"
                        +"<div><strong>Mensagem: <strong>" + contact.getMessage() + "</div>"
                        +"<div>Retornaremos o mais rápido possível</div>"
                        +"<div>=============================================================</div>"
                        +"<br>"
                        +"<div>https://tcc-pos-puc-frontend.vercel.app/home</div>";
    }
}

