package com.proyectointegrador.proyecto_Integrador_CTD.domain.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vw_bookingDetailsForMail")
@Immutable
public class BookingDetails {
    @Id
    private Long id;
    @Column(name = "eta")
    private String eta;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "product_id")
    private Long productId;
}
