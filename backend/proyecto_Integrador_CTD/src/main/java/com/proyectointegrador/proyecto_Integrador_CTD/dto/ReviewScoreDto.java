package com.proyectointegrador.proyecto_Integrador_CTD.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewScoreDto {
    private Long id;


    private Product product;
    private UserDto user;
    private Integer score;

    public ReviewScoreDto() {
    }
}
