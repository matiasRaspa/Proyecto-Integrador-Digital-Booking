package com.proyectointegrador.proyecto_Integrador_CTD.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.PoliticTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoliticDto {

    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private PoliticTypes type;

    public PoliticDto() {
    }
}
