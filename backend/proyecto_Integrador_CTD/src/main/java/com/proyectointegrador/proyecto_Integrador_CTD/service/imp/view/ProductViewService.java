package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.FeaturePerProduct;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.ProductView;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.view.ProductViewDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.view.ProductViewRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IImageService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.imp.storedProcedure.ScoreAvgPerProductService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure.IFeaturePerProductService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure.IImagePerProductService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure.IScoreAvgPerProductService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IProductViewService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductViewService implements IProductViewService {
    private final static Logger logger = Logger.getLogger(ProductViewService.class);
    @Autowired
    private ProductViewRepository productViewRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IImagePerProductService imagePerProductService;

    @Autowired
    private IFeaturePerProductService featurePerProductService;

    @Autowired
    private IScoreAvgPerProductService scoreAvgPerProductService;

    @Override
    public List<ProductViewDto> getAllProductsView() {
        List<ProductView> products = productViewRepository.getAllProductView();
        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));
        }
        return proDto;
    }

    @Override
    public ProductViewDto getProductViewById(Long id) {
        ProductView product = productViewRepository.getProductViewById(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found" + id);
        }
        ProductViewDto productViewDto = mapToDto(product);
        productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
        productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
        productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));
        return productViewDto;
    }

    @Override
    public List<ProductViewDto> filterByCategory(String category) {
        List<ProductView> products = productViewRepository.filterByCategory(category);
        List<FeaturePerProduct> features = new ArrayList<>();

        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            features = featurePerProductService.findByProductId(productViewDto.getId());
            productViewDto.setFeatures(features);
        }
        return proDto;
    }

    @Override
    public List<ProductViewDto> filterByDates(String startDate, String endDate) {

        List<ProductView> products = productViewRepository.filterByDates(startDate, endDate);
        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));

        }
        return proDto;
    }

    @Override
    public List<ProductViewDto> filterByLocation(String locationName) {
        List<ProductView> products = productViewRepository.filterByLocation(locationName);
        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));
        }
        return proDto;
    }

    @Override
    public List<ProductViewDto> filterByDatesAndLocation(String startDate, String endDate, String locationName) {
        List<ProductView> products = productViewRepository.filterByDatesAndLocation(startDate, endDate, locationName);
        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));
        }
        return proDto;
    }

    @Override
    public List<ProductViewDto> filterByDatesAndCategory(String startDate, String endDate, String category) {
        List<ProductView> products = productViewRepository.filterByDatesAndCategory(startDate, endDate, category);
        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));
        }
        return proDto;

    }

    @Override
    public List<ProductViewDto> filterByCategoryAndLocation(String category, String locationName) {
        List<ProductView> products = productViewRepository.filterByCategoryAndLocation(category, locationName);
        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));
        }
        return proDto;
    }

    @Override
    public List<ProductViewDto> filterByCategoryAndLocationAndDates(String category, String startDate, String endDate, String locationName) {
        List<ProductView> products = new ArrayList<ProductView>();
        if (category.equals(" ") && locationName.equals(" ") && startDate.equals(" ") && endDate.equals(" ")) {
            return this.getAllProductsView();
        } else if (category.equals(" ") && locationName.equals(" ") && !startDate.equals(" ") && !endDate.equals(" ")) {
            products = productViewRepository.filterByDates(startDate, endDate);
        } else if (category.equals(" ") && !locationName.equals(" ") && startDate.equals(" ") && endDate.equals(" ")) {
            products = productViewRepository.filterByLocation(locationName);
        } else if (category.equals(" ") && !locationName.equals(" ") && !startDate.equals(" ") && !endDate.equals(" ")) {
            products = productViewRepository.filterByDatesAndLocation(startDate, endDate, locationName);
        } else if (!category.equals(" ") && locationName.equals(" ") && startDate.equals(" ") && endDate.equals(" ")) {
            products = productViewRepository.filterByCategory(category);
        } else if (!category.equals(" ") && locationName.equals(" ") && !startDate.equals(" ") && !endDate.equals(" ")) {
            products = productViewRepository.filterByDatesAndCategory(startDate, endDate, category);
        } else if (!category.equals(" ") && !locationName.equals(" ") && startDate.equals(" ") && endDate.equals(" ")) {
            products = productViewRepository.filterByCategoryAndLocation(category, locationName);
        } else if (!category.equals(" ") && !locationName.equals(" ") && !startDate.equals(" ") && !endDate.equals(" ")) {
            logger.info(category + " " + startDate + " " + endDate + " " + locationName);
            products = productViewRepository.filterByCategoryAndLocationAndDates(category, startDate, endDate, locationName);
            logger.info("products: " + products);
        }

        List<ProductViewDto> proDto = products.stream().map(this::mapToDto).collect(Collectors.toList());
        for (ProductViewDto productViewDto : proDto) {
            productViewDto.setImage(imagePerProductService.getOneImagePerProduct(productViewDto.getId()));
            productViewDto.setStars(scoreAvgPerProductService.getReviewScoreAvgPerProduct(productViewDto.getId()));
            productViewDto.setFeatures(featurePerProductService.findByProductId(productViewDto.getId()));
        }
        return proDto;
    }


    // mapper--
    private ProductViewDto mapToDto(ProductView productView) {
        return modelMapper.map(productView, ProductViewDto.class);
    }


}
