package com.proyectointegrador.proyecto_Integrador_CTD.service.location;

import java.util.List;

public interface ICountryService<T> {
    public T save(T country);

    public T findById(Long id);

    public T updateById(Long id,T country);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> saveAll(List<T> countries);
}
