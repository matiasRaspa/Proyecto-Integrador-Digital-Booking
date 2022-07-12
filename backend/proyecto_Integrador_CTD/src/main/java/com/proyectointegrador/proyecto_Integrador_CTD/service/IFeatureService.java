package com.proyectointegrador.proyecto_Integrador_CTD.service;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;

import java.util.List;

public interface IFeatureService<T> {
    public T save(T feature);

    public T findById(Long id);

    public T updateById(Long id, T feature);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> findAllByProductId(Long id);

    public List<T> saveAll(List<T> features);
}
