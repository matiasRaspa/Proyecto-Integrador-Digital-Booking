package com.proyectointegrador.proyecto_Integrador_CTD.service;

public interface IEmailSenderService {

    void sendEmail(String to, String subject, String body);

}
