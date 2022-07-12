package com.proyectointegrador.proyecto_Integrador_CTD.service;

import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService<T> {
    public T save(T image);

    public T findById(Long id);

    public T updateById(Long id,T image);

    public void deleteById(Long id);

    public List<T> findAll();

    public List<T> findAllByProductId(Long id);


}
