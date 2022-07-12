package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.ConfirmationToken;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.ConfirmationTokenRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IConfirmationTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService<ConfirmationToken> {

    private final static Logger logger = Logger.getLogger(ConfirmationTokenService.class);
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public Optional<ConfirmationToken> findByToken(String token) {
        return confirmationTokenRepository.findByToken(token);

    }

    @Override
    public void setConfirmationDate(String token) {
        LocalDateTime confirmationDate = LocalDateTime.now();
        confirmationTokenRepository.setConfirmedDate(confirmationDate, token);
        logger.info("Confirmation date set to " + confirmationDate);
    }

}

