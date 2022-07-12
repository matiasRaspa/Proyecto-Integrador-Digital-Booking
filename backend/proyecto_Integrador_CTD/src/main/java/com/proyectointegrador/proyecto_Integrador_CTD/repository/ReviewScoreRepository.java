package com.proyectointegrador.proyecto_Integrador_CTD.repository;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.ReviewScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewScoreRepository extends JpaRepository<ReviewScore, Long> {

    //select review scores by product id
    @Query("SELECT r FROM ReviewScore r WHERE r.product.id = ?1")
    public List<ReviewScore> findByProductId(Long productId);


    @Query("SELECT r FROM ReviewScore r WHERE r.product.id = ?1 AND r.user.id = ?2")
    public ReviewScore findByProductIdAndUserId(Long productId, Long userId);
}
