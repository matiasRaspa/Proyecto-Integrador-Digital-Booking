package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Category;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.CategoryDto;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.CategoryRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService implements ICategoryService<CategoryDto> {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Override
    public CategoryDto save(CategoryDto category) {
        Category categoryEntity = modelMapper.map(category, Category.class);
        categoryEntity = categoryRepository.save(categoryEntity);
        return modelMapper.map(categoryEntity, CategoryDto.class);
    }

    @Override
    public CategoryDto findById(Long id) {

        return categoryRepository.findById(id).map(category -> {
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
            categoryDto.setProductsCount(setProductsC(category));
            return categoryDto;
        }).orElse(null);
    }

    @Override
    public CategoryDto updateById(Long id, CategoryDto category) {
        if (categoryRepository.findById(id).isPresent()) {
            Category categoryEntity = modelMapper.map(category, Category.class);
            categoryEntity.setId(id);
            Category newCategory = categoryRepository.save(categoryEntity);
            return modelMapper.map(newCategory, CategoryDto.class);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        }

    }

    @Override
    public List<CategoryDto> findAll() {
        //FIND ALL AND SET PRODUCTS COUNT
        return categoryRepository.findAll().stream().map(category -> {
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
            categoryDto.setProductsCount(setProductsC(category));
            return categoryDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<CategoryDto> saveAll(List<CategoryDto> categories) {
        List<Category> categoryEntities = categories.stream().map(category -> {
            Category categoryEntity = modelMapper.map(category, Category.class);
            return categoryEntity;
        }).collect(java.util.stream.Collectors.toList());
        return categoryRepository.saveAll(categoryEntities).stream().map(category -> {
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
            categoryDto.setProductsCount(setProductsC(category));
            return categoryDto;
        }).collect(java.util.stream.Collectors.toList());

    }


    // ---- mappers ----

    private CategoryDto mapToDTO(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }


    // --- SET ---
    public Integer setProductsC(Category category) {
        return productService.findByCategoryId(category.getId()).size();
    }
}

