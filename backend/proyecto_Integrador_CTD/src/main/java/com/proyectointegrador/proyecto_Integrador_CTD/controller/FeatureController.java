package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.FeatureDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.FeatureService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/features")
public class FeatureController {
    final static Logger logger = Logger.getLogger(FeatureController.class);

    @Autowired
    private FeatureService featureService;

    @GetMapping("/{id}")


    public ResponseEntity<FeatureDto> findById(@PathVariable Long id) {
        logger.info("Searching feature with id: %s"+ (id));
        FeatureDto feature = featureService.findById(id);
        if (Objects.isNull(feature)) {
            throw new ResourceNotFoundException("Feature not found with id " + id);
        }
        return ResponseEntity.ok(feature);
    }

    @PostMapping()


    public ResponseEntity<FeatureDto> save(@Valid @RequestBody FeatureDto featureDto) {
        try {
            logger.info("Saving feature: %s"+ (featureDto));
            return new ResponseEntity<FeatureDto>(featureService.save(featureDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving feature: %s"+ (featureDto));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureDto> updateById(@PathVariable Long id, @RequestBody FeatureDto feature) {

        logger.info("Updating feature with id: %s"+ (id));
        FeatureDto actualizado = featureService.updateById(id, feature);
        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting feature with id: %s"+ (id));
        featureService.deleteById(id);
        return ResponseEntity.ok("Feature deleted successfully " + id);

    }

    @GetMapping
    public ResponseEntity<List<FeatureDto>> findAll() {
        if (featureService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all features");
            return ResponseEntity.ok(featureService.findAll());
        }

    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<FeatureDto>> saveAll(@RequestBody List<FeatureDto> features) {
        try {
            logger.info("Saving all features: %s"+ (features));
            return new ResponseEntity<List<FeatureDto>>(featureService.saveAll(features), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving all features: %s"+ (features));
            return ResponseEntity.badRequest().build();
        }
    }



}
