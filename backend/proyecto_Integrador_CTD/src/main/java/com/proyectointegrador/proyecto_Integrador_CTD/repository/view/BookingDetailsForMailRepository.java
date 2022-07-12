package com.proyectointegrador.proyecto_Integrador_CTD.repository.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailsForMailRepository extends JpaRepository<BookingDetailsForMail, Long> {

    @Query(value= "{CALL usp_getBookingsDetailsForMail(:bookingId)}", nativeQuery = true)
    public BookingDetailsForMail getBookingDetailsForMail(@Param("bookingId") Long bookingId);

}
