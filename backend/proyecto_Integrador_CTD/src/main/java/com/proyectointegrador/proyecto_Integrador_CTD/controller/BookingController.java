package com.proyectointegrador.proyecto_Integrador_CTD.controller;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.BookingDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.BadBookingException;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IBookingService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.BookingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final static Logger logger = Logger.getLogger(BookingController.class);
    @Autowired
    private BookingService bookingService;

    @GetMapping("/{id}")


    public ResponseEntity<BookingDto> findById(@PathVariable Long id) {
        BookingDto booking = bookingService.findById(id);
        if (Objects.isNull(booking)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        return ResponseEntity.ok(booking);
    }

    @PostMapping()


    public ResponseEntity<BookingDto> save(@Valid @RequestBody BookingDto bookingDto) {
        try {
            return new ResponseEntity<BookingDto>(bookingService.save(bookingDto), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving Booking: %s" + (bookingDto));
            throw new BadBookingException("Error saving Booking: " + (bookingDto));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        if (Objects.isNull(bookingService.findById(id))) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        bookingService.deleteById(id);


    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateById(@PathVariable Long id, @RequestBody BookingDto booking) {
        logger.info("Updating product with id: %s" + (id));
        BookingDto updated = bookingService.updateById(id, booking);
        return ResponseEntity.ok(updated);

    }

    @GetMapping()


    public ResponseEntity<List<BookingDto>> findAll() {
        if (bookingService.findAll().isEmpty()) {

            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(bookingService.findAll());
    }

    @GetMapping("/product/{id}")


    public ResponseEntity<List<BookingDto>> findByProductId(@PathVariable Long id) {
        if (bookingService.findByProductId(id).isEmpty()) {

            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(bookingService.findByProductId(id));
    }

    @GetMapping("/user/{id}")


    public ResponseEntity<List<BookingDto>> findByUserId(@PathVariable Long id) {
        if (bookingService.findByUserId(id).isEmpty()) {

            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(bookingService.findByUserId(id));
    }

    @GetMapping("/product/{id}/bookingDates")


    public ResponseEntity<List<LocalDate>> getBookingDates(@PathVariable Long id) {
        if (bookingService.getBookingDates(id).isEmpty()) {

            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(bookingService.getBookingDates(id));
    }

}
