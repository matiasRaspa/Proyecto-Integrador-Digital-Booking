package com.proyectointegrador.proyecto_Integrador_CTD.service.location;

import java.util.List;

public interface ICityService <T>{
    public T save(T city);

    public T findById(Long id);

    public T updateById(Long id, T city);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> saveAll(List<T> cities);
}
