package com.proyectointegrador.proyecto_Integrador_CTD.service;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IProductService<T> {

    public T save(T product);

    public T findById(Long id);

    public T updateById(Long id,T product);

    public void deleteById(Long id);

    public List<T> findAll( );

    public List<T> findByLocationName(String locationName);

    public List<T> findByLocationId(Long locationId);

    public List<T> findByCategoryId(Long categoryId);


    public Page<T> findAllToHome(Pageable pageable);

    public List<T> findByCategoryTitle(String categoryTitle);

    public List<T> findByCategoryTitleAndLocationName(String categoryTitle, String locationName);


    List<T> findByAvailability(String startDate, String endDate);
}

