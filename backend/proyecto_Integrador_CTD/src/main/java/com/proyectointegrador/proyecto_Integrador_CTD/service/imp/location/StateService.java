package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.location;

import com.proyectointegrador.proyecto_Integrador_CTD.controller.CategoryController;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.Product;
import com.proyectointegrador.proyecto_Integrador_CTD.domain.location.State;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.ProductDto;
import com.proyectointegrador.proyecto_Integrador_CTD.dto.location.StateDto;
import com.proyectointegrador.proyecto_Integrador_CTD.exception.ResourceNotFoundException;
import com.proyectointegrador.proyecto_Integrador_CTD.repository.location.StateRepository;
import com.proyectointegrador.proyecto_Integrador_CTD.service.location.IStateService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService implements IStateService<StateDto> {
    final static Logger logger = Logger.getLogger(StateService.class);

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StateDto save(StateDto state) {
        State stateEntity = mapToEntity(state);
        stateEntity = stateRepository.save(stateEntity);
        return mapToDto(stateEntity);
    }

    @Override
    public StateDto findById(Long id) {
        logger.info("findById: " + id);
        return stateRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public StateDto updateById(Long id, StateDto state) {
        if (stateRepository.findById(id).isPresent()) {
            State stateEntity = mapToEntity(state);
            stateEntity.setId(id);
            stateEntity = stateRepository.save(stateEntity);
            return mapToDto(stateEntity);
        } else {
            throw new ResourceNotFoundException("State id " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (stateRepository.findById(id).isPresent()) {
            stateRepository.deleteById(id);
        } else {
            logger.error("State not found with id " + id);
            throw new ResourceNotFoundException("State not found with id " + id);
        }

    }

    @Override
    public List<StateDto> findAll() {
        return stateRepository.findAll().stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<StateDto> saveAll(List<StateDto> states) {
        List<State> stateEntities = states.stream().map(this::mapToEntity).collect(java.util.stream.Collectors.toList());
        return stateRepository.saveAll(stateEntities).stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    //-- Mapper---
    public StateDto mapToDto(State state) {
        return modelMapper.map(state, StateDto.class);

    }

    public State mapToEntity(StateDto stateDto) {
        return modelMapper.map(stateDto, State.class);
    }
}
