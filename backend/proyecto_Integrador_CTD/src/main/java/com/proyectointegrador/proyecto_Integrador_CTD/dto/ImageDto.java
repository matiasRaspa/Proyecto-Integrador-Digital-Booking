package com.proyectointegrador.proyecto_Integrador_CTD.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDto {
    private Long id;
    private String title;
    private String url;
    private ProductDto product;


    //constructor
    public ImageDto() {
    }

    public ImageDto(String title, String url, ProductDto product) {
        this.title = title;
        this.url = url;
        this.product = product;
    }
}
