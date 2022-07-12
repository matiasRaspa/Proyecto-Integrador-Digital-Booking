package com.proyectointegrador.proyecto_Integrador_CTD.repository.location;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {


}
