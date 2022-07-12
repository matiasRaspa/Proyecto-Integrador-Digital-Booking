package com.proyectointegrador.proyecto_Integrador_CTD.service.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetails;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.view.BookingDetailDto;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.view.BookingDetailsService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookingDetailsService {

    List<BookingDetailDto> getBookingDetailsByUserId(Long userId);


}
