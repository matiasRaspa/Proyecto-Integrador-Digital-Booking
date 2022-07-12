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
public class BookingDetailsForMail {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String productName;
    @Column(name = "full_name")
    private String locationName;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "eta")
    private String eta;
    @Column(name = "url")
    private String imageUrl;
    @Column(name = "email")
    private String email;

}
