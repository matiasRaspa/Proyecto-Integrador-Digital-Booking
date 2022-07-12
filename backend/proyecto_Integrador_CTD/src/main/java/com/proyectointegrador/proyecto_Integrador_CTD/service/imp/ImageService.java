package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;


import com.proyectointegrador.proyecto_Integrador_CTD.domain.Image;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.ImageRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IImageService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.aws.S3ImageServiceImp;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService<ImageDto> {
    private final static Logger logger = Logger.getLogger(ImageService.class);

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ModelMapper modelMapper;

    private S3ImageServiceImp s3ImageServiceImp;




    @Override
    public ImageDto save(ImageDto image) {
        Image imageEntity = mapToEntity(image);
        imageEntity = imageRepository.save(imageEntity);
        return mapToDTO(imageEntity);
    }


    @Override
    public ImageDto findById(Long id) {
        return imageRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public ImageDto updateById(Long id, ImageDto image) {
        if (imageRepository.findById(id).isPresent()) {
            Image imageEntity = mapToEntity(image);
            imageEntity.setId(id);
            Image newImage = imageRepository.save(imageEntity);
            return mapToDTO(newImage);
        } else {
            throw new ResourceNotFoundException("Image not found with id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (imageRepository.findById(id).isPresent()) {
            imageRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Image not found with id " + id);
        }

    }

    @Override
    public List<ImageDto> findAll() {
        return imageRepository.findAll().stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<ImageDto> findAllByProductId(Long id) {
        return imageRepository.findAllByProductId(id).stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }




    //-- Mapper---
    public ImageDto mapToDTO(Image image) {
        return modelMapper.map(image, ImageDto.class);
    }

    public Image mapToEntity(ImageDto imageDto) {
        return modelMapper.map(imageDto, Image.class);
    }

}
