package com.proyectointegrador.proyecto_Integrador_CTD.controller.location;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CityDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.location.CityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cities")
public class CityController {

    final static Logger logger = Logger.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<CityDto>findById(@PathVariable Long id){
        logger.info("Searching product with id: %s"+ (id));
        CityDto city = cityService.findById(id);
        if (Objects.isNull(city)){
            throw new ResourceNotFoundException("City not found with id:"+id);
        }
        return ResponseEntity.ok(city);
    }

    @PostMapping()

    public ResponseEntity<CityDto>save(@Valid @RequestBody CityDto cityDto){
        try{
            logger.info("Saving city: %s"+ (cityDto));
            return new ResponseEntity<CityDto>(cityService.save(cityDto), HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error saving city: %s"+ (cityDto));
            return  ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updateById(@PathVariable Long id, @RequestBody CityDto city) {
        logger.info("Updating city with id: %s"+ (id));
        CityDto actualizado = cityService.updateById(id, city);
        return ResponseEntity.ok(actualizado);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting city with id: %s"+ (id));
        cityService.deleteById(id);
        return ResponseEntity.ok("City deleted successfully " + id);

    }

    @GetMapping
    public ResponseEntity<List<CityDto>> findAll(){
        if (cityService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all cities");
            return ResponseEntity.ok(cityService.findAll());
        }

    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<CityDto>> saveAll(@RequestBody List<CityDto> cities) {
        try {
            logger.info("Saving all cities: %s"+ (cities));
            return new ResponseEntity<List<CityDto>>(cityService.saveAll(cities), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving all cities: %s"+ (cities));
            return ResponseEntity.badRequest().build();
        }
    }
}
