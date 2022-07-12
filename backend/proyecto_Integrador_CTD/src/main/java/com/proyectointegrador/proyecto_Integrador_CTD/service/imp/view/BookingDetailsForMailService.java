package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForMail;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.view.BookingDetailsForMailRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IBookingDetailsForMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailsForMailService implements IBookingDetailsForMailService {

    @Autowired
    private BookingDetailsForMailRepository bookingDetailsForMailRepository;

    @Override
    public BookingDetailsForMail getBookingDetailsForMail(Long bookingId) {
        return bookingDetailsForMailRepository.getBookingDetailsForMail(bookingId);
    }
}
