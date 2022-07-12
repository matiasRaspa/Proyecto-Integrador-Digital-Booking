package com.proyectointegrador.proyecto_Integrador_CTD;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Category;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.CategoryRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProyectoIntegradorCtdApplicationTests {

    @Autowired
    public CategoryRepository categoryRepository;


    @Autowired
    public CategoryService categoryService;

    @Test
    void saveCategory() {
        Category category = new Category();
        category.setTitle("Categoria 1");
        category.setDescription("Descripcion de la categoria 1");
        categoryRepository.save(category);
        Assertions.assertNotNull(categoryService.findById(category.getId()));
    }

    @Test
    void findAllCategory() {

        Assertions.assertNotNull(categoryService.findAll());
    }

    @Test
    void deleteCategory() {
        categoryService.deleteById(1L);
        Assertions.assertNull(categoryService.findById(1L));
    }


}
