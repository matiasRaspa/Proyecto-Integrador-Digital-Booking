package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetails;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.view.BookingDetailDto;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.view.BookingDetailsRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IBookingDetailsService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingDetailsService implements IBookingDetailsService {
     private final static Logger logger = Logger.getLogger(BookingDetailsService.class);

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductViewService productViewService;


    @Override
    public List<BookingDetailDto> getBookingDetailsByUserId(Long userId) {
        List<BookingDetails> bookingDetails = bookingDetailsRepository.getBookingsPerUser(userId);
        logger.info("BookingDetailsService.getBookingDetailsByUserId: " + bookingDetails.size());
        List<BookingDetailDto> bookingDetailDtos = bookingDetails.stream()
                .map(this::convertToDto)
                .collect(java.util.stream.Collectors.toList());
        for (BookingDetailDto bookingDetailDto : bookingDetailDtos) {
            bookingDetailDto.setProduct(productViewService.getProductViewById(bookingDetailDto.getProductId()));
        }
        logger.info("BookingDetailsService.getBookingDetailsByUserId: " + bookingDetailDtos.size());
        return bookingDetailDtos;
    }


    //mapper

    private BookingDetailDto convertToDto(BookingDetails bookingDetails) {
        return modelMapper.map(bookingDetails, BookingDetailDto.class);
    }
}


