package com.proyectointegrador.proyecto_Integrador_CTD.repository.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForWSPMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailsForWSPMessageRepository extends JpaRepository<BookingDetailsForWSPMessage, Long> {

    @Query(value = "CALL usp_getBookingDetailsForWSPMessage(:BookingId)", nativeQuery = true)
    BookingDetailsForWSPMessage getBookingDetailsForWSPMessage(@Param("BookingId") Long BookingId);
}
