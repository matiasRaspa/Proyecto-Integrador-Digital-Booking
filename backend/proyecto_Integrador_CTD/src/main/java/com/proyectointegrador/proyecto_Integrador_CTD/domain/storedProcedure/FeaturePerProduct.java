package com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.FeatureTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usp_featurePerProduct")
@Immutable
public class FeaturePerProduct {
    @Id
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    private FeatureTypes type;
    @Column(name = "product_id")
    private Long productId;
}
