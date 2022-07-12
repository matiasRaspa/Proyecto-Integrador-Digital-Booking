package com.proyectointegrador.proyecto_Integrador_CTD.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.PoliticTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "politics")
public class Politic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private PoliticTypes type;

    @ManyToMany(targetEntity = Product.class, mappedBy = "politics", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Product> products;

    public Politic() {
    }
}
