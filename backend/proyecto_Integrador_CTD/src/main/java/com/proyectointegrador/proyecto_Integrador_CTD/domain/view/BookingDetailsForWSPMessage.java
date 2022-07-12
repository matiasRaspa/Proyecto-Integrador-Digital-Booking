package com.proyectointegrador.proyecto_Integrador_CTD.domain.view;

import com.amazonaws.services.dynamodbv2.xspec.S;
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
@Table(name = "vw_bookingDetailsForWSPMessage")
@Immutable
public class BookingDetailsForWSPMessage {
    @Id
    private Long id;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "location_name")
    private String locationName;
    @Column(name = "product_name")
    private String ProductName;
    @Column(name = "start_date")
    private String StartDate;
    @Column(name = "eta")
    private String ETA;

}
