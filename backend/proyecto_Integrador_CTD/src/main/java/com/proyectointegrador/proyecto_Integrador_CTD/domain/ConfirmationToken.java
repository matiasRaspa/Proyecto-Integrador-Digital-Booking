package com.proyectointegrador.proyecto_Integrador_CTD.domain;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmation_tokens")
@ToString
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.user = user;
    }
}
