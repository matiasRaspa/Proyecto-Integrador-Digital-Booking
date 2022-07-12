package com.proyectointegrador.proyecto_Integrador_CTD.service.imp;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.BookingDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ImageDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ReviewScoreDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.ProductRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.IProductService;
import com.proyectointegrador.proyecto_Integrador_CTD.util.DatesUtil;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class ProductService implements IProductService<ProductDto> {
    final static Logger logger = Logger.getLogger(ProductService.class);


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReviewScoreService reviewService;
    @Autowired
    private FeatureService featureService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private PoliticService politicService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private DatesUtil datesUtil;

    @Override
    public ProductDto save(ProductDto product) {
        Product productEntity = modelMapper.map(product, Product.class);
        productEntity = productRepository.save(productEntity);
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @Override
    public ProductDto findById(Long id) {
        // return review score calculation with created method calculateStars
        return productRepository.findById(id).map(product -> {
            ProductDto productDto = mapToDto(product);
            //  logger.debug("productDto: " + productDto.getFeatures());
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        }).orElse(null);


    }

    @Override
    public ProductDto updateById(Long id, ProductDto product) {
        if (productRepository.findById(id).isPresent()) {
            Product productEntity = mapToEntity(product);
            productEntity.setId(id);
            Product newProduct = productRepository.save(productEntity);
            return mapToDto(newProduct);
        } else {
            logger.error("No se encontro el producto con id: " + id);
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (productRepository.findById(id).isPresent()) {

            productRepository.deleteById(id);
        } else {
            logger.error("Product not found with id " + id);
            throw new ResourceNotFoundException("Product not found with id " + id);
        }

    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(product -> {
            ProductDto productDto = mapToDto(product);
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<ProductDto> findByLocationName(String locationName) {
        return productRepository.findByLocationName(locationName).stream().map(product -> {
            ProductDto productDto = mapToDto(product);
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        }).collect(java.util.stream.Collectors.toList());


    }

    @Override
    public List<ProductDto> findByLocationId(Long locationId) {
        return productRepository.findByLocationId(locationId).stream().map(product -> {
            ProductDto productDto = mapToDto(product);
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream().map(product -> {
            ProductDto productDto = mapToDto(product);
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Page<ProductDto> findAllToHome(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(product -> {
            ProductDto productDto = mapToDto(product);
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        });
    }

    @Override
    public List<ProductDto> findByCategoryTitle(String categoryTitle) {
        return productRepository.findByCategoryTitle(categoryTitle).stream().map(product -> {
            ProductDto productDto = mapToDto(product);
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategoryTitleAndLocationName(String categoryTitle, String locationName) {
        return productRepository.findByCategoryTitleAndLocationName(categoryTitle, locationName).stream().map(product -> {
            ProductDto productDto = mapToDto(product);
            productDto.setStars(calculateStars(productDto.getId()));
            List<ImageDto> images = imageService.findAllByProductId(productDto.getId());
            for (ImageDto image : images) {
                image.setProduct(null);
                productDto.getImages().add(image);
            }
            return productDto;
        }).collect(java.util.stream.Collectors.toList());

    }

    @Override
    public List<ProductDto> findByAvailability(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        logger.info("start: " + start + " end: " + end);
        List<ProductDto> availableProducts = new ArrayList<>();
        List<ProductDto> allProducts = this.findAll();
        List<LocalDate> finderDates = new ArrayList<>(datesUtil.getIntervalDateList(start, end));
        logger.info("Finder dates: " + finderDates);
        for (ProductDto product : allProducts) {
            boolean isAvailable = true;
            logger.info("Product: " + product.getId());
            List<LocalDate> reservedDates = bookingService.getBookingDates(product.getId());
            for (LocalDate date : finderDates) {
                if (reservedDates.contains(date)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableProducts.add(product);
            }
        }
        logger.info("Available products: " + availableProducts.size());
        return availableProducts;
    }


    //-- Mapper---
    public ProductDto mapToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product mapToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }


    //---Stars calculator---
    public Double calculateStars(Long id) {
        List<ReviewScoreDto> reviewScores = reviewService.findByProductId(id);
        Double stars = 0.0;
        for (ReviewScoreDto reviewScore : reviewScores) {
            stars += reviewScore.getScore();
        }
        return stars / reviewScores.size();
    }

}
