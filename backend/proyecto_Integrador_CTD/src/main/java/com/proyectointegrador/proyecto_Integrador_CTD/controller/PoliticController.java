package com.proyectointegrador.proyecto_Integrador_CTD.controller;


import com.proyectointegrador.proyecto_Integrador_CTD.dto.PoliticDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.PoliticService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/politics")
public class PoliticController {

    final static Logger logger = Logger.getLogger(PoliticController.class);

    @Autowired
    private PoliticService politicService;

    @GetMapping("/{id}")

    public ResponseEntity<PoliticDto> findById(@PathVariable Long id) {
        logger.info("Searching politic with id: %s" + (id));
        PoliticDto politic = politicService.findById(id);
        if (Objects.isNull(politic)) {
            throw new ResourceNotFoundException("Politic not found with id " + id);
        }
        return ResponseEntity.ok(politic);
    }

    @PostMapping()

    public ResponseEntity<PoliticDto> save(@Valid @RequestBody PoliticDto politicDto) {
        try {
            logger.info("Saving politic: %s" + (politicDto));
            return new ResponseEntity<PoliticDto>(politicService.save(politicDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving politic: %s" + (politicDto));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoliticDto> updateById(@PathVariable Long id, @RequestBody PoliticDto politic) {

        logger.info("Updating politic with id: %s" + (id));
        PoliticDto actualizado = politicService.updateById(id, politic);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting politic with id: %s" + (id));
        politicService.deleteById(id);
        return ResponseEntity.ok("Politic deleted successfully " + id);

    }

    @GetMapping
    public ResponseEntity<List<PoliticDto>> findAll() {

        if (politicService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all politics");
            return ResponseEntity.ok(politicService.findAll());
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<PoliticDto>> saveAll(@RequestBody List<PoliticDto> politics) {

        try {
            logger.info("Saving all politics: %s" + (politics));
            return new ResponseEntity<List<PoliticDto>>(politicService.saveAll(politics), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving politics: %s" + (politics));
            return ResponseEntity.badRequest().build();
        }
    }

}


