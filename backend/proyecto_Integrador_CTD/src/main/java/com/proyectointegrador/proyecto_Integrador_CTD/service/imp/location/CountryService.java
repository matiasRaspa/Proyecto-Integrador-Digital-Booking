package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.location;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.Country;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CountryDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.location.CountryRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.location.ICountryService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService<CountryDto> {

    final static Logger logger = Logger.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CountryDto save(CountryDto country) {

        Country countryEntity = mapToEntity(country);
        countryEntity = countryRepository.save(countryEntity);
        return mapToDto(countryEntity);
    }

    @Override
    public CountryDto findById(Long id) {
        return countryRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public CountryDto updateById(Long id, CountryDto country) {
        if (countryRepository.findById(id).isPresent()) {
            Country countryEntity = mapToEntity(country);
            countryEntity.setId(id);
            countryEntity = countryRepository.save(countryEntity);
            return mapToDto(countryEntity);
        } else {
            throw new ResourceNotFoundException("Country id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (countryRepository.findById(id).isPresent()) {
            countryRepository.deleteById(id);
        } else {
            logger.error("Country not found with id " + id);
            throw new ResourceNotFoundException("Country not found with id " + id);
        }


    }


    @Override
    public List<CountryDto> findAll() {
        return countryRepository.findAll().stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<CountryDto> saveAll(List<CountryDto> countries) {
       List<Country> countriesEntity = countries.stream().map(this::mapToEntity).collect(java.util.stream.Collectors.toList());
         countriesEntity = countryRepository.saveAll(countriesEntity);
        return countriesEntity.stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    //-- Mapper---
    public Country mapToEntity(CountryDto countryDto) {
        return modelMapper.map(countryDto, Country.class);
    }

    public CountryDto mapToDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }
}
