package com.proyectointegrador.proyecto_Integrador_CTD.repository.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

    @Query(value = "{call usp_BookingsPerUser(:userId)}", nativeQuery = true)
    public List<BookingDetails> getBookingsPerUser(@Param("userId") Long userId);

}

