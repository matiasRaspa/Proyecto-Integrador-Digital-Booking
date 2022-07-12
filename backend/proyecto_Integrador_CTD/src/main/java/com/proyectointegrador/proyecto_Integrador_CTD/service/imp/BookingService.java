package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Booking;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForMail;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForWSPMessage;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.BookingDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.NotificationSenderException;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.BookingRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IBookingService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.view.BookingDetailsForWSPMessageService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IBookingDetailsForMailService;
import com.proyectointegrador.proyecto_Integrador_CTD.util.DatesUtil;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService<BookingDto> {
    final static Logger logger = Logger.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingDetailsForWSPMessageService bookingDetailsForWSPMessageService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DatesUtil datesUtil;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private IBookingDetailsForMailService bookingDetailsForMailService;

    @Autowired
    private WhatsAppMessageSenderService whatsAppMessageSenderService;


    @Override
    public BookingDto save(BookingDto bookingDto) {
        //TODO: ENVITAR QUE EL USUARIO NO PUEDA CREAR UNA RESERVA CON FECHA ANTES DE LA FECHA ACTUAL O UNA FEHCA YA RESERVADA
        logger.info("saving booking: " + bookingDto);
        Booking booking = mapToEntity(bookingDto);
        Booking newBooking = bookingRepository.save(booking);

        try {
            BookingDetailsForMail bookingDetailsForMail = bookingDetailsForMailService.getBookingDetailsForMail(newBooking.getId());
            emailSenderService.sendEmail(bookingDetailsForMail.getEmail(), "¡Gracias! Tu reserva en el " + bookingDetailsForMail.getProductName() + " está confirmada", emailSenderService.createBookingConfirmationTemplate(bookingDetailsForMail));

        } catch (Exception e) {
            throw new NotificationSenderException("Error sending email: ", e);
        }

        try {
            BookingDetailsForWSPMessage bookingDetailsForWSPMessage = bookingDetailsForWSPMessageService.getBookingDetailsForWSPMessage(newBooking.getId());
            whatsAppMessageSenderService.sendBookingConfirmationMessage(bookingDetailsForWSPMessage);

        }
        catch (Exception e) {
            throw new NotificationSenderException("Error sending whatsapp message: ", e);
        }

        return mapToDto(newBooking);
    }

    @Override
    public BookingDto findById(Long id) {
        logger.info("booking - findById: " + id);
        return bookingRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public BookingDto updateById(Long id, BookingDto bookingDto) {
        logger.info("updateById: " + bookingDto);
        if (bookingRepository.findById(id).isPresent()) {
            Booking booking = mapToEntity(bookingDto);
            booking.setId(id);
            return mapToDto(bookingRepository.save(booking));
        } else {
            logger.error("Booking not found");
            throw new ResourceNotFoundException("Booking not found with id:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (bookingRepository.findById(id).isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            logger.error("Booking not found");
            throw new ResourceNotFoundException("Booking not found with id:" + id);
        }
    }

    @Override
    public List<BookingDto> findAll() {
        logger.info("booking - findAll");
        return bookingRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> findByUserId(Long userId) {
        logger.info("booking - findByUserId: " + userId);
        return bookingRepository.findByUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> findByProductId(Long productId) {
        logger.info("booking - findByProductId: " + productId);
        return bookingRepository.findByProductId(productId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<LocalDate> getBookingDates(Long productId) {
        logger.info("booking - getBookingDates: " + productId);
        List<Booking> bookings = bookingRepository.findByProductId(productId);
        List<Booking> actualBookings = bookings.stream().filter(b -> b.getEndDate().isAfter(LocalDate.now())).collect(Collectors.toList());
        //logger.info("booking - getBookingDates: " + actualBookings);
        logger.info(actualBookings.size());
        List<LocalDate> bookingDates = new ArrayList<>();
        for (Booking booking : actualBookings) {
            logger.info(booking.getStartDate());
            bookingDates.addAll(datesUtil.getIntervalDateList(booking.getStartDate(), booking.getEndDate()));
        }
        logger.info("booking - getBookingDates: " + bookingDates.size());
        return bookingDates;
    }

    @Override
    public List<BookingDto> findByUserIdAndProductId(Long userId, Long productId) {
        logger.info("booking - findByUserIdAndProductId: " + userId + " - " + productId);
        return bookingRepository.findByUserIdAndProductId(userId, productId).stream().map(this::mapToDto).collect(Collectors.toList());
    }


    // --- Mappers ----
    public BookingDto mapToDto(Booking booking) {
        return modelMapper.map(booking, BookingDto.class);
    }

    public Booking mapToEntity(BookingDto bookingDto) {
        return modelMapper.map(bookingDto, Booking.class);
    }
}
