package com.proyectointegrador.proyecto_Integrador_CTD.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Category;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Feature;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.ReviewScore;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.City;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private CategoryDto category;

    private Double score;


    private Double stars;

    private CityDto location;

    private List<FeatureDto> features;

    private Set<ImageDto> images;

    private List<PoliticDto> politics;

    private String latitude;
    private String longitude;


    public ProductDto() {
    }
    public void addImageDto(ImageDto imageDto){
        this.images.add(imageDto);
    }

    public ProductDto(Long id) {
        this.id = id;
    }
}
