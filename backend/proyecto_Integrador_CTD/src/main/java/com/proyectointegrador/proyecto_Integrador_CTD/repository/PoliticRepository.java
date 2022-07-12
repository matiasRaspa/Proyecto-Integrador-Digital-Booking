package com.proyectointegrador.proyecto_Integrador_CTD.repository;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Politic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PoliticRepository extends JpaRepository<Politic, Long> {



 /*   List<Politic> findByProductId(Long id);*/
}
