package com.proyectointegrador.proyecto_Integrador_CTD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private String imageUrl;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Product> products;

    public Category() {
    }


}
