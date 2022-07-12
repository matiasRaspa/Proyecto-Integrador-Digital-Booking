package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.repository.storedProcedure.ScoreAvgPerProductRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure.IScoreAvgPerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreAvgPerProductService implements IScoreAvgPerProductService {
    @Autowired
    private ScoreAvgPerProductRepository scoreAvgPerProductRepository;

    @Override
    public Double getReviewScoreAvgPerProduct(Long productId) {
        return scoreAvgPerProductRepository.getReviewScoreAvgPerProduct(productId);
    }
}
