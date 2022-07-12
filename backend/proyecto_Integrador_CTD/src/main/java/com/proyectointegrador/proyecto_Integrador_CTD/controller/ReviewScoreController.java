package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.ReviewScoreDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.ReviewScoreService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reviewScores")
public class ReviewScoreController {
    final static Logger logger = Logger.getLogger(ReviewScoreController.class);

    @Autowired
    private ReviewScoreService reviewScoreService;

    @GetMapping("/{id}")

    public ResponseEntity<ReviewScoreDto> findById(@PathVariable Long id) {
        logger.info("Searching reviewScore with id: %s"+ (id));
        ReviewScoreDto reviewScore = reviewScoreService.findById(id);
        if (Objects.isNull(reviewScore)) {
            throw new ResourceNotFoundException("ReviewScore not found with id " + id);
        }
        return ResponseEntity.ok(reviewScore);
    }

    @PostMapping()

    public ResponseEntity<ReviewScoreDto> save(@Valid @RequestBody ReviewScoreDto reviewScoreDto) {
        try {
            logger.info("Saving feature: %s"+ (reviewScoreDto));
            return new ResponseEntity<ReviewScoreDto>(reviewScoreService.save(reviewScoreDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving reviewScore: %s"+ (reviewScoreDto));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewScoreDto> updateById(@PathVariable Long id, @RequestBody ReviewScoreDto reviewScore) {

        logger.info("Updating reviewScore with id: %s"+ (id));
        ReviewScoreDto actualizado = reviewScoreService.updateById(id, reviewScore);
        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting reviewScore with id: %s"+ (id));
        reviewScoreService.deleteById(id);
        return ResponseEntity.ok("ReviewScore deleted successfully " + id);

    }

    @GetMapping
    public ResponseEntity<List<ReviewScoreDto>> findAll() {
        if (reviewScoreService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all reviewScores");
            return ResponseEntity.ok(reviewScoreService.findAll());
        }

    }


}
