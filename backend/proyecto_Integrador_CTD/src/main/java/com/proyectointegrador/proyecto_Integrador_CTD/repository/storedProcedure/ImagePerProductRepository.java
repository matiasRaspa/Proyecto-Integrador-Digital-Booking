package com.proyectointegrador.proyecto_Integrador_CTD.repository.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Image;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.ImagePerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagePerProductRepository extends JpaRepository<ImagePerProduct,Long> {

    @Query(value = "{CALL usp_getOneImagePerProduct(:porductId)}", nativeQuery = true)
    ImagePerProduct getOneImagePerProduct(@Param("porductId") Long id);
}
