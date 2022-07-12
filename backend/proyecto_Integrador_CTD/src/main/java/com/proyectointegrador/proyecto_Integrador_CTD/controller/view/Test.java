package com.proyectointegrador.proyecto_Integrador_CTD.controller.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForWSPMessage;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.WhatsAppMessageSenderService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.view.BookingDetailsForWSPMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    private WhatsAppMessageSenderService whatsAppMesaggeSenderService;

    @Autowired
    private BookingDetailsForWSPMessageService bookingDetailsForWSPMessageService;


    /*@RequestMapping("/send")
    public String send() throws IOException {
        return whatsAppMesaggeSenderService.sendBookingConfirmationMessage();
    }*/

    @GetMapping("/get={id}")
    public BookingDetailsForWSPMessage get(@PathVariable Long id) {
        return bookingDetailsForWSPMessageService.getBookingDetailsForWSPMessage(id);
    }


}
