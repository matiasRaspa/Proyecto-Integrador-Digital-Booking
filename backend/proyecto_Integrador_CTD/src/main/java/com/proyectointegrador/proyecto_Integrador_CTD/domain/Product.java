package com.proyectointegrador.proyecto_Integrador_CTD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    private Category category;


    private Double score;

    //AVERAGE SCORE OF REVIEWS
    private Double stars;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City location;


    //HAY QUE REVEER ESTO

    @ManyToMany(targetEntity = Feature.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "feature_Product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private List<Feature> features;


    //PODRIA NO ESTAR ACA Y ATOMIZARSE
    //falta el GEt by ID
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Image> images;

    //HAY QUE REVEER ESTO
    @ManyToMany
    @JoinTable(name = "product_politic",
            joinColumns = @javax.persistence.JoinColumn(name = "product_id"),
            inverseJoinColumns = @javax.persistence.JoinColumn(name = "politic_id"))
    private List<Politic> politics;

    private String Latitude;
    private String Longitude;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Booking> bookings;

    public Product() {
    }

    public void addImage(Image image) {
        this.images.add(image);
    }
}
