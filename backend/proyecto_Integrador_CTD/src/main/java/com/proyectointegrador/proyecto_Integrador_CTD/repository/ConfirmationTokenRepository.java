package com.proyectointegrador.proyecto_Integrador_CTD.repository;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);


    @Transactional
    @Modifying
    @Query("update ConfirmationToken c set c.confirmedAt = ?1 where c.token = ?2")
    void setConfirmedDate(LocalDateTime confirmedDateTime, String token);
}

