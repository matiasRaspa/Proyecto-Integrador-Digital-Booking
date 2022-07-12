package com.proyectointegrador.proyecto_Integrador_CTD.service;

import java.util.List;

public interface IPoliticService<T> {
    public T save(T politic);

    public T findById(Long id);

    public T updateById(Long id, T politic);

    public void deleteById(Long id);

    public List<T> findAll();

    /*public List<T> findAllByProductId(Long id);*/

    public List<T> saveAll(List<T> politic);


}
