package com.proyectointegrador.proyecto_Integrador_CTD.domain.view;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.CategoryDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.FeatureDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.PoliticDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vw_productView")
@Immutable
public class ProductView {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "title")
    private String category;
    @Column(name = "score")
    private Double score;
    @Column(name = "stars")
    private Double stars;
    @Column(name = "city_id")
    private Long location;


    // private Set<ImageDto> images;

}
