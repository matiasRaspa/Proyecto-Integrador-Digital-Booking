package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.location;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.City;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.CityDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.location.CityRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.location.ICityService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CityService implements ICityService<CityDto> {
    private final static Logger logger = Logger.getLogger(CityService.class);

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;

    @Override
    public CityDto save(CityDto city) {
        City cityEntity = mapToEntity(city);
        cityEntity.setFullName(getStateName(cityEntity), getCountryName(cityEntity));
        cityEntity = cityRepository.save(cityEntity);
        return mapToDto(cityEntity);
    }

    @Override
    public CityDto findById(Long id) {
        return cityRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public CityDto updateById(Long id, CityDto city) {
        if (cityRepository.findById(id).isPresent()) {
            City cityEntity = mapToEntity(city);
            cityEntity.setId(id);
            cityEntity = cityRepository.save(cityEntity);
            return mapToDto(cityEntity);
        } else {
            throw new ResourceNotFoundException("City id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (cityRepository.findById(id).isPresent()) {
            cityRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("City not found with id: " + id);
        }

    }

    @Override
    public List<CityDto> findAll() {

        return cityRepository.findAll().stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<CityDto> saveAll(List<CityDto> cities) {
        List<City> citiesEntity = cities.stream().map(this::mapToEntity).collect(java.util.stream.Collectors.toList());
        citiesEntity = cityRepository.saveAll(citiesEntity);
        return citiesEntity.stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    //-- Mapper---
    public City mapToEntity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }

    public CityDto mapToDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }


    //Get the state and country name from state id
    public String getStateName(City city) {
       Long id = city.getState().getId();
        String stateName = stateService.findById(id).getName();
        return stateService.findById(id).getName();
    }

    public String getCountryName(City city) {
        Long id = city.getState().getId();
        return countryService.findById(stateService.findById(id).getCountry().getId()).getName()
        ;
    }
}
