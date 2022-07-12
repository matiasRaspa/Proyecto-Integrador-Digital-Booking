package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;


import com.proyectointegrador.proyecto_Integrador_CTD.controller.ReviewScoreController;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Booking;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.ReviewScore;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ReviewScoreDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.BookingRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.ReviewScoreRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IReviewScoreService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewScoreService implements IReviewScoreService<ReviewScoreDto> {
    final static Logger logger = Logger.getLogger(ReviewScoreController.class);

    @Autowired
    private ReviewScoreRepository reviewScoreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingService bookingService;


    @Override
    public ReviewScoreDto save(ReviewScoreDto reviewScore) {
        if (bookingService.findByUserIdAndProductId(reviewScore.getUser().getId(), reviewScore.getProduct().getId()) != null) {
            if (reviewScoreRepository.findByProductIdAndUserId(reviewScore.getProduct().getId(), reviewScore.getUser().getId()) != null) {

                ReviewScore reviewScore1 = reviewScoreRepository.findByProductIdAndUserId(reviewScore.getProduct().getId(), reviewScore.getUser().getId());
                reviewScore1.setScore(reviewScore.getScore());
                logger.info("updating reviewScore: " + reviewScore);
                logger.info("user id "+ reviewScore1.getUser().getId() + ". product id: " +reviewScore1.getProduct().getId());
                return mapToDTO(reviewScoreRepository.save(reviewScore1));
            }else{
                logger.info("saving reviewScore: " + reviewScore);
                ReviewScore reviewScoreEntity = mapToEntity(reviewScore);
                return mapToDTO(reviewScoreRepository.save(reviewScoreEntity));
            }
        } else {
            logger.error("Booking not found");
            throw new ResourceNotFoundException("Booking not found with id:" + reviewScore.getUser().getId() + " and " + reviewScore.getProduct().getId());
        }
    }

    @Override
    public ReviewScoreDto findById(Long id) {
        return reviewScoreRepository.findById(id).map(reviewScore -> modelMapper.map(reviewScore, ReviewScoreDto.class)).orElse(null);
    }


    @Override
    public ReviewScoreDto updateById(Long id, ReviewScoreDto reviewScore) {
        if (reviewScoreRepository.findById(id).isPresent()) {
            ReviewScore reviewScoreEntity = modelMapper.map(reviewScore, ReviewScore.class);
            reviewScoreEntity.setId(id);
            ReviewScore newReviewScore = reviewScoreRepository.save(reviewScoreEntity);
            return modelMapper.map(newReviewScore, ReviewScoreDto.class);
        } else {
            logger.error("No se encontro el producto con id: " + id);
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (reviewScoreRepository.findById(id).isPresent()) {
            reviewScoreRepository.deleteById(id);
        } else {
            logger.error("ReviewScore not found with id " + id);
            throw new ResourceNotFoundException("ReviewScore not found with id " + id);
        }

    }

    @Override
    public List<ReviewScoreDto> findAll() {
        List<ReviewScore> reviewScores = reviewScoreRepository.findAll();
        if (reviewScores.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewScores.stream().map(reviewScore -> modelMapper.map(reviewScore, ReviewScoreDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ReviewScoreDto> findByProductId(Long id) {
        return reviewScoreRepository.findByProductId(id).stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());

    }


    //-- Mapper---
    public ReviewScoreDto mapToDTO(ReviewScore reviewScore) {
        return modelMapper.map(reviewScore, ReviewScoreDto.class);
    }
    public ReviewScore mapToEntity(ReviewScoreDto reviewScore) {
        return modelMapper.map(reviewScore, ReviewScore.class);
    }

}
