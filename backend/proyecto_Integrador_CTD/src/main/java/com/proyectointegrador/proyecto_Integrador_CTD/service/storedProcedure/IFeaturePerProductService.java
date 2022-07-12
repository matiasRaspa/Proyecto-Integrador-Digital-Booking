package com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.FeaturePerProduct;

import java.util.List;

public interface IFeaturePerProductService {
    public List<FeaturePerProduct> findByProductId(Long productId);
}
