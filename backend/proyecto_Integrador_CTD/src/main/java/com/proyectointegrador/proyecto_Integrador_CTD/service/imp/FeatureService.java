package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.controller.FeatureController;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Feature;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.FeatureDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.FeatureRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IFeatureService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FeatureService implements IFeatureService<FeatureDto> {
    final static Logger logger = Logger.getLogger(FeatureController.class);

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FeatureDto save(FeatureDto feature) {
        Feature featureEntity = mapToEntity(feature);
        featureEntity = featureRepository.save(featureEntity);

        return mapToDTO(featureEntity);
    }

    @Override
    public FeatureDto findById(Long id) {
        return featureRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public FeatureDto updateById(Long id, FeatureDto feature) {
        if (featureRepository.findById(id).isPresent()) {
            Feature featureEntity = mapToEntity(feature);
            featureEntity.setId(id);
            Feature newFeature = featureRepository.save(featureEntity);
            return mapToDTO(newFeature);
        } else {
            logger.error("No se encontro el producto con id: " + id);
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (featureRepository.findById(id).isPresent()) {
            featureRepository.deleteById(id);
        } else {
            logger.error("Product not found with id " + id);
            throw new ResourceNotFoundException("Product not found with id " + id);
        }

    }

    @Override
    public List<FeatureDto> findAll() {

        return featureRepository.findAll().stream().map(this::mapToDTO).
                collect(java.util.stream.Collectors.toList());

    }

    @Override
    public List<FeatureDto> findAllByProductId(Long id) {
        logger.info("Find all features by product id: " + id);
        List<Feature> features = featureRepository.findAll();
        //FILTER BY PRODUCT ID
        List<Feature> featuresByProductId = features.stream().filter(feature -> feature.getProducts().stream().anyMatch(product -> product.getId().equals(id))).collect(Collectors.toList());
        logger.info("Features by product id: " + featuresByProductId);
        return featuresByProductId.stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<FeatureDto> saveAll(List<FeatureDto> features) {
        List<Feature> featuresEntity = features.stream().map(this::mapToEntity).collect(Collectors.toList());
        return featureRepository.saveAll(featuresEntity).stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    /*@Override
    public List<FeatureDto> findAllByProductId(Long id) {
        return featureRepository.findAllByProductId(id).stream().map(this::mapToDTO).
                collect(java.util.stream.Collectors.toList());
    }*/

    //-- Mapper---
    public FeatureDto mapToDTO(Feature featureDto) {
        return modelMapper.map(featureDto, FeatureDto.class);
    }

    public Feature mapToEntity(FeatureDto featureDto) {
        return modelMapper.map(featureDto, Feature.class);
    }
}
