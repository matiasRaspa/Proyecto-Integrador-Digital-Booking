package com.proyectointegrador.proyecto_Integrador_CTD.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {

    private RoleTypes name;

    public RoleDto() {
    }
}
