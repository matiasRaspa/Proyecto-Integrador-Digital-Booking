package com.proyectointegrador.proyecto_Integrador_CTD.service;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Role;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.enums.RoleTypes;

import java.util.List;

public interface IRoleService<T> {
    public T save(T role);

    public T findById(Long id);

    public T updateById(Long id, T role);

    public void deleteById(Long id);

    public List<T> findAll();

    public T findByName(RoleTypes name);
}
