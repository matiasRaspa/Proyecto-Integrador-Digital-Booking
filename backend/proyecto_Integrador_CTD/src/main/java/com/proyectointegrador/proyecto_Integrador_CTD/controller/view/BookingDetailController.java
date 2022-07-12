package com.proyectointegrador.proyecto_Integrador_CTD.controller.view;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.view.BookingDetailDto;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IBookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookingDetails")
public class BookingDetailController {

    @Autowired
    private IBookingDetailsService bookingDetailsService;

    @GetMapping("/UserId={userId}")
    public ResponseEntity<List<BookingDetailDto>> getBookingDetailsByUserId(@PathVariable Long userId) {
        List<BookingDetailDto> bookings = bookingDetailsService.getBookingDetailsByUserId(userId);
        if (bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(bookings);
    }
}
