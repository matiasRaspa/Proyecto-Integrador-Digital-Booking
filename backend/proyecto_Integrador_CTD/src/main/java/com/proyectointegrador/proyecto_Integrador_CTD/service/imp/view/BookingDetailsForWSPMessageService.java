package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForWSPMessage;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.view.BookingDetailsForWSPMessageRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IBookingDetailsForWSPMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailsForWSPMessageService implements IBookingDetailsForWSPMessageService {
    @Autowired
    private BookingDetailsForWSPMessageRepository bookingDetailsForWSPMessageRepository;

    @Override
    public BookingDetailsForWSPMessage getBookingDetailsForWSPMessage(Long id) {
        BookingDetailsForWSPMessage bookingDetailsForWSPMessage = bookingDetailsForWSPMessageRepository.getBookingDetailsForWSPMessage(id);
        if (bookingDetailsForWSPMessage == null) {

            throw new RuntimeException("BookingDetailsForWSPMessage not found");
        }
        return bookingDetailsForWSPMessage;
    }
}


