package com.proyectointegrador.proyecto_Integrador_CTD.dto.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateDto {

    private Long id;
    private String name;

    private CountryDto country;


    public StateDto() {
    }
}
