package com.pequenosgenios.pg.messages;


import com.pequenosgenios.pg.dto.UserDTO;

public class EmailMessages {

    public static String createTitle(UserDTO user){
        return
                user.getName() + " seu cadastro foi recebido!";
    }
    public static String messageToNewUser(UserDTO user, String password){
        return  "Hi " + user.getName() +"! Welcome to Ataria. "
                        +"Your data and password for access is below.\n\n"
                        +"==============================================================\n"
                        +"Name: " + user.getName() + "\n"
                        +"Usuário: " + user.getUsername() + "\n"
                        +"E-mail: " + user.getEmail() + "\n"
                        +"Password: " + user.getPassword() + "\n"
                        +"==============================================================\n\n"
                        +"<br>"
                        +"https://tcc-pos-puc-frontend.vercel.app/home";
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
}

