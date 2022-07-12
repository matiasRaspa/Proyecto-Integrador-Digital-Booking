package com.proyectointegrador.proyecto_Integrador_CTD.service;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.ConfirmationToken;

import java.util.Optional;

public interface IConfirmationTokenService<T> {

    public Optional<T> findByToken(String token);

    public void setConfirmationDate(String token);
}
