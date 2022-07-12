package com.proyectointegrador.proyecto_Integrador_CTD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.FeatureTypes;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String iconUrl;

    private FeatureTypes type;


    @ManyToMany(targetEntity = Product.class, mappedBy = "features", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Product> products;


    public Feature() {
    }

    public void addProduct(Product product) {
        products.add(product);
    }


}
