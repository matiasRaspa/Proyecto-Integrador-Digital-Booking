package com.proyectointegrador.proyecto_Integrador_CTD.domain.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
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
@Table(name= "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private Set<Product> product;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    private String fullName;

    //constructor vacio
    public City() {
    }

    //set full name from name and state, and country
    public void setFullName(String stateName, String countryName) {
        this.fullName = this.name + ", " + stateName + ", " + countryName;
    }

}
