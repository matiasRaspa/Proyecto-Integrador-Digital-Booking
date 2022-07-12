package com.proyectointegrador.proyecto_Integrador_CTD.service;

import java.util.List;

public interface IUserService <T> {
    public T save(T user);

    public T findById(Long id);

    public T updateById(Long id,T user);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> saveAll(List<T> users);


}
