package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/images")

public class ImageController {
    final static Logger logger = Logger.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")

    public ResponseEntity<ImageDto> findById(@PathVariable Long id) {
        logger.info("Searching image with id: %s"+ (id));
        ImageDto image = imageService.findById(id);
        if (Objects.isNull(image)) {
            throw new ResourceNotFoundException("Image not found with id " + id);
        }
        return ResponseEntity.ok(image);
    }

    @PostMapping()

    public ResponseEntity<ImageDto> save( @RequestBody ImageDto imageDto) {
        try {
            logger.info("Saving image: %s"+ (imageDto));
            return new ResponseEntity<ImageDto>(imageService.save(imageDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving image: %s"+ (imageDto));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDto> updateById(@PathVariable Long id, @RequestBody ImageDto image) {

        logger.info("Updating image with id: %s"+ (id));
        ImageDto actualizado = imageService.updateById(id, image);
        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting image with id: %s"+ (id));
        imageService.deleteById(id);
        return ResponseEntity.ok("image deleted successfully " + id);

    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> findAll() {
        if (imageService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all images");
            return ResponseEntity.ok(imageService.findAll());
        }

    }

}
