package com.pequenosgenios.pg.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class SendEmailService {

    private final JavaMailSender envioEmailDoJava;

    public SendEmailService(final JavaMailSender javaMailSender) {
        
        this.envioEmailDoJava = javaMailSender;
    }

    public void enviarEmailComAnexoNovoCadastro(String para, String titulo, String conteudo, String logo)
            throws MessagingException {
        log.info("Sendind email to confirm the data..");
        var mensagem = envioEmailDoJava.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true); // html definido
        helper.setTo(para);
        helper.setSubject(titulo);
        helper.setText(conteudo, true);
        helper.addAttachment("Logogrande.jpg", new ClassPathResource(logo));
        envioEmailDoJava.send(mensagem);
        log.info("Email com anexo enviado com sucesso");
    }

    public void enviarEmailComAnexoContact(String para, String titulo, String conteudo, String logo)
            throws MessagingException {
        log.info("Sendind email to confirm the data..");
        var mensagem = envioEmailDoJava.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true); // html definido
        helper.setBcc("rlmwolwer@gmail.com");
        helper.setTo(para);
        helper.setSubject(titulo);
        helper.setText(conteudo, true);
        helper.addAttachment("Logogrande.jpg", new ClassPathResource(logo));
        envioEmailDoJava.send(mensagem);
        log.info("Email com anexo enviado com sucesso");
    }

    public void enviarEmailComAnexoRecuperarSenha(String para, String titulo, String conteudo, String logo)
            throws MessagingException {
        log.info("Sendind email to confirm the data..");
        var mensagem = envioEmailDoJava.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true); // html definido
        helper.setTo(para);
        helper.setSubject(titulo);
        helper.setText(conteudo, true);
        helper.addAttachment("Logogrande.jpg", new ClassPathResource(logo));
        envioEmailDoJava.send(mensagem);
        log.info("Email com anexo enviado com sucesso");
    }
}
