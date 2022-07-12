package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.CategoryDto;
import com.proyectointegrador.proyecto_Integrador_CTD.service.ICategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    ICategoryService<CategoryDto> categoryService;
    final static Logger logger = Logger.getLogger(CategoryController.class);

    @PostMapping()

    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        logger.info("Guardando categoria: %s"+ categoryDto);
        return new ResponseEntity<CategoryDto>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")

    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        CategoryDto category = categoryService.findById(id);
        if (Objects.isNull(category)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateById(@PathVariable Long id, @RequestBody CategoryDto category) {
        CategoryDto actualizado = categoryService.updateById(id, category);
        if (Objects.isNull(actualizado)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (Objects.isNull(categoryService.findById(id))) {
            return ResponseEntity.notFound().build();
        } else {
            categoryService.deleteById(id);
            return ResponseEntity.ok("Se eliminó la categoria con id " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        if (categoryService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(categoryService.findAll());
        }
    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<CategoryDto>> saveAll(@RequestBody List<CategoryDto> categoryDto) {
        try {
            logger.info("Guardando categorias: %s"+ categoryDto);
            return new ResponseEntity<List<CategoryDto>>(categoryService.saveAll(categoryDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error guardando categorias: %s"+ categoryDto);
            return ResponseEntity.badRequest().build();
        }
    }
}


