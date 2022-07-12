package com.proyectointegrador.proyecto_Integrador_CTD.service;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService<T> {
    public T save(T t);

    public T findById(Long id);

    public T updateById(Long id, T t);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> findByUserId(Long userId);

    public List<T> findByProductId(Long productId);

    public List<LocalDate> getBookingDates(Long productId);

    public List<T> findByUserIdAndProductId(Long userId, Long productId);
}
