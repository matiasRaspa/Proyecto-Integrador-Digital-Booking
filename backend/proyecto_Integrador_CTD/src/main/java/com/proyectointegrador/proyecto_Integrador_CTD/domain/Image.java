package com.proyectointegrador.proyecto_Integrador_CTD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @NotNull
    private String url;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    //constructor vacio

    public Image() {
    }
}
