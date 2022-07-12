package com.proyectointegrador.proyecto_Integrador_CTD.repository;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    //get bookings by user id
    List<Booking> findByUserId(Long userId);

    //get bookings by product id
    List<Booking> findByProductId(Long productId);

    @Query("Select b from Booking b where b.user.id = ?1 and b.product.id = ?2")
    List<Booking> findByUserIdAndProductId(Long userId, Long productId);



}
