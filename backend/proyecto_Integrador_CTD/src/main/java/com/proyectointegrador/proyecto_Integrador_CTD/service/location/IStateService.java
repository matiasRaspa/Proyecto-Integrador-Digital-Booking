package com.proyectointegrador.proyecto_Integrador_CTD.service.location;

import java.util.List;

public interface IStateService<T> {
    public T save(T state);

    public T findById(Long id);

    public T updateById(Long id,T state);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> saveAll(List<T> states);
}
