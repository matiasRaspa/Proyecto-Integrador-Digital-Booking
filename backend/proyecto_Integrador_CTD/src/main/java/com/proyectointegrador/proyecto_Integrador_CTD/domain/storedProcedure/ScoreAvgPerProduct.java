package com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure;

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
@Table(name = "usp_getReviewScoreAvgPerProduct")
@Immutable
public class ScoreAvgPerProduct {
    @Id
    @Column(name = "avg(r.product_id)")
    private Long id;

    @Column(name = "avg(r.score)")
    private Double average;
}
