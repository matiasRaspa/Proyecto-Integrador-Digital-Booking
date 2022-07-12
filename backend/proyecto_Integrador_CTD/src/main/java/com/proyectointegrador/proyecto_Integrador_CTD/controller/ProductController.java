package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.CategoryDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/products")
public class ProductController {

    final static Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")

    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        logger.info("Searching product with id: %s" + (id));
        ProductDto product = productService.findById(id);
        if (Objects.isNull(product)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping()

    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto) {
        try {
            logger.info("Saving product: %s" + (productDto));
            return new ResponseEntity<ProductDto>(productService.save(productDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving product: %s" + (productDto));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateById(@PathVariable Long id, @RequestBody ProductDto product) {

        logger.info("Updating product with id: %s" + (id));
        ProductDto actualizado = productService.updateById(id, product);
        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        logger.info("Deleting product with id: %s" + (id));
        productService.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully " + id);

    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> findAll() {
        if (productService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all products");
            return ResponseEntity.ok(productService.findAll());
        }

    }

    @GetMapping(headers = "Home=true", params = {"page", "size"})
    public ResponseEntity<Page<ProductDto>> findAll(@PageableDefault( sort = "id", direction = ASC) Pageable  pageable) {
       try {
           logger.info("Searching all products");
           return ResponseEntity.ok(productService.findAllToHome(pageable));
       } catch (Exception e) {
           logger.error("Error searching all products");
           return ResponseEntity.badRequest().build();
       }
    }



    @GetMapping("/location={locationName}")
    public ResponseEntity<List<ProductDto>> findByLocationName(@PathVariable String locationName) {
        if (productService.findByLocationName(locationName).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all products");
            return ResponseEntity.ok(productService.findByLocationName(locationName));
        }

    }

    @GetMapping("/locationId={locationId}")
    public ResponseEntity<List<ProductDto>> findByLocationId(@PathVariable Long locationId) {
        if (productService.findByLocationId(locationId).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all products");
            return ResponseEntity.ok(productService.findByLocationId(locationId));
        }

    }
    @GetMapping("/category={categoryName}")
    public ResponseEntity<List<ProductDto>> findByCategoryName(@PathVariable String categoryName) {
        if (productService.findByCategoryTitle(categoryName).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all products");
            return ResponseEntity.ok(productService.findByCategoryTitle(categoryName));
        }

    }
    @GetMapping("/categoryId={categoryId}")
    public ResponseEntity<List<ProductDto>> findByCategoryId(@PathVariable Long categoryId) {
        if (productService.findByCategoryId(categoryId).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all products");
            return ResponseEntity.ok(productService.findByCategoryId(categoryId));
        }

    }
    @GetMapping("/categoryTitle={categoryTitle}/locationName={locationName}")
    public ResponseEntity<List<ProductDto>> findByCategoryTitleAndLocationName(@PathVariable String categoryTitle, @PathVariable String locationName) {
        if (productService.findByCategoryTitleAndLocationName(categoryTitle, locationName).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all products");
            return ResponseEntity.ok(productService.findByCategoryTitleAndLocationName(categoryTitle, locationName));
        }

    }

    @GetMapping("/startDate={startDate}/endDate={endDate}")
    public ResponseEntity<List<ProductDto>> findByAvailability(@PathVariable String startDate, @PathVariable String endDate) {
        if (productService.findByAvailability(startDate, endDate).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Searching all products");
            return ResponseEntity.ok(productService.findByAvailability(startDate, endDate));
        }
    }

}
