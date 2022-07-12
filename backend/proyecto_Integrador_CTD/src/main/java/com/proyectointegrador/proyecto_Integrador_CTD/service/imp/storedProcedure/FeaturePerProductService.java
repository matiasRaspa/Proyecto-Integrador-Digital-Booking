package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.FeaturePerProduct;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.storedProcedure.FeaturePerProductRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure.IFeaturePerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturePerProductService implements IFeaturePerProductService {

    @Autowired
    private FeaturePerProductRepository featurePerProductRepository;
    @Override
    public List<FeaturePerProduct> findByProductId(Long productId) {
        return featurePerProductRepository.findByProductId(productId);
    }
}
