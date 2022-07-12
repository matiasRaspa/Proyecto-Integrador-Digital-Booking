package com.proyectointegrador.proyecto_Integrador_CTD.controller.view;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.storedProcedure.FeaturePerProduct;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.BookingDetailsForMail;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.view.ProductView;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.view.ProductViewDto;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.storedProcedure.FeaturePerProductRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.storedProcedure.IFeaturePerProductService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IBookingDetailsForMailService;
import com.proyectointegrador.proyecto_Integrador_CTD.service.view.IProductViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/productView")
public class ProductViewController {

    @Autowired
    private IProductViewService productViewService;

    @Autowired
    private IFeaturePerProductService featurePerProductService;

    //TODO, DELETE THIS

    @Autowired
    private IBookingDetailsForMailService bookingDetailsForMailService;


    @GetMapping("/category={categoryName}")
    public ResponseEntity<List<ProductViewDto>> getProductViewsByCategoryName(@PathVariable String categoryName) {
        List<ProductViewDto> productViewDtos = productViewService.filterByCategory(categoryName);
        if (productViewDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productViewDtos, HttpStatus.OK);
        }

    }

    @GetMapping("/features/porductid={id}")
    public ResponseEntity<List<FeaturePerProduct>> findFeaturePerProductId(@PathVariable Long id) {
        List<FeaturePerProduct> featurePerProducts = featurePerProductService.findByProductId(id);
        if (featurePerProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(featurePerProducts, HttpStatus.OK);
        }
    }

    @GetMapping("/dates={startDate}&{endDate}")
    public ResponseEntity<List<ProductViewDto>> getProductViewsByDates(@PathVariable String startDate, @PathVariable String endDate) {
        List<ProductViewDto> productViewDtos = productViewService.filterByDates(startDate, endDate);
        if (productViewDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productViewDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/location={locationName}")
    public ResponseEntity<List<ProductViewDto>> getProductViewsByLocation(@PathVariable String locationName) {
        List<ProductViewDto> productViewDtos = productViewService.filterByLocation(locationName);
        if (productViewDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productViewDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/dates={startDate}&{endDate}/locationName={locationName}")
    public ResponseEntity<List<ProductViewDto>> getProductViewsByDatesAndLocation(@PathVariable String startDate, @PathVariable String endDate, @PathVariable String locationName) {
        List<ProductViewDto> productViewDtos = productViewService.filterByDatesAndLocation(startDate, endDate, locationName);
        if (productViewDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productViewDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/dates={startDate}&{endDate}/category={categoryName}")
    public ResponseEntity<List<ProductViewDto>> getProductViewsByDatesAndCategory(@PathVariable String startDate, @PathVariable String endDate, @PathVariable String categoryName) {
        List<ProductViewDto> productViewDtos = productViewService.filterByDatesAndCategory(startDate, endDate, categoryName);
        if (productViewDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productViewDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/category={categoryName}/locationName={locationName}")
    public ResponseEntity<List<ProductViewDto>> getProductViewsByCategoryAndLocation(@PathVariable String categoryName, @PathVariable String locationName) {
        List<ProductViewDto> productViewDtos = productViewService.filterByCategoryAndLocation(categoryName, locationName);
        if (productViewDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productViewDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/home")
    public ResponseEntity<List<ProductViewDto>> getProductViewsByCategoryAndLocationAndDates(@RequestParam String categoryName, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String locationName) {
        List<ProductViewDto> productViewDtos = productViewService.filterByCategoryAndLocationAndDates(categoryName, startDate, endDate, locationName);
        if (productViewDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productViewDtos, HttpStatus.OK);
        }
    }


    //TODO DELETE THIS
    @GetMapping("/test={id}")
    public ResponseEntity<BookingDetailsForMail> getBookingDetailsForMail(@PathVariable Long id) {
        return new ResponseEntity<>(bookingDetailsForMailService.getBookingDetailsForMail(id), HttpStatus.OK);
    }


}
