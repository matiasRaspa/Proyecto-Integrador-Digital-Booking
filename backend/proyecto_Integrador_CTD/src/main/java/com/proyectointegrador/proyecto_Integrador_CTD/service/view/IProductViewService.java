package com.proyectointegrador.proyecto_Integrador_CTD.service.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.ProductView;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.view.ProductViewDto;

import java.util.List;

public interface IProductViewService {
    public List<ProductViewDto> getAllProductsView();

    public ProductViewDto getProductViewById(Long id);

    public List<ProductViewDto> filterByCategory(String category);

    public List<ProductViewDto> filterByDates(String startDate, String endDate);

    public List<ProductViewDto> filterByLocation(String locationName);

    public List<ProductViewDto> filterByDatesAndLocation(String startDate, String endDate, String locationName);

    public List<ProductViewDto> filterByDatesAndCategory(String startDate, String endDate, String category);

    public List<ProductViewDto> filterByCategoryAndLocation(String category, String locationName);

    public List<ProductViewDto> filterByCategoryAndLocationAndDates(String category, String startDate, String endDate, String locationName);

}
