package com.proyectointegrador.proyecto_Integrador_CTD.repository.location;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository  extends JpaRepository<Country, Long> {

}

