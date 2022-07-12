package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.storedProcedure;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.ImagePerProduct;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.storedProcedure.ImagePerProductRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure.IImagePerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagePerProductService implements IImagePerProductService {

    @Autowired
    private ImagePerProductRepository imagePerProductRepository;

    @Override
    public ImagePerProduct getOneImagePerProduct(Long id) {
      return imagePerProductRepository.getOneImagePerProduct(id);
    }
}

