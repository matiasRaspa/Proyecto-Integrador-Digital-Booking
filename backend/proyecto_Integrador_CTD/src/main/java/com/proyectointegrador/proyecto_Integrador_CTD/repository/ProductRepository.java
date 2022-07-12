package com.proyectointegrador.proyecto_Integrador_CTD.repository;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//select product by location name
    @Query("select p from Product p where p.location.name = ?1")
    List<Product> findByLocationName(String locationName);

    //select product by location id
    @Query("select p from Product p where p.location.id = ?1")
    List<Product> findByLocationId(Long locationId);

    //select product by category id
    @Query("select p from Product p where p.category.id = ?1")
    List<Product> findByCategoryId(Long categoryId);

    //select product by category title
    @Query("select p from Product p where p.category.title = ?1")
    List<Product> findByCategoryTitle(String categoryTitle);

    //select products by category tile and location name
    @Query("select p from Product p where p.category.title = ?1 and p.location.name = ?2")
    List<Product> findByCategoryTitleAndLocationName(String categoryTitle, String locationName);


}
