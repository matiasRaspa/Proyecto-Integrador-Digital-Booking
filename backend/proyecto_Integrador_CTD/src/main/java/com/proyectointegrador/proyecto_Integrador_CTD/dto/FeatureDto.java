package com.proyectointegrador.proyecto_Integrador_CTD.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.FeatureTypes;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureDto {
    private Long id;
    private String title;
    private String iconUrl;
    private FeatureTypes type;


    public FeatureDto() {
    }
}
