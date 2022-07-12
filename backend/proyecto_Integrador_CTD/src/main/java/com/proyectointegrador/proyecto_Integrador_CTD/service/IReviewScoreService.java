package com.proyectointegrador.proyecto_Integrador_CTD.service;

import java.util.List;

public interface IReviewScoreService <T> {
    public T save(T reviewScore);

    public T findById(Long id);

    public T updateById(Long id,T reviewScore);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> findByProductId(Long id);
}
