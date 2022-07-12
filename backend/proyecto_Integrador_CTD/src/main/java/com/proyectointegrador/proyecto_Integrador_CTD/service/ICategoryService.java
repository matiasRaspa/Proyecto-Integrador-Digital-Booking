package com.proyectointegrador.proyecto_Integrador_CTD.service;

import java.util.List;

public interface ICategoryService<T> {

    public T save(T category);

    public T findById(Long id);

    public T updateById(Long id,T category);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> saveAll(List<T> categories);
}
