package com.proyectointegrador.proyecto_Integrador_CTD.repository;


import com.proyectointegrador.proyecto_Integrador_CTD.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
