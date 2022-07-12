package com.proyectointegrador.proyecto_Integrador_CTD.dto.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.ProductView;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDetailDto {

    private Long id;

    private String eta;

    private String startDate;

    private String endDate;

    private Long userId;

    private Long productId;

    private ProductViewDto product;

    public BookingDetailDto() {
    }
}
