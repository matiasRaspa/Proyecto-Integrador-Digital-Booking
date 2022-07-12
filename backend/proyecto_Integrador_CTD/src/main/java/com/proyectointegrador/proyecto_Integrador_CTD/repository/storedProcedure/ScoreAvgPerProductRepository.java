package com.proyectointegrador.proyecto_Integrador_CTD.repository.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.ScoreAvgPerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreAvgPerProductRepository extends JpaRepository<ScoreAvgPerProduct,Long> {

    @Query(value = "{CALL usp_getReviewScoreAvgPerProduct(:productId)}", nativeQuery = true)
    public Double getReviewScoreAvgPerProduct(@Param("productId") Long productId);
}
