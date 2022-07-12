package com.proyectointegrador.proyecto_Integrador_CTD.repository.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductViewRepository extends JpaRepository<ProductView, Long> {

    //Non filter
    @Query(value = "SELECT * FROM vw_productview", nativeQuery = true)
    List<ProductView> getAllProductView();

    //get one product by id
    @Query(value = "{Call usp_getProductViewById(:productId)}", nativeQuery = true)
    ProductView getProductViewById(@Param("productId") Long productId);

    //filter by category
    @Query(value = "CALL usp_filterProductViewsByCategory(:category)", nativeQuery = true)
    public List<ProductView> filterByCategory(@Param("category") String category);

    //filter by dates
    @Query(value = "{CALL usp_filterProductViewByDates(:startDate,:endDate)}", nativeQuery = true)
    public List<ProductView> filterByDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

    //filter by location
    @Query(value = "{CALL usp_filterProductViewByLocation(:locationName)}", nativeQuery = true)
    public List<ProductView> filterByLocation(@Param("locationName") String locationName);

    //filter by dates and location
    @Query(value = "{CALL usp_filterProductViewByDatesAndLocation(:startDate,:endDate,:locationName)}", nativeQuery = true)
    public List<ProductView> filterByDatesAndLocation(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("locationName") String locationName);

    //filter by dates and category
    @Query(value = "{CALL usp_filterProductViewByDatesAndCategory(:startDate,:endDate,:category)}", nativeQuery = true)
    public List<ProductView> filterByDatesAndCategory(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("category") String category);

    //filter by category and location
    @Query(value = "{CALL usp_filterProductsViewByCategoryAndLocation(:category,:locationName)}", nativeQuery = true)
    public List<ProductView> filterByCategoryAndLocation(@Param("category") String category, @Param("locationName") String locationName);

    //filter by dates and category and location
    @Query(value = "{CALL usp_filterTotal(:category,:startDate,:endDate,:locationName)}", nativeQuery = true)
    public List<ProductView> filterByCategoryAndLocationAndDates(@Param("category") String category, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("locationName") String locationName);
}
