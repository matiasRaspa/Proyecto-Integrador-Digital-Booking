package com.proyectointegrador.proyecto_Integrador_CTD.dto.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.FeaturePerProduct;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.ImagePerProduct;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.CategoryDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.FeatureDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductViewDto {
    private Long id;

    private String name;

    private String description;

    private String category;

    private Double score;

    private Double stars;

    private Long location;

    private List<FeaturePerProduct> features;

     private ImagePerProduct image;
}
