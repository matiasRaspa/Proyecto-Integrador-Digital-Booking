package com.proyectointegrador.proyecto_Integrador_CTD.dto.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CityDto {

    private Long id;

    private String name;

    private State state;

    private String fullName;

    public CityDto() {
    }
}
