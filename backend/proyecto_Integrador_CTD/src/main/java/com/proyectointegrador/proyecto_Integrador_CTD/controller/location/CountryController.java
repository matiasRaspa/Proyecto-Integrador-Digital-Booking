package com.proyectointegrador.proyecto_Integrador_CTD.controller.location;


import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CountryDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.location.CountryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins = "*")
public class CountryController {

    final static Logger logger = Logger.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping("/{id}")

    public ResponseEntity<CountryDto> findById(@PathVariable Long id) {
        logger.info("Searching country with id: %s"+ (id));
        CountryDto country = countryService.findById(id);
        if (Objects.isNull(country)) {
            throw new ResourceNotFoundException("Country not found with id:" + id);
        }
        return ResponseEntity.ok(country);
    }

    @PostMapping()

    public ResponseEntity<CountryDto> save(@Valid @RequestBody CountryDto countryDto) {
        try {
            logger.info("Saving country: %s"+ (countryDto));
            return new ResponseEntity<CountryDto>(countryService.save(countryDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving country: %s"+ (countryDto));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> updateById(@PathVariable Long id, @RequestBody CountryDto country) {
        logger.info("Updating country with id: %s"+ (id));
        CountryDto actualizado = countryService.updateById(id, country);
        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting country with id: %s"+ (id));
        countryService.deleteById(id);
        return ResponseEntity.ok("Country deleted successfully " + id);

    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> findAll() {
        if (countryService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all countries");
            return ResponseEntity.ok(countryService.findAll());
        }

    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<CountryDto>> saveAll( @RequestBody List<CountryDto> countries) {
       try {
           logger.info("Saving all countries: %s"+ (countries));
           return new ResponseEntity<List<CountryDto>>(countryService.saveAll(countries), HttpStatus.CREATED);
       } catch (Exception e) {
           logger.error("Error saving all countries: %s"+ (countries));
           return ResponseEntity.badRequest().build();
       }
    }
}