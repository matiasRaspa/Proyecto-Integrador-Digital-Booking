package com.proyectointegrador.proyecto_Integrador_CTD.repository.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.FeaturePerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeaturePerProductRepository extends JpaRepository<FeaturePerProduct, Long> {

    @Query(value="{Call usp_featurePerProduct(:productId)}",nativeQuery=true)
    public List<FeaturePerProduct> findByProductId(@Param("productId")long productId);
}
